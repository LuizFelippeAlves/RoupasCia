package com.roupasecia.roupasecia.Repository;

import com.roupasecia.roupasecia.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
