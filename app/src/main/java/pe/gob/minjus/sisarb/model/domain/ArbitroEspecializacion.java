package pe.gob.minjus.sisarb.model.domain;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArbitroEspecializacion extends  GenericDomain{
    private Integer arbitroEspecializacionId;
    private Integer arbitroId;
    private Integer especializacionId;
}
