package pe.gob.minjus.sisarb.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Medico extends Persona{
	
	private Integer idMedico;
	private String numeroColeg;
	
}