package co.id.bcafinance.finalproject.repo;


import co.id.bcafinance.finalproject.model.Pesanan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PesananRepo extends JpaRepository<Pesanan,Long> {

    Optional<Pesanan> findPesananByKodePesanan(String kodePesanan);
}
