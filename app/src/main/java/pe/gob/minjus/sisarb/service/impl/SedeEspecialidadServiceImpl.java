package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.domain.SedeEspecialidad;
import pe.gob.minjus.sisarb.model.mapper.SedeEspecialidadMapper;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.SedeEspecialidadService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SedeEspecialidadServiceImpl implements SedeEspecialidadService {

    @Autowired
    SedeEspecialidadMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;


    @Transactional(readOnly = true)
    @Override
    public List<SedeEspecialidad> findBySede(Integer sedeId) {
        return mapper.findBySede(sedeId);
    }

    @Transactional
    @Override
    public void saveBySede(Sede request,String auditUsuario) {
        deleteAllBySedeId(request.getSedeId(),auditUsuario);
        if (!request.getListadoSedeEspecialidad().isEmpty()){
            List<SedeEspecialidad> list = request.getListadoSedeEspecialidad();
            list = list.stream().map(e-> {
                e.setSedeId(request.getSedeId());
                e.setAuditUsuarioCreacion(auditUsuario);
                return e;
            }).collect(Collectors.toList());
            list.forEach( e->{
                mapper.save(e);
                auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE_ESPECIALIDAD,
                        (e.getSedeEspecialidadId() == null) ? null : e.getSedeEspecialidadId().toString(),
                        auditUsuario, AuditTipoEnum.I.name(),
                        null, JsonConvert.objectToJsonString(e)));
            } );
        }
    }

    @Transactional
    @Override
    public void deleteAllBySedeId(Integer sedeId, String auditUsuarioModifica) {
        List<SedeEspecialidad> list= mapper.findBySede(sedeId);
        list.stream().forEach( (e)->{
            SedeEspecialidad objAu = mapper.findById(e.getSedeEspecialidadId());
            mapper.deleteById(e.getSedeEspecialidadId(),auditUsuarioModifica);
            SedeEspecialidad objUpdate = mapper.findById(e.getSedeEspecialidadId());
            auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE_ESPECIALIDAD,
                    (e.getSedeEspecialidadId() == null) ? null : e.getSedeEspecialidadId().toString(),
                    auditUsuarioModifica,
                    AuditTipoEnum.D.name(),
                    JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        } );
    }
}
