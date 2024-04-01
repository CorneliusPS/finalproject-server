package co.id.bcafinance.finalproject.dto;

import co.id.bcafinance.finalproject.model.KategoriBarang;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TambahBarangDTO {

    private Long idBarang;

    @NotNull(message = "KodeBarang Tidak Boleh NULL")
    @NotBlank(message = "KodeBarang Tidak Boleh Blank")
    @NotEmpty(message = "KodeBarang Tidak Boleh Kosong")
    private String kodeBarang;

    @NotNull(message = "NamaBarang Tidak Boleh NULL")
    @NotBlank(message = "NamaBarang Tidak Boleh Blank")
    @NotEmpty(message = "NamaBarang Tidak Boleh Kosong")
    private String namaBarang;

    private BigDecimal hargaBarang;

    @NotNull(message = "StokBarang Tidak Boleh NULL")
    private Integer stokBarang;

    private enum eStatusBarang {
        AVAILABLE,
        NOT_AVAILABLE
    }
    @NotNull(message = "StatusBarang Tidak Boleh NULL")
    private eStatusBarang statusBarang;

    private String deskripsiBarang;

    private Boolean isActive;

    private KategoriBarang kategoriBarang;

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

    public BigDecimal getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(BigDecimal hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public Integer getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(Integer stokBarang) {
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

    public Boolean getActive() {
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
}


