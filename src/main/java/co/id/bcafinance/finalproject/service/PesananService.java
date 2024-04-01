package co.id.bcafinance.finalproject.service;
/*
IntelliJ IDEA 2023.3.3 (Ultimate Edition)
Build #IU-233.14015.106, built on January 25, 2024
@Author Cornelius
Java Developer
Created on 3/25/2024 14:42 PM
@Last Modified 3/25/2024 14:42 PM
Version 1.0
*/

import co.id.bcafinance.finalproject.configuration.OtherConfig;
import co.id.bcafinance.finalproject.core.IService;
import co.id.bcafinance.finalproject.handler.RequestCapture;
import co.id.bcafinance.finalproject.handler.ResponseHandler;
import co.id.bcafinance.finalproject.model.KategoriBarang;
import co.id.bcafinance.finalproject.model.Pesanan;
import co.id.bcafinance.finalproject.repo.PesananRepo;
import co.id.bcafinance.finalproject.util.LoggingFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PesananService implements IService<Pesanan> {

    @Autowired
    PesananRepo pesananRepo;

    private String [] strExceptionArr = new String[2];

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseEntity<Object> save(Pesanan pesanan, HttpServletRequest request) {
        Optional<Pesanan> existingPesanan = pesananRepo.findPesananByKodePesanan(pesanan.getKodePesanan());
        if(pesanan==null) {
            return new ResponseHandler().generateResponse("Data Tidak Valid", HttpStatus.BAD_REQUEST, null, "FV02001", request);//FAILED VALIDATION
        }else if (existingPesanan.isPresent()) {
            return new ResponseHandler().generateResponse("Pesanan Sudah ada", HttpStatus.CONFLICT, null, "DSA1001", request);
        }

        try {
            pesananRepo.save(pesanan);
        } catch (Exception e)
        {
            strExceptionArr[1] = "save(Pesanan pesanan, HttpServletRequest request) LINE 55"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Pesanan gagal dibuat!",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE01001", request
            ); //FAILED ERROR
        }
        return new ResponseHandler().generateResponse("Pesanan Berhasil",
                HttpStatus.CREATED,
                null, null, request
        );
    }


    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long id, Pesanan pesanan, HttpServletRequest request) {
        Optional<Pesanan> findOnePesanan = pesananRepo.findById(id);
        if(findOnePesanan.isEmpty()) {
            return new ResponseHandler().generateResponse("Pesanan Tidak ditemukan", HttpStatus.BAD_REQUEST, null, "FV02001", request);//FAILED VALIDATION
        }
        try{
            Pesanan updatePesanan = findOnePesanan.get();

            String kodePesanan = pesanan.getKodePesanan().replaceAll("\\s", " ").trim().toLowerCase();
            kodePesanan = kodePesanan.replaceAll("\\s", "");


            if (pesanan.getKodePesanan().equals(kodePesanan)) {
                return new ResponseHandler().generateResponse("Kode Pesanan Sudah ada!", HttpStatus.CONFLICT, null, "DNF1001", request);
            }

            updatePesanan.setKodePesanan(kodePesanan);
            updatePesanan.setIdUser(pesanan.getIdUser());
            updatePesanan.setIdKota(pesanan.getIdKota());
            updatePesanan.setNoHp(pesanan.getNoHp());
            updatePesanan.setTotalHarga(pesanan.getTotalHarga());
            updatePesanan.setAlamatPengiriman(pesanan.getAlamatPengiriman());

        } catch (Exception e){
            return new ResponseHandler().generateResponse("Tidak dapat melakukan update pesanan!", HttpStatus.BAD_REQUEST, null, "DTU1001", request);
        }

        return new ResponseHandler().generateResponse("Pesanan berhasil diperbarui!",
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
        List<Pesanan> findAllPesanan = pesananRepo.findAll();

        if (findAllPesanan.isEmpty()) {
            return new ResponseHandler().generateResponse("Pesanan tidak ada!", HttpStatus.NOT_FOUND, null, "DTA1001", request);
        }

        return new ResponseHandler().generateResponse("Pesanan berhasil ditemukan!",
                HttpStatus.OK,
                findAllPesanan,
                null,
                request
        );
    }

    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Pesanan> findOnePesanan = pesananRepo.findById(id);

        if (findOnePesanan.isEmpty()) {
            return new ResponseHandler().generateResponse("Pesanan tidak ditemukan!", HttpStatus.NOT_FOUND, null, "DNF1001", request);
        }

        return new ResponseHandler().generateResponse("Pesanan Barang berhasil ditemukan!",
                HttpStatus.OK,
                findOnePesanan.get(),
                null,
                request
        );
    }


}

    

