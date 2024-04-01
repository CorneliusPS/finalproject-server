package co.id.bcafinance.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MstBarang")
public class Barang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBarang")
    private Long idBarang;

    @Column(name = "KodeBarang", nullable = false, unique = true)
    private String kodeBarang;

    @Column(name = "NamaBarang", nullable = false)
    private String namaBarang;

    @Column(name = "ImageBarang")
    private String imageBarang;

    @Column(name = "ImageId")
    private String imageId;

    @Column(name = "HargaBarang")
    private BigDecimal hargaBarang;

    @Column(name = "StokBarang", nullable = false)
    private Long stokBarang;

    private enum eStatusBarang {
        AVAILABLE,
        NOT_AVAILABLE
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "StatusBarang", nullable = false)
    private eStatusBarang statusBarang;

    @Column(name = "DeskripsiBarang")
    private String deskripsiBarang;

    @Column(name = "IsActive")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "IDKategori",
            foreignKey = @ForeignKey(name = "FK_TO_KATEGORI"))
    private KategoriBarang kategoriBarang;

    /**
     Start Group Audit trails
     */
    @Column(name = "CreatedBy", nullable = false)
    private Long createdBy = 1L;

    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate = new Date();

    @Column(name = "ModifiedBy")
    private Long modifiedBy;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    public Long getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Long idBarang) {
        this.idBarang = idBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getImageBarang() {
        return imageBarang;
    }

    public void setImageBarang(String imageBarang) {
        this.imageBarang = imageBarang;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public BigDecimal getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(BigDecimal hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public Long getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(Long stokBarang) {
        this.stokBarang = stokBarang;
    }

    public eStatusBarang getStatusBarang() {
        return statusBarang;
    }

    public void setStatusBarang(eStatusBarang statusBarang) {
        this.statusBarang = statusBarang;
    }

    public String getDeskripsiBarang() {
        return deskripsiBarang;
    }

    public void setDeskripsiBarang(String deskripsiBarang) {
        this.deskripsiBarang = deskripsiBarang;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


    public KategoriBarang getKategoriBarang() {
        return kategoriBarang;
    }

    public void setKategoriBarang(KategoriBarang kategoriBarang) {
        this.kategoriBarang = kategoriBarang;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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
