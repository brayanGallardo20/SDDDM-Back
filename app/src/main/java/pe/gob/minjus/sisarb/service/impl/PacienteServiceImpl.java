package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.model.domain.Paciente;
import pe.gob.minjus.sisarb.model.mapper.PacienteMapper;
import pe.gob.minjus.sisarb.model.request.PersonaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.PacienteService;
import pe.gob.minjus.sisarb.utils.Constantes;
import java.util.List;

@Slf4j
@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteMapper mapper;

    @Override
    public ResponseEntity<Respuesta<List<Paciente>>> listPaginated(PersonaBusquedaRequest request) {
        Respuesta<List<Paciente>> response = new Respuesta<>();
        List<Paciente> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Respuesta<List<Paciente>>> listAll() {
        Respuesta<List<Paciente>> response = new Respuesta<>();
        List<Paciente> list = mapper.listAll();
        response.setData(list);
        response.setTotalRegistros(list.size());
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Paciente>> findById(Integer id)  {
        Respuesta<Paciente> response = new Respuesta<>();
        Paciente objGet = obtenerPorId(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 
    @Override
    public ResponseEntity<Respuesta<Paciente>> save(Paciente request)  {
        Respuesta<Paciente> response = new Respuesta<>();
        mapper.save(request);
        Paciente objSave = obtenerPorId(request.getIdPaciente());
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Paciente>> update(Paciente request)  {
        Respuesta<Paciente> response = new Respuesta<>();
        mapper.update(request);
        Paciente objUpdate = mapper.findById(request.getIdPaciente());
        //Paciente objAu = obtenerPorId(request.getIdPaciente());
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Paciente>> deleteById(Paciente request)  {
        Respuesta<Paciente> response = new Respuesta<>();
        mapper.deleteById(request.getIdPaciente());
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    Paciente obtenerPorId(Integer id) {
    	Paciente objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }
}
