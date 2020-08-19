package ar.com.ada.api.payhome.utils;

public class Validador {
    
    public ResultadoValidacionEnum validarDatos(String nombre, String idImpositivo) {

        // Si es nulo, error
        if (idImpositivo == null)
            return ResultadoValidacionEnum.ID_IMPOSITIVO_INVALIDO;

        // ID impositivo al menos de 11 digitos y maximo 20
        if (!(idImpositivo.length() >= 11 && idImpositivo.length() <= 20))
            return ResultadoValidacionEnum.ID_IMPOSITIVO_INVALIDO;

        for (char caracter : idImpositivo.toCharArray()) {
            if (!Character.isDigit(caracter))
                return ResultadoValidacionEnum.ID_IMPOSITIVO_INVALIDO;
        }

        if (nombre == null)
            return ResultadoValidacionEnum.NOMBRE_INVALIDO;

        if (nombre.length() > 100)
            return ResultadoValidacionEnum.NOMBRE_INVALIDO;

        // Si llego hassta aqui, es que todo lo de arriba, era valido
        return ResultadoValidacionEnum.OK;
    }

    public enum ResultadoValidacionEnum {
        OK, // Cuando esta todo validado ok
        NOMBRE_INVALIDO, // Nombre tenga algun problema
        ID_IMPOSITIVO_INVALIDO // ID impositivo tenga un problema
    }
}