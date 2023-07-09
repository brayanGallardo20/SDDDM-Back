package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.UbigeoItem;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UbigeoMapper {

    List<UbigeoItem> getUbigeoList(@Param("origen") String origen,@Param("ubigeoPadreId") Long ubigeoPadreId);

    UbigeoItem getUbigeoPorId(@Param("origen") String origen,@Param("ubigeoId") Long ubigeoId);


}
