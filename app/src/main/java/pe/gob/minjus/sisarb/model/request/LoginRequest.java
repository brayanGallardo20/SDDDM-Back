package pe.gob.minjus.sisarb.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest{
    private String clave;
    private String usuario;
}
