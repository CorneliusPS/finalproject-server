package co.id.bcafinance.finalproject.dto;
/*
IntelliJ IDEA 2023.3.3 (Ultimate Edition)
Build #IU-233.14015.106, built on January 25, 2024
@Author Cornelius
Java Developer
Created on 3/25/2024 15:58 PM
@Last Modified 3/25/2024 15:58 PM
Version 1.0
*/

import co.id.bcafinance.finalproject.model.Barang;
import co.id.bcafinance.finalproject.model.Kota;
import co.id.bcafinance.finalproject.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PesananDTO {
    @NotNull(message = "Kode Pesanan Tidak Boleh Kosong")
    @NotBlank(message = "Kode Pesanan Tidak Boleh Kosong")
    @NotEmpty(message = "Kode Pesanan Tidak Boleh Kosong")
    public String kodePesanan;

    public User user;
    public Kota kota;

    @NotEmpty(message = "Nama Penerima Tidak Boleh Kosong")
    @NotNull(message = "Nama Penerima Tidak Boleh Kosong")
    @NotBlank(message = "Nama Penerima Tidak Boleh Kosong")
    public String namaPenerima;

    @NotEmpty(message = "No Hp Tidak Boleh Kosong")
    @NotNull(message = "No Hp Tidak Boleh Kosong")
    @NotBlank(message = "No Hp Tidak Boleh Kosong")
    public String noHp;

    @NotEmpty(message = "Alamat Pengiriman Tidak Boleh Kosong")
    @NotNull(message = "Alamat Pengiriman Tidak Boleh Kosong")
    @NotBlank(message = "Alamat Pengiriman Tidak Boleh Kosong")
    public String alamatPengiriman;
    public Integer jumlah;
    public Integer totalHarga;

    private enum eStatusBarang {
        DIPROSES,
        DIKIRIM,
        DITERIMA
    }
    @NotNull(message = "StatusBarang Tidak Boleh NULL")
    private eStatusBarang status;
    private List<Barang> ltBarang;

    public List<Barang> getLtBarang() {
        return ltBarang;
    }

    public void setLtBarang(List<Barang> ltBarang) {
        this.ltBarang = ltBarang;
    }

    public String getKodePesanan() {
        return kodePesanan;
    }

    public void setKodePesanan(String kodePesanan) {
        this.kodePesanan = kodePesanan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Kota getKota() {
        return kota;
    }

    public void setKota(Kota kota) {
        this.kota = kota;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamatPengiriman() {
        return alamatPengiriman;
    }

    public void setAlamatPengiriman(String alamatPengiriman) {
        this.alamatPengiriman = alamatPengiriman;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Integer totalHarga) {
        this.totalHarga = totalHarga;
    }

    public eStatusBarang getStatus() {
        return status;
    }

    public void setStatus(eStatusBarang status) {
        this.status = status;
    }

}
    

