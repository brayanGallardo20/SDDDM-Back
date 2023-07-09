package pe.gob.minjus.sisarb.model.pide.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PidePersonaNatural {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
}
