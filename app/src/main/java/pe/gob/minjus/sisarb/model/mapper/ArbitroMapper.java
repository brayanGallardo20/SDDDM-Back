package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Arbitro;

public interface ArbitroMapper {

    Arbitro findById(Integer id);
    Integer save(Arbitro model);
    Integer update(Arbitro model);
    Integer deleteById(Integer id);
}
