package ar.com.ada.api.payhome.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.payhome.entities.TipoServicio;
import ar.com.ada.api.payhome.models.response.GenericResponse;
import ar.com.ada.api.payhome.services.ServicioService;

@RestController
@RequestMapping("/api")
public class TipoServicioController {

    @Autowired
    ServicioService servicioService;
    
    @PostMapping("/tipos-servicios")
    public ResponseEntity<GenericResponse> crearTipoServicio(@RequestBody TipoServicio tipoServicio) {
        
        GenericResponse res = new GenericResponse();
        boolean resultado = servicioService.crearTipoServicio(tipoServicio);

        if(resultado) {
            res.isOk = true;
            res.id = tipoServicio.getTipoServicioId();
            res.message = "Tipo servicio creado correctamente";
            return ResponseEntity.ok(res);
        }
        res.isOk = false;
        res.message = "Este tipo de servicio ya existe";
        return ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/tipos-servicios")
    public ResponseEntity<List<TipoServicio>> listarTipoServicio() {
        
        List<TipoServicio> tSListado = servicioService.listarTipoServicios();
        
        return ResponseEntity.ok(tSListado);
    }

}