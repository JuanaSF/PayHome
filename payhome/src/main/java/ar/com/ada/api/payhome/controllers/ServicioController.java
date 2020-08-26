package ar.com.ada.api.payhome.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.payhome.entities.*;
import ar.com.ada.api.payhome.models.request.*;
import ar.com.ada.api.payhome.models.response.*;
import ar.com.ada.api.payhome.services.*;
import ar.com.ada.api.payhome.services.ServicioService.ServicioValidacionEnum;

@RestController
@RequestMapping("/api")
public class ServicioController {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    DeudorService duedorService;

    @Autowired
    ServicioService servicioService;
    
    @PostMapping("/servicios")
    public ResponseEntity<GenericResponse> crearServicio(@RequestBody ServicioRequest servicioReq) {

        GenericResponse response = new GenericResponse();

        Servicio servicio = new Servicio();

        Empresa empresaEncontrada = empresaService.buscarPor(servicioReq.empresaId);
        servicio.setEmpresa(empresaEncontrada);

        Deudor deudorEncontrado = duedorService.buscarPor(servicioReq.deudorId);
        servicio.setDeudor(deudorEncontrado);

        TipoServicio tipoServicio = servicioService.buscarTipoServicioPor(servicioReq.tipoServicioId);
        servicio.setTipoServicio(tipoServicio);

        servicio.setTipoComprobante(servicioReq.tipoComprobanteId);
        servicio.setNumero(servicioReq.numero);
        servicio.setFechaEmision(servicioReq.fechaEmision);
        servicio.setFechaVencimiento(servicioReq.fechaVencimiento);
        servicio.setImporte(servicioReq.importe);
        servicio.setMoneda(servicioReq.moneda);
        servicio.setCodigoBarras(servicioReq.codigoBarras);
        servicio.setEstado(servicioReq.estadoId);
        
        ServicioValidacionEnum resultadoValidacion = servicioService.validarServicio(servicio);

        if(resultadoValidacion != ServicioValidacionEnum.OK) {
            
            response.isOk = false;
            response.message = "Hubo un error en la validación del servicio "+resultadoValidacion;
            return ResponseEntity.badRequest().body(response);
        }

        servicioService.crearServicio(servicio);

        if(servicio.getServicioId() == null) {
            response.isOk = false;
            response.message = "No se pudo crear el servicio";
            return ResponseEntity.badRequest().body(response);
        }
        response.id = servicio.getServicioId();
        response.isOk = true;
        response.message = "El servicio se registro con exito!"; 
        return ResponseEntity.ok(response);
    }
}