package pe.gob.minjus.sisarb.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta<T> {
    T data;
    String mensaje;
    Integer totalRegistros;
    String status; 
}
