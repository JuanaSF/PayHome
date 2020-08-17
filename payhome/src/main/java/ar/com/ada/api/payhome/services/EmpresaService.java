package ar.com.ada.api.payhome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.payhome.entities.Empresa;
import ar.com.ada.api.payhome.entities.Pais.TipoIdImpositivoEnum;
import ar.com.ada.api.payhome.repos.EmpresaRepository;
import ar.com.ada.api.payhome.utils.ValidadorIdImpositivo;
import ar.com.ada.api.payhome.utils.ValidadorIdImpositivo.EmpresaValidacionEnum;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepo;

	public Integer crearEmpresa(Integer paisId, TipoIdImpositivoEnum tipoIdImpositivo, String idImpositivo, String nombre) {

        Empresa empresa = new Empresa();
        empresa.setPaisId(paisId);
        empresa.setTipoIdImpositivo(tipoIdImpositivo);
        empresa.setIdImpositivo(idImpositivo);
        empresa.setNombre(nombre);
        empresaRepo.save(empresa);

        if(empresa.getEmpresaId() == null)
            return null;
        
        return empresa.getEmpresaId();
    }

    public EmpresaValidacionEnum validarDatosEmpresa(String nombre, String idImpositivo) {

        ValidadorIdImpositivo validador = new ValidadorIdImpositivo();
        EmpresaValidacionEnum resVal = validador.validarDatos(nombre, idImpositivo);

        return resVal;
    }

	public List<Empresa> listarEmpresas() {
		return empresaRepo.findAll();
	}
    
}