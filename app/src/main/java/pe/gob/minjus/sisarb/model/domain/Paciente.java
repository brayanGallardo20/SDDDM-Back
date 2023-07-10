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
public class Paciente extends Persona{
	
	private Integer idPaciente;
	private double peso;
	private double altura;
	private int idtiposangre;
	
}