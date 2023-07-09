package pe.gob.minjus.sisarb.service;

import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.domain.SedeEspecialidad;

import java.util.List;

public interface SedeEspecialidadService {
    List<SedeEspecialidad> findBySede(Integer sedeId);
    void saveBySede(Sede request,String auditUsuario);
    void deleteAllBySedeId(Integer sedeId,String auditUsuarioModifica);

}
