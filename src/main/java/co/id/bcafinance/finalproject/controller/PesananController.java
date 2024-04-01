package co.id.bcafinance.finalproject.controller;
/*
IntelliJ IDEA 2023.3.3 (Ultimate Edition)
Build #IU-233.14015.106, built on January 25, 2024
@Author Cornelius
Java Developer
Created on 3/25/2024 14:41 PM
@Last Modified 3/25/2024 14:41 PM
Version 1.0
*/

import co.id.bcafinance.finalproject.dto.PesananDTO;
import co.id.bcafinance.finalproject.dto.TambahBarangDTO;
import co.id.bcafinance.finalproject.model.Barang;
import co.id.bcafinance.finalproject.model.Pesanan;
import co.id.bcafinance.finalproject.service.PesananService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/pesanan-mgmnt")
public class PesananController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PesananService pesananService;

    @PostMapping("/v1/pesanan")
    public ResponseEntity<Object> save(@Valid @RequestBody PesananDTO pesananDTO, HttpServletRequest request){
        Pesanan pesanan = modelMapper.map(pesananDTO, new TypeToken<Pesanan>() {}.getType());
        return pesananService.save(pesanan, request);
    }

    @GetMapping("/v1/pesanan")
    public ResponseEntity<Object> findAllWithoutPage(HttpServletRequest request) {
        return pesananService.findWithoutPage(request);
    }

    @GetMapping("/v1/pesanan/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id, HttpServletRequest request) {
        return pesananService.findById(id, request);
    }




}
    

