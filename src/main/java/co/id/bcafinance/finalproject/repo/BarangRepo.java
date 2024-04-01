package co.id.bcafinance.finalproject.repo;

import co.id.bcafinance.finalproject.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarangRepo extends JpaRepository<Barang, Long> {
    Optional<Barang> findBarangByKodeBarang(String kodeBarang);

    Optional<Barang> findByImageId(String imageId);
}
