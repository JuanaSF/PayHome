package ar.com.ada.api.payhome.models.request;

import ar.com.ada.api.payhome.entities.Pais.TipoIdImpositivoEnum;

public class EmpresaRequest {

    public Integer paisId;
    public TipoIdImpositivoEnum tipoIdImpositivo;
    public String idImpositivo;
    public String nombre;
}