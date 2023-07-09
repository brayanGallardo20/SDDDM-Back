package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Tabla;

import java.util.List;

public interface TablaMapper {

    List<Tabla> listTabla(String databaseOwner);
}
