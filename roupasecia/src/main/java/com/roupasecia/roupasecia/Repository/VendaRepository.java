package com.roupasecia.roupasecia.Repository;

import com.roupasecia.roupasecia.Entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
