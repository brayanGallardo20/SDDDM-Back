package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GenericDomain {

    //Atributos de auditoria

    private String auditUsuarioCreacion;
    private Date auditFechaCreacion;
    private String auditUsuarioModifica;
    private Date auditFechaModifica;

    private String auditFechaCreacionFormat;
    private String auditFechaModificaFormat;
}
