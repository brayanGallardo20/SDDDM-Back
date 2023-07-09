package pe.gob.minjus.sisarb.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UbigeoItem {

	private Long ubigeoId;
	private String origen;
	private String tipo;
	private String descripcion;
	private String ubigeo;
	private Long ubigeoPadreId;

	private String estadoRegistro;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModificacion;
	private Date fechaModificacion;

	
}
