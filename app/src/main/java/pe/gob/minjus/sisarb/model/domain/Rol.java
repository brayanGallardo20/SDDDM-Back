package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
	private int idRol;
	private String nombre;
	private boolean estado;
	private boolean principal;
}
