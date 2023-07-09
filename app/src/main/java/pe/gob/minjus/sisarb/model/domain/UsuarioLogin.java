package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLogin {
	private int idUsuario;
    private List<RolMenu> rolMenu;
    private List<Rol> rol;
    private String nombre;
    private String apePat;
    private String apeMat;
    private String usuario;
}
