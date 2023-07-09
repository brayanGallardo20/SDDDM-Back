package pe.gob.minjus.sisarb.model.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InstitucionSecretario extends GenericDomain{
    private Integer institucionId;
    private Secretario secretario;
}
