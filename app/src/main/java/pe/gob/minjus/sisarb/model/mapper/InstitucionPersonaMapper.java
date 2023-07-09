package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.InstitucionPersona;

public interface InstitucionPersonaMapper {
   
	InstitucionPersona findById(Integer id);
	 void updateInstitucionPersona(InstitucionPersona request);
	 void insertInstitucionPersona(InstitucionPersona request);
     InstitucionPersona findByIntitucionPersona(InstitucionPersona request);
     void delete(InstitucionPersona request);

}
