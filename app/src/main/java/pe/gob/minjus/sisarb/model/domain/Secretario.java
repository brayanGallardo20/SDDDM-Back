package pe.gob.minjus.sisarb.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Secretario extends GenericDomain{
    private Integer secretarioId;
    private PersonaNatural personaNatural;
}
