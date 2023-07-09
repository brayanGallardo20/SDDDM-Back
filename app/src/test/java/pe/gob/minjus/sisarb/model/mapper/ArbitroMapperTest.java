package pe.gob.minjus.sisarb.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.domain.GenericDomain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ArbitroMapperTest {

    @Autowired
    ArbitroMapper mapper;

    @Test
    void findById() {
        //Given
        Integer id = 42;

        //when
        Arbitro a = mapper.findById(id);
        log.info(a.toString());
        //then
        assertThat(id).isEqualTo(a.getArbitroId());
    }


    @DisplayName("Registrar Arbitro")
    @Test
    void save() {
        //given
        Arbitro a = Arbitro.builder()
                .arbitroId(null)
                .personaId(95)
                .tipoArbitroId(321)
                .entidadFinancieraId(1)
                .nroCuenta("235235235")
                .nroCuentaCci("456456456")
                .paginaWeb("http://ssss.com")
                .habilitado(1)
                .fallecido(0)
                .profesion("Arquitecto")
                .observacion("Una obs")
                .build();

        a.setAuditUsuarioCreacion("USER_EJEMPLO");
        a.setAuditFechaCreacion(null);
                 a.setAuditUsuarioModifica(null);
                 a.setAuditFechaModifica(null);
                 a.setAuditFechaModificaFormat(null);
                 a.setAuditFechaModificaFormat(null);
        log.info(a.toString());
        //when
        int save =mapper.save(a);
        //then
        assertThat( save).isEqualTo(1);
    }

    @Test
    void update() {
        //given
        Arbitro au = Arbitro.builder()
                .arbitroId(42)
                .personaId(95)
                .tipoArbitroId(321)
                .entidadFinancieraId(1)
                .nroCuenta("11111111")
                .nroCuentaCci("2222222")
                .paginaWeb("http://3s3s3.com")
                .habilitado(1)
                .fallecido(1)
                .profesion("Arquitecto Modi")
                .observacion("Cualquier obs")
                .build();
        au.setAuditUsuarioModifica("USER_MODIFICA");

        //when
        int upt = mapper.update(au);

        //then
        assertThat(upt).isEqualTo(1);

    }

    @Test
    void deleteById() {
        //GIVEN
        Integer idDelete = 42;
        //when
        int updDelete = mapper.deleteById(idDelete);
        //then
        assertThat(updDelete).isEqualTo(1);

    }
}