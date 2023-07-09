package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.domain.SedeMateriaArbitral;
import pe.gob.minjus.sisarb.model.mapper.SedeMateriaArbitralMapper;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.SedeMateriaArbitralService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class SedeMateriaArbitralServiceImpl implements SedeMateriaArbitralService {

    @Autowired
    SedeMateriaArbitralMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;


    @Transactional(readOnly = true)
    @Override
    public List<SedeMateriaArbitral> findBySede(Integer sedeId) {
        return mapper.findBySede(sedeId);
    }

    @Transactional
    @Override
    public void saveBySede(Sede request,String auditUsuario) {
        deleteAllBySedeId(request.getSedeId(),auditUsuario);
        if (!request.getListadoSedeMateriaArbitral().isEmpty()){
            List<SedeMateriaArbitral> list = request.getListadoSedeMateriaArbitral();
            list = list.stream().map(e-> {
                e.setSedeId(request.getSedeId());
                e.setAuditUsuarioCreacion(auditUsuario);
                return e;
            }).collect(Collectors.toList());
            list.forEach( e->{
                mapper.save(e);
                auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE_MATERIA_ARBITRAL,
                        (e.getSedeMateriaArbitralId() == null) ? null : e.getSedeMateriaArbitralId().toString(),
                        auditUsuario, AuditTipoEnum.I.name(),
                        null, JsonConvert.objectToJsonString(e)));

            } );
        }
    }

    @Transactional
    @Override
    public void deleteAllBySedeId(Integer sedeId, String auditUsuarioModifica) {
        List<SedeMateriaArbitral> list= mapper.findBySede(sedeId);
        list.stream().forEach( (e)->{
            SedeMateriaArbitral objAu = mapper.findById(e.getSedeMateriaArbitralId());
            mapper.deleteById(e.getSedeMateriaArbitralId(),auditUsuarioModifica);
            SedeMateriaArbitral objUpdate = mapper.findById(e.getSedeMateriaArbitralId());
            auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE_MATERIA_ARBITRAL,
                    (e.getSedeMateriaArbitralId() == null) ? null : e.getSedeMateriaArbitralId().toString(),
                    auditUsuarioModifica,
                    AuditTipoEnum.D.name(),
                    JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        } );
    }
}