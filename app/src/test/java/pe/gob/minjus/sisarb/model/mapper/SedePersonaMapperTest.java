package pe.gob.minjus.sisarb.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.gob.minjus.sisarb.model.domain.SedePersona;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@Slf4j
@SpringBootTest
class SedePersonaMapperTest {
    
    @Autowired
    SedePersonaMapper mapper;

    @Test
    void findById() {
        // given
        Integer idFind =2;

        //when
        SedePersona find = mapper.findById(idFind);

        //then
        assertThat(find.getSedePersonaId()).isEqualTo(idFind);
    }

    @Test
    void save() {
        //given
        Integer saveResult = 1;
        SedePersona obj = SedePersona.builder()
                .sedeId(301)
                .personaId(426)
                .personaCargoId(103)
                .build();
        obj.setAuditUsuarioCreacion("USER_PRUEBA_CREATED");


        //when
        Integer save = mapper.save(obj);

        //then
        assertThat( save).isEqualTo(saveResult);
    }

    @Test
    void update() {
        //given
        Integer updateResult = 1;
        SedePersona obj = SedePersona.builder()
                .sedePersonaId(2)
                .sedeId(302)
                .personaId(426)
                .personaCargoId(103)
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
        Integer delete = mapper.deleteById(2,"USER_DELETED");
        //then
        assertThat( delete).isEqualTo(deleteResult);

    }

    @Test
    void findBySedePersonaCargo() {
        //given
        Integer sedeId = 302;
        Integer personaId =426;
        Integer personaCargoId =103;

        //when
        List<SedePersona> list = mapper.findBySedePersonaCargo(sedeId,personaId,personaCargoId);
         list.forEach(  e->log.info(e.toString())); ;
        log.info("Cantidad list: "+list.size());

        //then
        assertThat(list.size()>0);
    }
}