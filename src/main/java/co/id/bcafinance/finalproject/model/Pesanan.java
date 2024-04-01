package co.id.bcafinance.finalproject.model;
/*
IntelliJ IDEA 2023.3.3 (Ultimate Edition)
Build #IU-233.14015.106, built on January 25, 2024
@Author Cornelius
Java Developer
Created on 3/25/2024 14:15 PM
@Last Modified 3/25/2024 14:15 PM
Version 1.0
*/

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MstPesanan")
public class Pesanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPesanan")
    private Long idPesanan;

    @Column(name = "KodePesanan")
    private String kodePesanan;

    @Column(name = "IdUser")
    private Long idUser;

    @Column(name = "IdKota")
    private Long idKota;

    @Column(name = "NamaPenerima")
    private String namaPenerima;

    @Column(name = "NoHp")
    private String noHp;

    @Column(name = "AlamatPengiriman")
    private String alamatPengiriman;

    @Column(name = "Jumlah")
    private Integer jumlah;

    @Column(name = "TotalHarga")
    private Integer totalHarga;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedBy", nullable = false)
    private Long createdBy = 1L;

    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate = new Date();

    @Column(name = "ModifiedBY")
    private Long modifiedBy;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "IdUser", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "IdKota", insertable = false, updatable = false)
    private Kota kota;

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

    public String getKodePesanan() {
        return kodePesanan;
    }

    public void setKodePesanan(String kodePesanan) {
        this.kodePesanan = kodePesanan;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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

    @ManyToMany

    @JoinTable(
            name = "MapPesananBarang",
            joinColumns = @JoinColumn(name = "IDPesanan"),
            inverseJoinColumns = @JoinColumn(name = "IdBarang")
    )
    private List<Barang> ltBarang;




    public List<Barang> getLtBarang() {
        return ltBarang;
    }


    public void setLtBarang(List<Barang> ltBarang) {
        this.ltBarang = ltBarang;
    }


    public Long getIDPesanan() {
        return idPesanan;
    }

    public void setIDPesanan(Long idPesanan) {
        this.idPesanan = idPesanan;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdKota() {
        return idKota;
    }

    public void setIdKota(Long idKota) {
        this.idKota = idKota;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }



}
    

