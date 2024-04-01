package co.id.bcafinance.finalproject.controller;

import co.id.bcafinance.finalproject.dto.TambahKategoriDTO;
import co.id.bcafinance.finalproject.model.KategoriBarang;
import co.id.bcafinance.finalproject.service.KategoriService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/kategori-mgmnt")
public class KategoriController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    KategoriService kategoriService;

    @PostMapping("/v1/kategori-barang")
    public ResponseEntity<Object> save(@Valid @RequestBody TambahKategoriDTO tambahKategoriDTO, HttpServletRequest request) {
        KategoriBarang newKategoriBarang = modelMapper.map(tambahKategoriDTO, new TypeToken<KategoriBarang>() {}.getType());

        return kategoriService.save(newKategoriBarang, request);
    }

    @GetMapping("/v1/kategori-barang/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id, HttpServletRequest request) {
        return kategoriService.findById(id, request);
    }

    @DeleteMapping("/v1/kategori-barang/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id, HttpServletRequest request) {
        return kategoriService.delete(id, request);
    }

    @PutMapping("/v1/kategori-barang/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TambahKategoriDTO tambahKategoriDTO, HttpServletRequest request) {
        KategoriBarang updateKategoriBarang = modelMapper.map(tambahKategoriDTO, KategoriBarang.class);

        return kategoriService.update(id, updateKategoriBarang, request);
    }

    @GetMapping("/v1/kategori-barang")
    public ResponseEntity<Object> findAllWithoutPage(HttpServletRequest request) {
        return kategoriService.findWithoutPage(request);
    }
}


