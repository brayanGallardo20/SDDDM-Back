package pe.gob.minjus.sisarb.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pe.gob.minjus.sisarb.model.domain.Institucion;
import pe.gob.minjus.sisarb.model.domain.InstitucionPersona;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InstitucionPersonaResponse {

  private PersonaNatural persona;
  private Institucion institucion;
  private InstitucionPersona institucionPersona;
    
}