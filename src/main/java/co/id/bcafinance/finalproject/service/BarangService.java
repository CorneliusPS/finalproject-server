package co.id.bcafinance.finalproject.service;

import co.id.bcafinance.finalproject.configuration.CloudinaryConfig;
import co.id.bcafinance.finalproject.configuration.OtherConfig;
import co.id.bcafinance.finalproject.core.IService;
import co.id.bcafinance.finalproject.dto.BarangDTO;
import co.id.bcafinance.finalproject.handler.ResponseHandler;
import co.id.bcafinance.finalproject.model.Barang;
import co.id.bcafinance.finalproject.model.KategoriBarang;
import co.id.bcafinance.finalproject.repo.BarangRepo;
import co.id.bcafinance.finalproject.handler.RequestCapture;
import co.id.bcafinance.finalproject.util.LoggingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class BarangService implements IService<Barang> {

    @Autowired
    BarangRepo barangRepo;

    private String [] strExceptionArr = new String[2];

    @Value("${uploadDir}")
    private String uploadFolder;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    @Override
    public ResponseEntity<Object> save(Barang barang, HttpServletRequest request) {
        Optional<Barang> existingBarang = barangRepo.findBarangByKodeBarang(barang.getKodeBarang());
        if(barang==null) {
            return new ResponseHandler().generateResponse("Data Tidak Valid", HttpStatus.BAD_REQUEST, null, "FV02001", request);//FAILED VALIDATION
        }else if (existingBarang.isPresent()) {
            return new ResponseHandler().generateResponse("Barang Sudah ada", HttpStatus.CONFLICT, null, "DSA1001", request);
        }

        try {
            barangRepo.save(barang);
        } catch (Exception e)
        {
            strExceptionArr[1] = "save(Barang barang, HttpServletRequest request) LINE 55"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Barang gagal dibuat!",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE01001", request
            ); //FAILED ERROR
        }
        return new ResponseHandler().generateResponse("Barang berhasil dibuat!",
                HttpStatus.CREATED,
                barang,
                null,
                request
        );
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Barang> findOneBarang = barangRepo.findById(id);

        if (findOneBarang.isEmpty()) {
            return new ResponseHandler().generateResponse("Barang tidak ditemukan!", HttpStatus.NOT_FOUND, null, "DNF1001", request);
        }

        return new ResponseHandler().generateResponse("Barang berhasil ditemukan!",
                HttpStatus.OK,
                findOneBarang.get(),
                null,
                request
        );
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long id, Barang barang, HttpServletRequest request) {
        // Refactor di bagian sini
        Optional<Barang> findOneBarang = barangRepo.findById(id);

        if (findOneBarang.isEmpty()) {
            return new ResponseHandler().generateResponse("Barang tidak ditemukan!", HttpStatus.NOT_FOUND, null, "DNF1001", request);
        }

        try {
            Barang updateBarang = findOneBarang.get();

            // Trim and convert to lowercase
            String kodeBarang = barang.getKodeBarang().replaceAll("\\s", " ").trim().toLowerCase();
            kodeBarang = kodeBarang.replaceAll("\\s", "");

            if (barang.getKodeBarang().equals(kodeBarang)) {
                return new ResponseHandler().generateResponse("Kode Barang Sudah ada!", HttpStatus.CONFLICT, null, "DNF1001", request);
            }

//            String namaKategori = namaBarang.getNamaKategori().trim().toLowerCase();

            updateBarang.setKodeBarang(kodeBarang);
            updateBarang.setNamaBarang(barang.getNamaBarang());
            updateBarang.setHargaBarang(barang.getHargaBarang());
            updateBarang.setStokBarang(barang.getStokBarang());
            updateBarang.setStatusBarang(barang.getStatusBarang());
            updateBarang.setDeskripsiBarang(barang.getDeskripsiBarang());
            updateBarang.setActive(barang.getActive());
            updateBarang.setKategoriBarang(barang.getKategoriBarang());
        } catch (Exception e) {
            return new ResponseHandler().generateResponse("Tidak dapat melakukan update barang!", HttpStatus.BAD_REQUEST, null, "DTU1001", request);
        }

        return new ResponseHandler().generateResponse("Data berhasil diperbarui!",
                HttpStatus.OK,
                null,
                null,
                request
        );
    }

    public ResponseEntity<Object> updateImageBarang(String kodeBarang, MultipartFile file, HttpServletRequest request) {
        Optional<Barang> findOneBarang = barangRepo.findBarangByKodeBarang(kodeBarang);

        if (findOneBarang.get().getKodeBarang() == null) {
            return new ResponseHandler().generateResponse("Data tidak ada!",
                    HttpStatus.NOT_FOUND,
                    null,
                    "FE01001", request);//FAILED ERROR
        } else if (!findOneBarang.get().getKodeBarang().equals(kodeBarang) || findOneBarang.get().getImageId() == null) {
            return new ResponseHandler().generateResponse("Foto barang tidak ada dalam database", HttpStatus.NOT_FOUND, null, "DSA1001", request);
        }

        String cloudinaryImageId = findOneBarang.get().getImageId();
        try {
            cloudinaryConfig.delete(cloudinaryImageId);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to delete image from Cloudinary", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            BufferedImage bi = ImageIO.read(file.getInputStream());
            if (bi == null) {
                return new ResponseEntity<>("Gambar tidak valid!", HttpStatus.BAD_REQUEST);
            }
            Map result = cloudinaryConfig.upload(file);

            String imageId = (String) result.get("public_id");
            String[] parts = imageId.split("/");
            String filename = parts[1];

            findOneBarang.get().setImageBarang((String) result.get("url"));
            findOneBarang.get().setImageId(filename);

        } catch (Exception e) {
            strExceptionArr[1] = "save(GroupMenu groupMenu, HttpServletRequest request) LINE 55"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Disimpan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE01001", request);//FAILED ERROR
        }

        return new ResponseHandler().generateResponse("Gambar behasil diupload!",
                HttpStatus.CREATED,
                null,
                null, request);
    }

    @Override
    public ResponseEntity<Object> find(Pageable pageable, String filterBy, String value, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findWithoutPage(HttpServletRequest request) {
        List<Barang> findAllBarang = barangRepo.findAll();

        if (findAllBarang.isEmpty()) {
            return new ResponseHandler().generateResponse("Barang tidak ada!", HttpStatus.NOT_FOUND, null, "DTA1001", request);
        }

        return new ResponseHandler().generateResponse("Barang berhasil ditemukan!",
                HttpStatus.OK,
                findAllBarang,
                null,
                request
        );
    }

    public ResponseEntity<Object> findAllBarangWithImages(BarangDTO barangDTO, HttpServletRequest request) {
        try {
            List<Barang> allBarang = barangRepo.findAll();

            if (allBarang.isEmpty()) {
                return new ResponseHandler().generateResponse("Data tidak ada!",
                        HttpStatus.NOT_FOUND,
                        null,
                        "FE01001", request);//FAILED ERROR
            }

            return new ResponseHandler().generateResponse("Successfully retrieved all Barang",
                    HttpStatus.OK, allBarang, null, request);
        } catch (Exception e) {
            strExceptionArr[1] = "findAllBarangWithImages(HttpServletRequest request) LINE X" + RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data retrieval failed",
                    HttpStatus.INTERNAL_SERVER_ERROR, null, "FE01002", request);
        }
    }

    public ResponseEntity<Object> saveImgCd(String kodeBarang, MultipartFile file, HttpServletRequest request) {
        Optional<Barang> findOneBarang = barangRepo.findBarangByKodeBarang(kodeBarang);

        if (findOneBarang.get().getKodeBarang() == null) {
            return new ResponseHandler().generateResponse("Data tidak ada!",
                    HttpStatus.NOT_FOUND,
                    null,
                    "FE01001", request);//FAILED ERROR
        } else if (findOneBarang.get().getKodeBarang().equals(kodeBarang) && findOneBarang.get().getImageId() != null) {
            return new ResponseHandler().generateResponse("Barang sudah ada dalam database", HttpStatus.CONFLICT, null, "DSA1001", request);
        }

        try {
            BufferedImage bi = ImageIO.read(file.getInputStream());
            if (bi == null) {
                return new ResponseEntity<>("Gambar tidak valid!", HttpStatus.BAD_REQUEST);
            }
            Map result = cloudinaryConfig.upload(file);
            System.out.println(result);

            String imageId = (String) result.get("public_id");
            String[] parts = imageId.split("/");
            String filename = parts[1];


            findOneBarang.get().setImageBarang((String) result.get("url"));
            findOneBarang.get().setImageId(filename);

        } catch (Exception e) {
            strExceptionArr[1] = "save(GroupMenu groupMenu, HttpServletRequest request) LINE 55"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Disimpan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE01001", request);//FAILED ERROR
        }

        return new ResponseHandler().generateResponse("Gambar behasil diupload!",
                HttpStatus.CREATED,
                null,
                null, request);
    }

    public ResponseEntity<Object> deleteBarang(String imageId, HttpServletRequest request) {
        Optional<Barang> getOneBarang = barangRepo.findByImageId(imageId);
        if (getOneBarang.isEmpty()) {
            return new ResponseEntity<>("Barang tidak ditemukan!", HttpStatus.NOT_FOUND);
        }
        String cloudinaryImageId = getOneBarang.get().getImageId();
        System.out.println(cloudinaryImageId);
        try {
            cloudinaryConfig.delete(cloudinaryImageId);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to delete image from Cloudinary", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        barangRepo.deleteById(getOneBarang.get().getIdBarang());

        return new ResponseHandler().generateResponse("Barang berhasil dihapus!",
                HttpStatus.OK,
                null,
                null, request);
    }

    public ResponseEntity<Object> findTotalBarang(HttpServletRequest request) {
        Long totalBarang = barangRepo.count();

        return new ResponseHandler().generateResponse("Total Barang",
                HttpStatus.OK,
                totalBarang,
                null, request);
    }
}
