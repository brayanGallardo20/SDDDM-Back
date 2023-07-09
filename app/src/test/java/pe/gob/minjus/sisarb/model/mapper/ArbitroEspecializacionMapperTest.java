package pe.gob.minjus.sisarb.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.gob.minjus.sisarb.model.domain.ArbitroEspecializacion;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
class ArbitroEspecializacionMapperTest {


    @Autowired
    ArbitroEspecializacionMapper mapper;

    @Test
    void findByArbitro() {
        // given
        Integer sizeBase =0;

        //when
        List<ArbitroEspecializacion> list = mapper.findByArbitro(1);
        list.forEach(e->log.info(e.toString()));

        //then
        assertThat(list.size()>sizeBase);

    }

    @DisplayName("Metodo guardar")
    @Test
    void save() {
        //given
        Integer saveResult = 1;
        ArbitroEspecializacion obj = ArbitroEspecializacion.builder()
                .arbitroId(1)
                .especializacionId(25)
                .build();
        obj.setAuditUsuarioCreacion("USER_PRUEBA");

        //when
        Integer save = mapper.save(obj);

        //then
        assertThat( save).isEqualTo(saveResult);
    }

    @Test
    void update() {
        //given
        Integer updateResult = 1;
        ArbitroEspecializacion obj = ArbitroEspecializacion.builder()
                .arbitroEspecializacionId(1)
                .arbitroId(1)
                .especializacionId(25)
                .build();
        obj.setAuditUsuarioModifica("USER_MODIFICA");

        //when
        Integer update = mapper.update(obj);

        //then
        assertThat( update).isEqualTo(updateResult);
    }

    @Test
    void deleteById() {
        //given
        Integer deleteResult = 1;
        //when
        Integer delete = mapper.deleteById(1,"USER_DELETED");
        //then
        assertThat( delete).isEqualTo(deleteResult);
    }

}