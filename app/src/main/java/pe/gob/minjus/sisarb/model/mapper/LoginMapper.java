package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Usuario;

public interface LoginMapper {
    Usuario loginSistema(Usuario request);
}
