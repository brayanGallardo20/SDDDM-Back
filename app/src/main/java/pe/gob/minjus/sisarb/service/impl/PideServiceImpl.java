package pe.gob.minjus.sisarb.service.impl;

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.psm.ws.PideClientWS;
import pe.gob.minjus.psm.ws.domain.ResponseReniec;
import pe.gob.minjus.psm.ws.domain.ResponseSunat;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationConfiguracion;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.Configuracion;
import pe.gob.minjus.sisarb.model.mapper.ConfiguracionMapper;
import pe.gob.minjus.sisarb.model.pide.response.PidePersonaJuridica;
import pe.gob.minjus.sisarb.model.pide.response.PidePersonaNatural;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ConfiguracionService;
import pe.gob.minjus.sisarb.service.PideService;
import pe.gob.minjus.sisarb.utils.Constantes;

@Slf4j
@Service
public class PideServiceImpl implements PideService {

    @Value("${sisarb.entorno.configuracion}")
    private String entornoConfiguracion;

    @Autowired
    ConfiguracionService configuracionService;

    @Override
    public ResponseEntity<Respuesta<PidePersonaNatural>> getDataPersonaNaturalByDni(String dni) {
        validateFieldRequired(dni,"DNI");
        Respuesta<PidePersonaNatural> response = new Respuesta<>();
        String urlPide = configuracionService.getWebServicePide(entornoConfiguracion);
        try {
            PideClientWS client = new PideClientWS(urlPide);
            ResponseReniec responseReniec = client.obtenerPersonaPorDNIResponse(dni);
            log.info(responseReniec.toString());
            log.info(responseReniec.getMessage());
            PidePersonaNatural personaNatural;
            boolean success = responseReniec.getSuccess();
            if(success){
                personaNatural = new PidePersonaNatural();
                personaNatural.setNombre(responseReniec.getPersona().getNombres());
                personaNatural.setApellidoPaterno(responseReniec.getPersona().getApePaterno());
                personaNatural.setApellidoMaterno(responseReniec.getPersona().getApeMaterno());
                personaNatural.setDireccion(responseReniec.getPersona().getDireccion());

                response.setData(personaNatural);
                response.setMensaje(responseReniec.getMessage());
            }else{
                String mensaje = responseReniec.getMessage();
                response.setMensaje( (mensaje!=null)?mensaje:Constantes.MSJ_DATO_NO_ENCONTRADO_EN_PIDE);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
          return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.getMessage());
            response.setMensaje(Constantes.MSJ_PIDE_NO_DISPONIBLE);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Respuesta<PidePersonaJuridica>> getDataPersonaJuridicaByRuc(String ruc) {
        validateFieldRequired(ruc,"RUC");
        Respuesta<PidePersonaJuridica> response = new Respuesta<>();
        String urlPide = configuracionService.getWebServicePide(entornoConfiguracion);
        try {
            PideClientWS client = new PideClientWS(urlPide);
            ResponseSunat responseSunat = client.obtenerPersonaPorRucResponse(ruc);
            PidePersonaJuridica personaJuridica;
            boolean success = responseSunat.getSuccess();
            if(success){
                personaJuridica = new PidePersonaJuridica();
                personaJuridica.setRuc(responseSunat.getPersona().getRuc());
                personaJuridica.setRazonSocial(responseSunat.getPersona().getRazonSocial());
                response.setData(personaJuridica);
                response.setMensaje(responseSunat.getMessage());
            }else{
                String mensaje = responseSunat.getMessage();
                response.setMensaje( (mensaje!=null)?mensaje:Constantes.MSJ_DATO_NO_ENCONTRADO_EN_PIDE);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.getMessage());
            response.setMensaje(Constantes.MSJ_PIDE_NO_DISPONIBLE);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   void validateFieldRequired(String campo,String tipo){
      if(campo==null){
           throw new EntityValidationCustom(Constantes.MSJ_CAMPO_REQUERIDO+": "+tipo);
      }
      if(campo.trim().isEmpty()){
          throw new EntityValidationCustom(Constantes.MSJ_CAMPO_REQUERIDO+": "+tipo);
      }
    }
}
