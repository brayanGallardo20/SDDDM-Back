package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeclaracionInteres extends GenericDomain {

    private Integer declaracionInteresId;
    private String nombre;
}
