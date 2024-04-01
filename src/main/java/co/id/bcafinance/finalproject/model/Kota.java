package co.id.bcafinance.finalproject.model;
/*
IntelliJ IDEA 2023.3.3 (Ultimate Edition)
Build #IU-233.14015.106, built on January 25, 2024
@Author Cornelius
Java Developer
Created on 3/25/2024 14:16 PM
@Last Modified 3/25/2024 14:16 PM
Version 1.0
*/

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MstKota")
public class Kota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDKota")
    private Long idKota;

    @Column(name = "Kota")
    private String kota;

    @Column(name = "Biaya")
    private Integer biaya;

    @Column(name = "Status")
    private String status;

    public Long getIdKota() {
        return idKota;
    }

    public void setIdKota(Long idKota) {
        this.idKota = idKota;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public Integer getBiaya() {
        return biaya;
    }

    public void setBiaya(Integer biaya) {
        this.biaya = biaya;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
    

