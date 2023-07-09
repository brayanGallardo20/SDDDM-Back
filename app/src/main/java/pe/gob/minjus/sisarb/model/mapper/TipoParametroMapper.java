package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.TipoParametro;
import pe.gob.minjus.sisarb.model.request.TipoParametroRequest;

import java.util.List;

public interface TipoParametroMapper {

    List<TipoParametro> listTipoParametro();
    List<TipoParametro> listTipoParametroAll();
    Integer listTipoParametroTotal (TipoParametroRequest request);
    void insert(TipoParametro request);
    void update(TipoParametro request);
    void delete(String id);
    TipoParametro find(TipoParametro request);
    TipoParametro findById(Integer id);
    Integer countTipoParametroById(Integer parametroId);     
}
