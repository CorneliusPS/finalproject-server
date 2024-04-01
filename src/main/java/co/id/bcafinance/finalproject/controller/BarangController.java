package co.id.bcafinance.finalproject.controller;

import co.id.bcafinance.finalproject.dto.BarangDTO;
import co.id.bcafinance.finalproject.dto.TambahBarangDTO;
import co.id.bcafinance.finalproject.dto.TambahKategoriDTO;
import co.id.bcafinance.finalproject.model.Barang;
import co.id.bcafinance.finalproject.model.KategoriBarang;
import co.id.bcafinance.finalproject.model.Pesanan;
import co.id.bcafinance.finalproject.service.BarangService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/barang-mgmnt")
public class BarangController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    BarangService barangService;


    @PostMapping("/v1/barang")
    public ResponseEntity<Object> save(@Valid @RequestBody TambahBarangDTO tambahBarangDTO, HttpServletRequest request) {
        Barang barang = modelMapper.map(tambahBarangDTO, new TypeToken<Barang>() {}.getType());

        return barangService.save(barang, request);
    }

    @GetMapping("/v1/barang")
    public ResponseEntity<Object> findAllWithoutPage(HttpServletRequest request) {
        return barangService.findWithoutPage(request);
    }

//    @GetMapping("/v1/barang")
//    public ResponseEntity<Object> findAllBarangWithImages(BarangDTO barangDTO, HttpServletRequest request) {
//        return barangService.findAllBarangWithImages(barangDTO, request);
//    }

    @PostMapping("/v1/barang-img")
    public ResponseEntity<Object> saveImgCd(@RequestParam("kodeBarang") String kodeBarang, @RequestParam("fotoBarang") MultipartFile file, HttpServletRequest request){
        return barangService.saveImgCd(kodeBarang, file, request);
    }

    @DeleteMapping("/v1/barang/{imageId}")
    public ResponseEntity<Object> delete(@PathVariable(value = "imageId") String imageId, HttpServletRequest request) {
        return barangService.deleteBarang(imageId, request);
    }

    @GetMapping("/v1/barang/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id, HttpServletRequest request) {
        return barangService.findById(id, request);
    }

    @PutMapping("/v1/barang/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody BarangDTO barangDTO, HttpServletRequest request) {
        Barang updateBarang = modelMapper.map(barangDTO, Barang.class);

        return barangService.update(id, updateBarang, request);
    }

    @PutMapping("/v1/barang-img")
    public ResponseEntity<Object> updateFotoBarang(@RequestParam("kodeBarang") String kodeBarang, @RequestParam("fotoBarang") MultipartFile file, HttpServletRequest request) {
        return barangService.updateImageBarang(kodeBarang, file, request);
    }

    @GetMapping("/v1/total-barang")
    public ResponseEntity<Object> getTotalBarang(HttpServletRequest request) {
        return barangService.findTotalBarang(request);
    }
}


