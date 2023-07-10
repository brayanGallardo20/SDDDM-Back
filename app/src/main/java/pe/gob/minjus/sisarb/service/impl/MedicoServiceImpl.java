package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.Medico;
import pe.gob.minjus.sisarb.model.mapper.MedicoMapper;
import pe.gob.minjus.sisarb.model.request.PersonaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.MedicoService;
import pe.gob.minjus.sisarb.utils.Constantes;
import java.util.List;

@Slf4j
@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    MedicoMapper mapper;

    @Override
    public ResponseEntity<Respuesta<List<Medico>>> listPaginated(PersonaBusquedaRequest request) {
        Respuesta<List<Medico>> response = new Respuesta<>();
        List<Medico> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Respuesta<List<Medico>>> listAll() {
        Respuesta<List<Medico>> response = new Respuesta<>();
        List<Medico> list = mapper.listAll();
        response.setData(list);
        response.setTotalRegistros(list.size());
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Medico>> findById(Integer id)  {
        Respuesta<Medico> response = new Respuesta<>();
        Medico objGet = obtenerPorId(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 
    @Override
    public ResponseEntity<Respuesta<Medico>> save(Medico request)  {
        Respuesta<Medico> response = new Respuesta<>();
        mapper.save(request);
        Medico objSave = obtenerPorId(request.getIdMedico());
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Medico>> update(Medico request)  {
        Respuesta<Medico> response = new Respuesta<>();
        mapper.update(request);
        Medico objUpdate = mapper.findById(request.getIdMedico());
        //Medico objAu = obtenerPorId(request.getIdMedico());
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Medico>> deleteById(Medico request)  {
        Respuesta<Medico> response = new Respuesta<>();
        mapper.deleteById(request.getIdMedico());
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    Medico obtenerPorId(Integer id) {
    	Medico objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }
    
   /* @Override
    public ResponseEntity<Respuesta<List<TipoDocumento>>> listChoose() {
        Respuesta<List<TipoDocumento>> response = new Respuesta<>();
        List<TipoDocumento> list = mapper.listChoose();
        response.setData(list);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/
}
