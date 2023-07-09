package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitucionSecretarioBusquedaRequest {
    private Integer institucionId;
    private Integer secretarioId;
    private String numeroDocumento;
}
