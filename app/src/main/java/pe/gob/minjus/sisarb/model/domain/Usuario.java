package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario extends  GenericDomain{

    private Integer idusuario;
    
    private Integer idpersona;

    private String usuario;

    private String clave;
}
