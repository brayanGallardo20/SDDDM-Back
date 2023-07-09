package pe.gob.minjus.sisarb.model.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SedeMateriaArbitral extends GenericDomain{
    private Integer sedeMateriaArbitralId;
    private Integer materiaArbitralId;
    private Integer sedeId;
}
