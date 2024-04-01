package co.id.bcafinance.finalproject.service;

import co.id.bcafinance.finalproject.configuration.OtherConfig;
import co.id.bcafinance.finalproject.core.IService;
import co.id.bcafinance.finalproject.handler.RequestCapture;
import co.id.bcafinance.finalproject.handler.ResponseHandler;
import co.id.bcafinance.finalproject.model.KategoriBarang;
import co.id.bcafinance.finalproject.repo.KategoriRepo;
import co.id.bcafinance.finalproject.util.LoggingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KategoriService implements IService<KategoriBarang> {

    @Autowired
    KategoriRepo kategoriRepo;

        private String [] strExceptionArr = new String[2];

    @Override
    public ResponseEntity<Object> save(KategoriBarang kategoriBarang, HttpServletRequest request) {
        // Trim and convert to lowercase
        String kodeKategori = kategoriBarang.getKodeKategori().replaceAll("\\s", " ").trim().toLowerCase();
        kodeKategori = kodeKategori.replaceAll("\\s", "");

        Optional<KategoriBarang> existingKategori = kategoriRepo.findKategoriBarangByKodeKategori(kodeKategori);
        if (kategoriBarang == null) {
            return new ResponseHandler().generateResponse("Data tidak valid!", HttpStatus.BAD_REQUEST, null, "DTV1001", request);
        } else if (existingKategori.isPresent()) {
            return new ResponseHandler().generateResponse("Kategori sudah ada dalam database", HttpStatus.CONFLICT, null, "DSA1001", request);
        }

        try {
            String namaKategori = kategoriBarang.getNamaKategori().trim().toLowerCase();
            // Set the trimmed and lowercase values
            kategoriBarang.setKodeKategori(kodeKategori);
            kategoriBarang.setNamaKategori(namaKategori);

            kategoriRepo.save(kategoriBarang);
        } catch (Exception e) {
            strExceptionArr[1] = "save(KategoriBarang kategoriBarang, HttpServletRequest request) LINE 55"+ RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());

            return new ResponseHandler().generateResponse("Kategori gagal dibuat!",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE01001", request
            ); //FAILED ERROR
        }

        return new ResponseHandler().generateResponse("Kategori Barang berhasil dibuat!",
                HttpStatus.CREATED,
                kategoriBarang,
                null,
                request
        );
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<KategoriBarang> findOneKategori = kategoriRepo.findById(id);

        if (findOneKategori.isEmpty()) {
            return new ResponseHandler().generateResponse("Kategori tidak ditemukan!", HttpStatus.NOT_FOUND, null, "DNF1001", request);
        }

        return new ResponseHandler().generateResponse("Kategori Barang berhasil ditemukan!",
                HttpStatus.OK,
                findOneKategori.get(),
                null,
                request
        );
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        Optional<KategoriBarang> findOneKategori = kategoriRepo.findById(id);

        if (findOneKategori.isEmpty()) {
            return new ResponseHandler().generateResponse("Kategori tidak ditemukan!", HttpStatus.NOT_FOUND, null, "DNF1001", request);
        }

        try {
            kategoriRepo.deleteById(id);
        } catch (Exception e) {
            return new ResponseHandler().generateResponse("Tidak dapat menghapus kategori!", HttpStatus.BAD_REQUEST, null, "DTD1001", request);
        }

        return new ResponseHandler().generateResponse("Data berhasil dihapus!",
                HttpStatus.OK,
                null,
                null,
                request
        );
    }

    @Override
    public ResponseEntity<Object> update(Long id, KategoriBarang kategoriBarang, HttpServletRequest request) {
        // Refactor di bagian sini
        Optional<KategoriBarang> findOneKategori = kategoriRepo.findById(id);
        Optional<KategoriBarang> findkategoriByKode = kategoriRepo.findKategoriBarangByKodeKategori(kategoriBarang.getKodeKategori());

        if (findOneKategori.isEmpty()) {
            return new ResponseHandler().generateResponse("Kategori tidak ditemukan!", HttpStatus.NOT_FOUND, null, "DNF1001", request);
        } else if (findkategoriByKode.isPresent()) {
            return new ResponseHandler().generateResponse("Kode Barang sudah ada!", HttpStatus.CONFLICT, null, "DNF1001", request);
        }

        try {
            KategoriBarang updateKategoriBarang = findOneKategori.get();

            // Trim and convert to lowercase
            String kodeKategori = kategoriBarang.getKodeKategori().replaceAll("\\s", " ").trim().toLowerCase();
            kodeKategori = kodeKategori.replaceAll("\\s", "");

            String namaKategori = kategoriBarang.getNamaKategori().trim().toLowerCase();

            updateKategoriBarang.setKodeKategori(kodeKategori);
            updateKategoriBarang.setNamaKategori(namaKategori);
            updateKategoriBarang.setDeskripsiKategori(kategoriBarang.getDeskripsiKategori());
        } catch (Exception e) {
            return new ResponseHandler().generateResponse("Tidak dapat melakukan update kategori!", HttpStatus.BAD_REQUEST, null, "DTU1001", request);
        }

        return new ResponseHandler().generateResponse("Data berhasil diperbarui!",
                HttpStatus.OK,
                null,
                null,
                request
        );
    }

    @Override
    public ResponseEntity<Object> find(Pageable pageable, String filterBy, String value, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findWithoutPage(HttpServletRequest request) {
        List<KategoriBarang> findAllKategori = kategoriRepo.findAll();

        if (findAllKategori.isEmpty()) {
            return new ResponseHandler().generateResponse("Kategori tidak ada!", HttpStatus.NOT_FOUND, null, "DTA1001", request);
        }

        return new ResponseHandler().generateResponse("Kategori berhasil ditemukan!",
                HttpStatus.OK,
                findAllKategori,
                null,
                request
        );
    }
}

