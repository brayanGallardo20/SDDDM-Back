package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.UbigeoItem;
import pe.gob.minjus.sisarb.model.mapper.UbigeoMapper;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.rest.model.CommonUbigeo;
import pe.gob.minjus.sisarb.rest.model.CommonWsResponse;
import pe.gob.minjus.sisarb.rest.temp.RestCommonWs;
import pe.gob.minjus.sisarb.service.UbigeoService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.Validators;

import java.util.List;

@Slf4j
@Service
public class UbigeoServiceImpl implements UbigeoService {

    @Autowired
    UbigeoMapper ubigeoMapper;

    @Autowired
    RestCommonWs restCommonWs;


    @Transactional
    @Override
    public ResponseEntity<Respuesta<List<UbigeoItem>>> obtieneUbigeos(String origen, Long ubigeoPadreId) {
        Respuesta<List<UbigeoItem>> response = new Respuesta<>();
        try {
            List<UbigeoItem> list = ubigeoMapper.getUbigeoList(origen, ubigeoPadreId);
            response.setData(list);
            response.setTotalRegistros(list.size());
            response.setMensaje("Se encontraron resultados");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Respuesta<UbigeoItem>> obtenerUbigeoPorId(String origen, Long ubigeoId) {
        Respuesta<UbigeoItem> response = new Respuesta<>();
        try {

            UbigeoItem list = ubigeoMapper.getUbigeoPorId(origen, ubigeoId);
            response.setData(list);
            response.setTotalRegistros(1);
            response.setMensaje("Se encontraron resultados");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Respuesta<List<CommonUbigeo>>> getUbigeo(String codigoDepartamento, String codigoProvincia) {
        Respuesta<List<CommonUbigeo>> response = new Respuesta<>();
        validacionesUbigeo(codigoDepartamento,codigoProvincia);
        CommonWsResponse<List<CommonUbigeo>> responseCommon = restCommonWs.getUbigeo(codigoDepartamento,codigoProvincia);
        if(responseCommon.getIdOperacion().equals("0000")){
            response.setMensaje(Constantes.MSJ_REGISTRO_ENCONTRADOS);
            response.setData(responseCommon.getRptaLista());
        }else if(responseCommon.getIdOperacion().equals("9999")) {
            throw new EntityValidationCustom(responseCommon.getMensajeOperacion());
        }else{
            if(responseCommon.getMensajeOperacion().isEmpty() || responseCommon.getMensajeOperacion()==null){
                throw new EntityValidationCustom(Constantes.MSJ_REGISTRO_NO_ENCONTRADOS);
            }
            throw new EntityValidationCustom(responseCommon.getMensajeOperacion());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    void validacionesUbigeo(String codigoDepartamento, String codigoProvincia){



        if(codigoDepartamento!=null) validCodeDptoProvDis(codigoDepartamento);
        if(codigoProvincia!=null) validCodeDptoProvDis(codigoProvincia);
    }

    void validCodeDptoProvDis(String codigo){
        if(!Validators.validOnlyIntegerStrings(codigo)) throw new EntityValidationCustom("EL código está compuesto por solo números");
        if(codigo.length()!=2) throw new EntityValidationCustom("El código debe tener solo 2 dígitos");
    }
}
