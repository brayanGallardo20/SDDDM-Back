package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Configuracion;

public interface ConfiguracionMapper {
    Configuracion findById(String id);
    Integer update(Configuracion model);

    String getWebServiceSeguridad(String configuracionId);

    String getWebServicePide(String configuracionId);


}
