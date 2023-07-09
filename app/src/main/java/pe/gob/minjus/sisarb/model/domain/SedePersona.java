package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SedePersona extends GenericDomain{
    private Integer sedePersonaId;
    private Integer sedeId;
    private Integer personaId;
    private Integer personaCargoId;
}
