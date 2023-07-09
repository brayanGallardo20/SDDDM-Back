package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.ArbitroEspecializacion;
import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.mapper.ArbitroEspecializacionMapper;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.ArbitroEspecializacionService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArbitroEspecializacionServiceImpl implements ArbitroEspecializacionService {

    @Autowired
    ArbitroEspecializacionMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;


    @Transactional(readOnly = true)
    @Override
    public List<ArbitroEspecializacion> findByArbitro(Integer arbitroId) {
        return mapper.findByArbitro(arbitroId);
    }

    @Transactional
    @Override
    public void saveByArbitro(Arbitro request,String auditUsuario) {
        deleteAllByArbitroId(request.getArbitroId(),auditUsuario);
        if (!request.getListadoArbitroEspecializacion().isEmpty()){
            List<ArbitroEspecializacion> list = request.getListadoArbitroEspecializacion();
            list = list.stream().map(e-> {
                e.setArbitroId(request.getArbitroId());
                e.setAuditUsuarioCreacion(auditUsuario);
                return e;
            }).collect(Collectors.toList());
            list.forEach( e->{
                mapper.save(e);
                auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_PERSONA_ESPECIALIZACION,
                        (e.getArbitroEspecializacionId() == null) ? null : e.getArbitroEspecializacionId().toString(),
                        auditUsuario, AuditTipoEnum.I.name(),
                        null, JsonConvert.objectToJsonString(e)));
            } );
        }
    }

    @Transactional
    @Override
    public void deleteAllByArbitroId(Integer personaId, String auditUsuarioModifica) {
        List<ArbitroEspecializacion> list= mapper.findByArbitro(personaId);
        list.stream().forEach( (e)->{
            ArbitroEspecializacion objAu = mapper.findById(e.getArbitroEspecializacionId());
            mapper.deleteById(e.getArbitroEspecializacionId(),auditUsuarioModifica);
            ArbitroEspecializacion objUpdate = mapper.findById(e.getArbitroEspecializacionId());
            auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_PERSONA_ESPECIALIZACION,
                    (e.getArbitroEspecializacionId() == null) ? null : e.getArbitroEspecializacionId().toString(),
                    auditUsuarioModifica,
                    AuditTipoEnum.D.name(),
                    JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        } );
    }
}
