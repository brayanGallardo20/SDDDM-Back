package pe.gob.minjus.sisarb.model.mapper.datos;

import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.domain.SedeEspecialidad;

@DataJdbcTest
public class SedeEspecialidadDataTest {


    public static SedeEspecialidad dataSedeEspecialidadSave(){
        return new SedeEspecialidad().builder()
                .sedeEspecialidadId(null)
                .sedeId(21)
                .especialidadId(5)
                //.activo(null)
                .build();
      
    }


    public static SedeEspecialidad dataSedeEspecialidadUpdate(){
        return new SedeEspecialidad().builder()
                .sedeEspecialidadId(1)
                .sedeId(null)
                .especialidadId(null)
                //.activo(0)
                .build();
    }


}
