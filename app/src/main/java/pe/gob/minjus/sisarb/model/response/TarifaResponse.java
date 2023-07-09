package pe.gob.minjus.sisarb.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pe.gob.minjus.sisarb.model.domain.GenericDomain;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TarifaResponse extends GenericDomain {

    private Long tarifaId;
    
    private Long institucionId;
    
    private String cuantiaDesde;
    
    private String cuantiaHasta;

    private String honorarioArbitro;
    
    private String honorarioPagarParte;
    
    private Integer activo;

}