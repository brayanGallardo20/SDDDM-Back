package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.SedeEspecialidad;

import java.util.List;

public interface SedeEspecialidadMapper {
    List<SedeEspecialidad> findBySede(Integer sedeId);
    Integer save(SedeEspecialidad model);
    Integer update(SedeEspecialidad model);
    Integer deleteById(Integer sedeEspecialidadId,String auditUsuarioModifica);
    SedeEspecialidad findById(Integer sedeEspecialidadId);
}
