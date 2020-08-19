package ar.com.ada.api.payhome.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.payhome.entities.Deudor;

@Repository
public interface DeudorRepository extends JpaRepository<Deudor, Integer> {
    
}