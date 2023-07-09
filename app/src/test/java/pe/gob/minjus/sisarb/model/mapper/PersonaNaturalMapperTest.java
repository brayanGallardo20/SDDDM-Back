package pe.gob.minjus.sisarb.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class PersonaNaturalMapperTest {

    @Autowired
    PersonaNaturalMapper mapper;

    @Test
    void findById() {
        //given
        Integer idEncontrado = 311;
        //when
        PersonaNatural pFind = mapper.findById(idEncontrado);
        log.info(pFind.toString());
        //then
            assertThat( idEncontrado).isEqualTo(pFind.getPersonaNaturalId());
    }

    @Test
    void save(){
        //Given
        PersonaNatural persona = new PersonaNatural();
        persona.setTipoDocumentoId(463);
        persona.setNumeroDocumento("00033002");
        persona.setApellidoPaterno("Perez");
        persona.setNombre("Juan");
        persona.setUbigeoId(1234);
        persona.setDireccion("Av. Siempreviva 123");
        persona.setAuditUsuarioCreacion("admin");

        //when
        int result = mapper.save(persona);
        //then
        assertThat(result).isEqualTo(1);


    }

    @Test
    void findByDocumentPersona() {
        //GIVEN
        PersonaNatural request = new PersonaNatural();
        request.setTipoDocumentoId(463);
        request.setNumeroDocumento("00022211");
        //WHEN
            PersonaNatural busqueda = mapper.findByDocumentPersona(request);
            log.info(busqueda.toString());
        //THEN
        assertThat(busqueda.getPersonaNaturalId()>0);
    }
}