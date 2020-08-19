package ar.com.ada.api.payhome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.payhome.entities.Deudor;
import ar.com.ada.api.payhome.entities.Pais.TipoIdImpositivoEnum;
import ar.com.ada.api.payhome.repos.DeudorRepository;
import ar.com.ada.api.payhome.utils.Validador;
import ar.com.ada.api.payhome.utils.Validador.ResultadoValidacionEnum;

@Service
public class DeudorService {

    @Autowired
    DeudorRepository deudorRepo;

    public Integer registrarDeudor(Integer paisId, TipoIdImpositivoEnum tipoIdImpositivo, String idImpositivo,
			String nombre) {
        
        Deudor deudor = new Deudor();
        deudor.setPaisId(paisId);
        deudor.setTipoIdImpositivo(tipoIdImpositivo);
        deudor.setIdImpositivo(idImpositivo);
        deudor.setNombre(nombre);
        deudorRepo.save(deudor);

        if(deudor.getDeudorId() == null)
            return null;
        
        return deudor.getDeudorId();
	}

	public ResultadoValidacionEnum validarDatosDeudor(String nombre, String idImpositivo) {
        
        Validador validador = new Validador();
        ResultadoValidacionEnum resVal = validador.validarDatos(nombre, idImpositivo);
        return resVal;
	}

	public List<Deudor> listarDeudores() {
		return deudorRepo.findAll();
	}

}