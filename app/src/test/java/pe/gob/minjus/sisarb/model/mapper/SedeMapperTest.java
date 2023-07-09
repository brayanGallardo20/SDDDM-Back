package pe.gob.minjus.sisarb.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.mapper.datos.SedeDataTest;
import pe.gob.minjus.sisarb.model.request.SedeBusquedaRequest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
class SedeMapperTest {

    @Autowired
    SedeMapper sedeMapper;

    @DisplayName("Buscar por Id")
    @Test
    void findById() {
        //given
        int idSede = 1;
        //When
        Sede sede = sedeMapper.findById(idSede);
        //Then
        assertThat(sede.getSedeId()).isEqualTo(idSede);
    }

    @DisplayName("Listar con paginado")
    @Test
    void listPaginated() {
        //given
        SedeBusquedaRequest request = new SedeBusquedaRequest();
        request.setFilaInicio(0);
        request.setCantidadPorPagina(10);
        //when
        List<Sede> listadoPaginated = sedeMapper.listPaginated(request);
        log.info(listadoPaginated.toString());
        //then
        assertThat(!(listadoPaginated.isEmpty()));
    }

    @DisplayName("Calcular total de paginado")
    @Test
    void listPaginatedTotal() {
        //given
        SedeBusquedaRequest request = new SedeBusquedaRequest();
        request.setFilaInicio(0);
        request.setCantidadPorPagina(10);
        //when
        Integer total = sedeMapper.listPaginatedTotal(request);
        log.info("Total: "+total);
        //then
        assertThat(total>0);
    }

   /* @DisplayName("Registrar Sede")
    @Test
    void save() {
        Sede sede = SedeDataTest.dataSedeSave();
        //when
        int save =   sedeMapper.save(sede);
        //then
        assertThat( save).isEqualTo(1);
    }

    @DisplayName("Modificar Sede")
    @Test
    void update() {
        //given
        Sede sede = SedeDataTest.dataSedeUpdate();
        //when
        int update =   sedeMapper.update(sede);
        //then
        assertThat( update).isEqualTo(1);
    }*/

    @Test
    void deleteById() {
        //given
        int deleteId = 1;
        //when
        Integer deleted = sedeMapper.deleteById(deleteId);
        //then
        assertThat( deleted).isEqualTo(1);;
    }

    @DisplayName("Listar combo sedes")
    @Test
    void listChoose() {
        //given
        List<Sede> comboList;
        //when
        comboList = sedeMapper.listChoose();
        //then
        comboList.stream().map( e-> {
            log.info(e.toString());
            return e;
        });
        assertThat(comboList.size()>0);
    }
}