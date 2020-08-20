package ar.com.ada.api.payhome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.payhome.entities.TipoServicio;
import ar.com.ada.api.payhome.repos.TipoServicioRepository;

@Service
public class ServicioService {

    @Autowired
    TipoServicioRepository tipoServiceRepo;

	public boolean crearTipoServicio(TipoServicio tipoServicio) {

		if (tipoServiceRepo.existsById(tipoServicio.getTipoServicioId()))
            return false;
        
        tipoServiceRepo.save(tipoServicio);
        return true;
	}

	public List<TipoServicio> listarTipoServicios() {
		return tipoServiceRepo.findAll();
	}
    
}