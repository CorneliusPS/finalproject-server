package co.id.bcafinance.finalproject.dto;

import co.id.bcafinance.finalproject.model.Barang;
import co.id.bcafinance.finalproject.model.User;

import java.util.Date;
import java.util.List;

public class PeminjamanBarangDTO {

    private Date tanggalPeminjaman;

    private Date tanggalPengambilan;
    private enum eStatusPeminjaman {
        WAITING_CONFIRMATION,
        ACCEPTED,
        REJECTED
    }
    private eStatusPeminjaman statusPeminjaman;

    private Integer jumlahPeminjaman;

    private String catatanAdmin;

    private List<User> ltUser;

    private List<Barang> ltBarang;
}


