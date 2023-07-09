package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.domain.SedeMateriaArbitral;
import pe.gob.minjus.sisarb.model.domain.SedePersona;

import java.util.List;

public interface SedePersonaMapper {
    SedePersona findById(Integer sedePersonaId);
    Integer save(SedePersona model);
    Integer update(SedePersona model);
    Integer deleteById(Integer sedePersonaId,String auditUsuarioModifica);

    List<SedePersona> findBySedePersonaCargo(Integer sedeId, Integer personaId, Integer personaCargoId);
}
