package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;
import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.domain.InstitucionPersona;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;

@Getter
@Setter
public class InstitucionPersonaRequest {
    private PersonaNatural personaNatural;
    private InstitucionPersona institucionPersona;
    private Arbitro arbitro;
}
