package pe.gob.minjus.sisarb.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ModelExceptionResponse {

	private String mensaje;
    private String detalles;
    private LocalDateTime fechaCompleta;
}
