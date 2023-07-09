package pe.gob.minjus.sisarb.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonWsResponse<T> {

    private String idOperacion;
    private String mensajeOperacion;
    private String tipoOperacion;
    private T rptaLista;
}
