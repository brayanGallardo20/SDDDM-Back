package pe.gob.minjus.sisarb.model.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Auditoria{


    private String tabla;
    private String codigo;
    private BigDecimal movimiento;
    private String auditUsuario;
    private Date auditFecha;
    private String auditTipo;
    private String valorOriginal;
    private String valorFinal;

    //Campos formateados
    private String auditFechaFormat;
    private String auditFechaCompletaFormat;

    public Auditoria(String tabla, String codigo, String auditUsuario, String auditTipo, String valorOriginal, String valorFinal) {
        this.tabla = tabla;
        this.codigo = codigo;
        this.auditUsuario = auditUsuario;
        this.auditTipo = auditTipo;
        this.valorOriginal = valorOriginal;
        this.valorFinal = valorFinal;
    }


}
