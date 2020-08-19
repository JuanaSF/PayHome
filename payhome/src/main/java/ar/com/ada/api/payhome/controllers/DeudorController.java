package ar.com.ada.api.payhome.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.payhome.entities.Deudor;
import ar.com.ada.api.payhome.models.request.*;
import ar.com.ada.api.payhome.models.response.*;
import ar.com.ada.api.payhome.services.DeudorService;
import ar.com.ada.api.payhome.utils.Validador.ResultadoValidacionEnum;

@RestController
@RequestMapping("/api")
public class DeudorController {

    @Autowired
    DeudorService deudorService;

    @PostMapping("/deudores")
    public ResponseEntity<GenericResponse> crearDeudor(@RequestBody DeudorRequest dr) {

        GenericResponse gr = new GenericResponse();
        ResultadoValidacionEnum resultadoValidacion = deudorService.validarDatosDeudor(dr.nombre, dr.idImpositivo);

        if(resultadoValidacion != ResultadoValidacionEnum.OK) {
            gr.isOk = false;
            gr.message = "No se pudo validar deudor: " + resultadoValidacion;
            return ResponseEntity.badRequest().body(gr);
        }

        Integer deudorId = deudorService.registrarDeudor(dr.paisId, dr.tipoIdImpositivo, dr.idImpositivo, dr.nombre);

        if(deudorId != null) {
            gr.isOk = true;
            gr.id = deudorId;
            gr.message = "Se registro deudor con exito!";
            return ResponseEntity.ok(gr);
        }
        gr.isOk = false;
        gr.message = "No se pudo registrar deudor";
        return ResponseEntity.badRequest().body(gr);
    }

    @GetMapping("/deudores")
    public ResponseEntity<List<Deudor>> obtenerDeudores() {

        List<Deudor>ld = deudorService.listarDeudores();

        return ResponseEntity.ok(ld);
    }
}