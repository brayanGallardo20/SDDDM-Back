package pe.gob.minjus.sisarb.service;

import pe.gob.minjus.sisarb.model.domain.SedePersona;

public interface SedePersonaService {
    SedePersona findById(Integer id);

    SedePersona save(SedePersona request);

    SedePersona update(SedePersona request);

    void deleteById(Integer sedePersonaId, String auditUsuarioModifica);
}
