package ar.com.ada.api.payhome.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.payhome.entities.Empresa;
import ar.com.ada.api.payhome.models.request.*;
import ar.com.ada.api.payhome.models.response.*;
import ar.com.ada.api.payhome.services.*;
import ar.com.ada.api.payhome.utils.Validador.ResultadoValidacionEnum;

@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @PostMapping("/empresas")
    public ResponseEntity<GenericResponse> crearEmpresa(@RequestBody EmpresaRequest empresa) {

        GenericResponse resp = new GenericResponse();
        ResultadoValidacionEnum resultadoValidacion = empresaService.validarDatosEmpresa(empresa.nombre, empresa.idImpositivo);

        if(resultadoValidacion != ResultadoValidacionEnum.OK){
            resp.isOk = false;
            resp.message = "No se pudo validar la empresa: " + resultadoValidacion;
            return ResponseEntity.badRequest().body(resp);
        }

        Integer id = empresaService.crearEmpresa(empresa.paisId, empresa.tipoIdImpositivo, 
        empresa.idImpositivo, empresa.nombre);

        if(id != null){
            resp.isOk = true;
            resp.id = id;
            resp.message = "Empresa creada correctamente";
            return ResponseEntity.ok(resp);
        }
        resp.isOk = false;
        resp.message = "No se pudo crear la empresa";
        return ResponseEntity.badRequest().body(resp);
    }

    @GetMapping("/empresas")
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        
        List<Empresa> le = empresaService.listarEmpresas();

        return ResponseEntity.ok(le);
    }
    
}