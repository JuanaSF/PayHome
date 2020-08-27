package ar.com.ada.api.payhome;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.payhome.entities.*;
import ar.com.ada.api.payhome.entities.Pais.TipoIdImpositivoEnum;
import ar.com.ada.api.payhome.services.*;
import ar.com.ada.api.payhome.utils.Validador.ResultadoValidacionEnum;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	EmpresaService empresaService;

	/* Test para Empresa
	Test1: que valide todos los datos correctamente
	Test2: que valide el Id Impositivo con valores incorrectos
	test3: que valide el nombre con valores incorrectos
	*/

	@Test
	void empresa_IdImpositivo_correcto() {

		ResultadoValidacionEnum resultado;

		Empresa empresa = new Empresa();
        empresa.setPaisId(32);
        empresa.setTipoIdImpositivo(TipoIdImpositivoEnum.CUIT);
        empresa.setIdImpositivo("5555432210090");
		empresa.setNombre("Zaraza Testing SA");
		
		resultado = empresaService.validarDatosEmpresa(empresa.getNombre(), empresa.getIdImpositivo());

		assertTrue(resultado == ResultadoValidacionEnum.OK);
	}

	@Test
	void empresa_IdImpositivo_incorrecto() {

		ResultadoValidacionEnum resultado;

		Empresa empresa = new Empresa();
        empresa.setPaisId(32);
        empresa.setTipoIdImpositivo(TipoIdImpositivoEnum.CUIT);
        empresa.setIdImpositivo("5555432210090X"); // <--Agrego una letra para que sea invalido
		empresa.setNombre("Zaraza Testing SA");
		
		resultado = empresaService.validarDatosEmpresa(empresa.getNombre(), empresa.getIdImpositivo());
		assertTrue(resultado == ResultadoValidacionEnum.ID_IMPOSITIVO_INVALIDO);

		empresa.setIdImpositivo("12345"); // tiene menos numeros que el minimo permitido
		resultado = empresaService.validarDatosEmpresa(empresa.getNombre(), empresa.getIdImpositivo());
		assertTrue(resultado == ResultadoValidacionEnum.ID_IMPOSITIVO_INVALIDO);

		empresa.setIdImpositivo("12345000000433844747657112"); // supera el maximo permitido
		resultado = empresaService.validarDatosEmpresa(empresa.getNombre(), empresa.getIdImpositivo());
		assertTrue(resultado == ResultadoValidacionEnum.ID_IMPOSITIVO_INVALIDO);

		empresa.setIdImpositivo(null); // el id es nulo
		resultado = empresaService.validarDatosEmpresa(empresa.getNombre(), empresa.getIdImpositivo());
		assertTrue(resultado == ResultadoValidacionEnum.ID_IMPOSITIVO_INVALIDO);
	}

	@Test
	void empresa_nombre_incorrecto() {

		ResultadoValidacionEnum resultado;

		Empresa empresa = new Empresa();
        empresa.setPaisId(32);
        empresa.setTipoIdImpositivo(TipoIdImpositivoEnum.CUIT);
        empresa.setIdImpositivo("5555432210090");
		empresa.setNombre(null); // el nombre es nulo
		
		resultado = empresaService.validarDatosEmpresa(empresa.getNombre(), empresa.getIdImpositivo());
		assertTrue(resultado == ResultadoValidacionEnum.NOMBRE_INVALIDO);

		//el nombre supera el maximo permitido
		empresa.setNombre("eeeeeeeeeeeeeeeeeeeeelNooooooooooooooooooooooooombreeeeeeeeeeeeeeeeeeeeeeeeeeeeeMaaaaaaaaaaaaaaaasLaaaaaaargoooooooo");
		assertTrue(resultado == ResultadoValidacionEnum.NOMBRE_INVALIDO);
	}

}
