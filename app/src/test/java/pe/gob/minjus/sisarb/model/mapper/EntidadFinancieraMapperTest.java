package pe.gob.minjus.sisarb.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.gob.minjus.sisarb.model.domain.EntidadFinanciera;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
class EntidadFinancieraMapperTest {

    @Autowired
    EntidadFinancieraMapper entidadFinancieraMapper;

    @DisplayName("Listar entidad financiera")
    @Test
    void listChoose(){
        //Given
        int sizeBase  =0;
        //when
        List<EntidadFinanciera> list = entidadFinancieraMapper.listChoose();
        list.forEach( (e)->log.info(e.toString()) );
        //Then
        assertThat(list.size()>sizeBase);

    }
}