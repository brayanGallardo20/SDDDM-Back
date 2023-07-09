package pe.gob.minjus.sisarb;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
 class SisarbApplicationTests {

    Calculadora calculadora = new Calculadora();

    @Test
    @DisplayName("Suma de valores ValorA y ValorB")
     void sumaDeValores() {
        //given
        int valorA = 2;
        int valorB = 3;

        //When
        int expectativa = calculadora.sumar(valorA,valorB);

        //Then
        int resultadoEsperado = 5;

        assertThat(expectativa).isEqualTo(resultadoEsperado);
    }

   static class Calculadora{
        int sumar(int valorA,int valorB){
            return valorA+valorB;
        }
    }
}
