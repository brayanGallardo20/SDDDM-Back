package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.mapper.AuditoriaMapper;
import pe.gob.minjus.sisarb.model.request.AuditoriaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.utils.Constantes;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    AuditoriaMapper auditoriaMapper;


    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<Respuesta<List<Auditoria>>> listPaginated(AuditoriaBusquedaRequest request) {
        Respuesta<List<Auditoria>> response = new Respuesta<>();
        List<Auditoria> list = auditoriaMapper.listPaginated(request);
        Integer total = auditoriaMapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Respuesta<Auditoria>> insert(Auditoria request) {
        validar(request);
        Respuesta<Auditoria> response = new Respuesta<>();
        request.setMovimiento(auditoriaMapper.getMovimiento());
        request.setAuditFecha(new Date());
        auditoriaMapper.insert(request);
        response.setData(request);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int insertNoTrans(Auditoria request) {
        try {
            request.setMovimiento(auditoriaMapper.getMovimiento());
            request.setAuditFecha(new Date());
            return auditoriaMapper.insert(request);
        } catch (Exception e) {
            log.error("Error al insertar en auditoria: " + e.getMessage());
            return 0;
        }

    }

    void validar(Auditoria request) {
        log.info(request.toString());
        if (request.getTabla() == null) {
            log.error("El nombre de la tabla es obligatorio");
        }
        if (request.getCodigo() == null) {
            log.error("El código de la tabla es obligatorio");
        }
    }


}
