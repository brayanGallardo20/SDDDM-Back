package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SedeEspecialidad extends  GenericDomain{
    private Integer sedeEspecialidadId;
    private Integer sedeId;
    private Integer especialidadId;
}
