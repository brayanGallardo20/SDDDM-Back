package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;
import pe.gob.minjus.sisarb.model.mapper.PersonaNaturalMapper;
import pe.gob.minjus.sisarb.service.AuditoriaService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PersonaServiceImplTest {

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Mock
    private PersonaNaturalMapper mapper;

    @Mock
    private AuditoriaService auditoriaService;


    @Test
    void save() {
        // Creamos un objeto PersonaNatural de prueba
        PersonaNatural persona = new PersonaNatural();
        persona.setTipoDocumentoId(463);
        persona.setNumeroDocumento("00033002");
        persona.setApellidoPaterno("Perez");
        persona.setNombre("Juan");
        persona.setUbigeoId(1234);
        persona.setDireccion("Av. Siempreviva 123");
        persona.setAuditUsuarioCreacion("admin");

        // Configuramos el mock de PersonaNaturalMapper para que devuelva un personaNaturalId al guardar el objeto
        when(mapper.save(persona)).thenReturn(312);

        // Configuramos el mock de PersonaNaturalMapper para que devuelva el objeto PersonaNatural completo al buscar por personaNaturalId
        when(mapper.findById(312)).thenReturn(persona);

        // Llamamos al método save de PersonaServiceImpl con el objeto PersonaNatural de prueba
        personaService.save(persona);

        // Verificamos que se haya llamado al método save de PersonaNaturalMapper con el objeto PersonaNatural de prueba
        verify(mapper, times(1)).save(persona);

        // Verificamos que se haya llamado al método findById de PersonaNaturalMapper con el personaNaturalId generado al guardar el objeto
        verify(mapper, times(1)).findById(312);

        // Verificamos que se haya llamado al método insertNoTrans de AuditoriaService
        verify(auditoriaService, times(1)).insertNoTrans(any(Auditoria.class));
    }

    @Test
    void savePruebaDirecta() {
        // Creamos un objeto PersonaNatural de prueba
        PersonaNatural persona = new PersonaNatural();
        persona.setApellidoPaterno("Perez");
        persona.setNombre("Juan");
        persona.setUbigeoId(1234);
        persona.setDireccion("Av. Siempreviva 123");
        persona.setAuditUsuarioCreacion("user_crea");


        when(mapper.save(persona)).thenReturn(321);

        // Llamamos al método savePruebaDirecta de personaService con la persona de prueba
        persona.setPersonaNaturalId(321);
        personaService.save(persona);

        log.info(persona.toString());
        // Verificamos que se haya llamado al método save de personaNaturalMapper con la persona de prueba
        verify(mapper, times(1)).save(eq(persona));


    }
}