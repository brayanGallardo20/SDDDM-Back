package pe.gob.minjus.sisarb.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.gob.minjus.sisarb.model.domain.EntidadFinanciera;
import pe.gob.minjus.sisarb.model.domain.Maestra;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MaestraMapperTest {

    @Autowired
    MaestraMapper maestraMapper;

    @Test
    void listChoose() {
        //Given
        int sizeBase = 0;
        Maestra request = new Maestra();
        request.setTablaMaestra("MAE_TIPO_ARBITRO");
        //when
        List<Maestra> list = maestraMapper.listChoose(request);
        list.forEach( (e)->log.info(e.toString()) );
        //Then
        assertThat(list.size()>sizeBase);
    }
}