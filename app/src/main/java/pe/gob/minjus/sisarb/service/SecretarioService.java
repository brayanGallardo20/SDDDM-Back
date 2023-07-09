package pe.gob.minjus.sisarb.service;

import pe.gob.minjus.sisarb.model.domain.Secretario;

public interface SecretarioService {

    //1. Registrar el secretario con su realción de persona_natural
    Secretario save(Secretario model);
}
