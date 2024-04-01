package co.id.bcafinance.finalproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TambahKategoriDTO {

    @NotNull(message = "Kategori Kode Tidak Boleh NULL")
    @NotBlank(message = "Kategori Kode Boleh Blank")
    @NotEmpty(message = "Kategori Kode Boleh Kosong")
    private String kodeKategori;

    @NotNull(message = "Nama Kategori Tidak Boleh NULL")
    @NotBlank(message = "Nama Kategori Kode Boleh Blank")
    @NotEmpty(message = "Nama Kategori Kode Boleh Kosong")
    private String namaKategori;

    private String deskripsiKategori;

    private Boolean isActive;

    public String getKodeKategori() {
        return kodeKategori;
    }

    public void setKodeKategori(String kodeKategori) {
        this.kodeKategori = kodeKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getDeskripsiKategori() {
        return deskripsiKategori;
    }

    public void setDeskripsiKategori(String deskripsiKategori) {
        this.deskripsiKategori = deskripsiKategori;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}


