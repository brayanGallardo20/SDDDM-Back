package pe.gob.minjus.sisarb.model.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
public class Configuracion {

    private String configuracionId;

    @NotEmpty(message = "El concepto es obligatorio")
    @Size( max = 100,message = "La longitud máxima para el concepto es de 100 caracteres")
    private String concepto;

    private Integer sistemaId;

    @NotEmpty(message = "La versión del sistema es obligatorio")
    @Size( max = 20,message = "La longitud máxima para la versión del sistema es de 20 caracteres")
    private String sistemaVersion;

    @NotEmpty(message = "El repositorio es obligatorio")
    @Size( max = 200,message = "La longitud máxima para el repositorio es de 200 caracteres")
    private String repositorio;

    @NotEmpty(message = "Los tipos de documento son obligatorios")
    @Size( max = 100,message = "La longitud máxima para el tipo documento es de 100 caracteres")
    private String tipoDocumento;

    @NotEmpty(message = "Los tipo de imágenes son obligatorios")
    @Size( max = 100,message = "La longitud máxima para el tipo imagen es de 100 caracteres")
    private String tipoImagen;

    @NotNull(message = "La tamaño del documento es obligatorio")
    @Min(value = 0,message =  "El valor del tamaño del documento no puede ser negativo")
    private Integer tamanioDocumento;

    @NotNull(message = "La tamaño de la imagen es obligatorio")
    @Min(value = 0,message =  "El valor del tamaño de la imagen no puede ser negativo")
    private Integer tamanioImagen;

    @NotEmpty(message = "La url del servicio de seguridad es obligatorio")
    @Size( max = 200,message = "La longitud máxima para la url del servicio de seguridad es de 200 caracteres")
    private String webServicioSeguridad;

    private String webServicioPide;
    private String ldapUrl;
    private String ldapDominio;
    private String smtpHost;
    private Integer activo;
    private Integer limiteDocumento;
    private Integer limiteImagen;
    private Integer limiteFila;
    private String rutaLogo;
    private Integer rutaLogoRelativo;
    private Date auditFechaCreacion;

    @NotEmpty(message = "El usuario que modifica el registro es obligatorio")
    @Size( max = 25,message = "La longitud máxima para el usuario que modifica es de 25 caracteres")
    private String auditUsuarioModifica;

    private Date auditFechaModifica;
}
