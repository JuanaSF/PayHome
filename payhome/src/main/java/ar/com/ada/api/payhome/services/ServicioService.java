package ar.com.ada.api.payhome.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.payhome.entities.Servicio;
import ar.com.ada.api.payhome.entities.TipoServicio;
import ar.com.ada.api.payhome.repos.ServicioRepository;
import ar.com.ada.api.payhome.repos.TipoServicioRepository;

@Service
public class ServicioService {

	@Autowired
	TipoServicioRepository tipoServiceRepo;

	@Autowired
	ServicioRepository servicioRepo;

	public boolean crearTipoServicio(TipoServicio tipoServicio) {

		if (tipoServiceRepo.existsById(tipoServicio.getTipoServicioId()))
			return false;

		tipoServiceRepo.save(tipoServicio);
		return true;
	}

	public List<TipoServicio> listarTipoServicios() {
		return tipoServiceRepo.findAll();
	}

	public TipoServicio buscarTipoServicioPor(Integer id) {

		Optional<TipoServicio> opTipoServicio = tipoServiceRepo.findById(id);
		if(opTipoServicio.isPresent()) {
			return opTipoServicio.get();
		}
		return null;
	}

	public ServicioValidacionEnum validarServicio(Servicio servicio) {

		if (servicio.getImporte().compareTo(new BigDecimal(0)) <= 0) {
            return ServicioValidacionEnum.IMPORTE_INVALIDO;
        }

        return ServicioValidacionEnum.OK;
	}

	public enum ServicioValidacionEnum {
        OK, IMPORTE_INVALIDO
	}
	
	public void crearServicio(Servicio servicio) {
		servicioRepo.save(servicio);
	}

}