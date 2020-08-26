package ar.com.ada.api.payhome.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.payhome.entities.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    Empresa findById(int id);
}