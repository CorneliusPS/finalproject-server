package co.id.bcafinance.finalproject.repo;

import co.id.bcafinance.finalproject.model.KategoriBarang;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KategoriRepo extends JpaRepository<KategoriBarang, Long> {
    Optional<KategoriBarang> findKategoriBarangByKodeKategori(String kodeKategori);
}

