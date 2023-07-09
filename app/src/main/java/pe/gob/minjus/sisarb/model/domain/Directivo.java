package pe.gob.minjus.sisarb.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Directivo extends GenericDomain {
    private Integer personaNaturalId;
    private Integer institucionPersonaId;
    private Integer institucionId;
    private Integer tipoDocumentoId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroDocumento;
    private String tipoDocumento;
    private Integer ubigeoId;
    private String correo;
    private String telefono;
    private String direccion;

    @JsonIgnore
    public String getTableName(){
        return "MAE_DIRECTIVO";
    }
}
