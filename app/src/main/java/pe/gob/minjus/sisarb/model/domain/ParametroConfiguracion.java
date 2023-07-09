package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ParametroConfiguracion{
	private Integer parametroConfiguracionId;
	private String ambienteConfiguracion;
	private String nombre;
	private String valor;
	private String detalle;
	private Date fechaCreacion;
	private String grupo;
	private Integer estado;
	private String auditUsuarioModifica;
	private Date auditFechaModifica;
	

	
}
