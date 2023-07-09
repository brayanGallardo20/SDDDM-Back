package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;
import pe.gob.minjus.sisarb.model.domain.Institucion;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;

@Getter
@Setter
public class InstitucionRequest {

    private Institucion institucion;
    private PersonaNatural persona;
}
