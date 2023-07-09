package pe.gob.minjus.sisarb.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.domain.SedeEspecialidad;
import pe.gob.minjus.sisarb.model.mapper.datos.SedeDataTest;
import pe.gob.minjus.sisarb.model.mapper.datos.SedeEspecialidadDataTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class SedeEspecialidadMapperTest {

    @Autowired
    SedeEspecialidadMapper sedeEspecialidadMapper;

    @DisplayName("Listar sede especialdiad por sedeId")
    @Test
    void findBySede() {
        //given
        Integer sedeId = 21;
        //when
        List<SedeEspecialidad> list;
        list = sedeEspecialidadMapper.findBySede(sedeId);
        //then
        log.info("Total: "+list.size());
        assertThat(list.size()>0);
    }


    @Test
    @DisplayName("Registrar una sede especialidad")
    void save() {
        //given
        SedeEspecialidad data = SedeEspecialidadDataTest.dataSedeEspecialidadSave();
        //when
        int save =   sedeEspecialidadMapper.save(data);
        //then
        assertThat( save).isEqualTo(1);
    }

    @Test
    @DisplayName("Actualizar una sede especialidad")
    void update() {
        //given
        SedeEspecialidad data = SedeEspecialidadDataTest.dataSedeEspecialidadUpdate();
        //when
        int save =   sedeEspecialidadMapper.update(data);
        //then
        assertThat( save).isEqualTo(1);
    }


    @Test
    @DisplayName("Eliminar todas la especialidades sede por sedeId")
    void deleteAllBySedeId() {
        //given
        int sedeId = 21;
        //when
        int delete =   sedeEspecialidadMapper.deleteById(sedeId,"USER_PRUEBA");
        //then
        assertThat( delete).isEqualTo(3);
    }




}