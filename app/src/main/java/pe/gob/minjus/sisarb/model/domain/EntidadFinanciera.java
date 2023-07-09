package pe.gob.minjus.sisarb.model.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EntidadFinanciera extends  GenericDomain{

    private Integer entidadFinancieraId;
    private String nombre;

}
