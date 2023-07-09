package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sancion extends GenericDomain {
    private Integer sancionId;
    private String nombre;
}
