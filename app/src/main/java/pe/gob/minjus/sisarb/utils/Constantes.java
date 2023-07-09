package pe.gob.minjus.sisarb.utils;



public class Constantes {
    private Constantes() {
    }

    public static final String MSJ_CRUD_LISTADO="Se ejecutó la consulta correctamente";
    public static final String MSJ_CRUD_REGISTRO="Se guardó el registro satisfactoriamente";
    public static final String MSJ_CRUD_MODIFICAR="Se actualizó el registro satisfactoriamente";
    public static final String MSJ_CRUD_ELIMINAR="Se eliminó el registro correctamente";

    public static final String MSJ_VALIDACION_USUARIO_REGISTRO="El usuario de creación es obligatorio";
    public static final String MSJ_VALIDACION_USUARIO_MODIFICA="El usuario que modifica es obligatorio";
    public static final String MSJ_VALIDACION_USUARIO_ELIMINA="El usuario que elimina es obligatorio";
    public static final String MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID="No existe el registro con ID: ";
    public static final String MSJ_VALIDACION_SOLO_CARACTERES_ALFANUMERICOS="No se permiten caracteres especiales ";

    public static final String MSJ_VALIDACION_REGISTRO_REPETIDO="Registro repetido";
    public static final String MSJ_VALIDACION_CANTIDAD_DIRECTIVO="No se puede ingresar mas de un Directivo";
    public static final String MSJ_VALIDACION_CANTIDAD_DIRECTIVO_GLOBAL="El directivo ya se encuentra registrado en una institución.";
    public static final String MSJ_VALIDACION_RUC_EXISTE="El Ruc ya se encuentro registrado";
    public static final String MSJ_VALIDACION_REGISTRO_RELACIONADO="No se puede eliminar, el registro tiene relación con otro proceso";

    public static final String MSJ_VALIDACION_CONFIGURACION = "Comuníquese con el área de tecnología, no se encontró la configuración: ";
    public static final String MSJ_REGISTRO_ENCONTRADOS="Se encontraron registros";
    public static final String MSJ_REGISTRO_NO_ENCONTRADOS="No se encontraron registros";
    public static final String MSJ_CAMPO_REQUERIDO="Campo requerido";

    public static final String MSJ_DATO_NO_ENCONTRADO_EN_PIDE ="El dato ingresado no existe en la PIDE";
    public static final String MSJ_PIDE_NO_DISPONIBLE="El Servicio de la PIDE no se encuentra disponible";

    public static final int ID_UNO=1;
    public static final int ID_APLICACION_SERVICIO_SEGURIDAD=104;
    public static final String IP_SERVICIO_SEGURIDAD="E8:EA:B5:A1";

    public static final String TABLE_MAE_ETAPA_ARBITRAL="MAE_ETAPA_ARBITRAL";
    public static final String TABLE_MAE_SUB_ETAPA_ARBITRAL="MAE_SUB_ETAPA_ARBITRAL";
    public static final String TABLE_MAE_TIPO_DOCUMENTO="MAE_TIPO_DOCUMENTO";

    public static final String TABLE_GEN_PARAMETRO_CONFIGURACION="GEN_PARAMETRO_CONFIGURACION";

    public static final String TABLE_MAE_CONFIGURACION="MAE_CONFIGURACION";
    public static final String TABLE_MAE_ESPECIALIDAD="MAE_ESPECIALIDAD";
    public static final String TABLE_MAE_ESPECIALIZACION="MAE_ESPECIALIZACION";
    public static final String TABLE_MAE_MATERIA_ARBITRAL="MAE_MATERIA_ARBITRAL";
    public static final String TABLE_TRS_SEDE="TRS_SEDE";
    public static final String TRS_INSTITUCION_PERSONA="TRS_INSTITUCION_PERSONA";
    public static final String TABLE_TRS_SEDE_ESPECIALIDAD="TRS_SEDE_ESPECIALIDAD";
    public static final String TABLE_TRS_SEDE_MATERIA_ARBITRAL="TRS_SEDE_MATERIA_ARBITRAL";
    public static final String TABLE_TRS_SEDE_PERSONA="TRS_SEDE_PERSONA";
    public static final String TABLE_TRS_PERSONA="TRS_PERSONA";

    public static final String TABLE_TRS_PERSONA_ESPECIALIZACION="TRS_PERSONA_ESPECIALIZACION";
    public static final String TABLE_TRS_ARBITRO="TRS_ARBITRO";
    public static final String TABLE_TRS_INSTITUCION_PERSONA="TRS_INSTITUCION_PERSONA";
    public static final Integer DIRECTIVO = 102;

    public static final Integer PERSONA_CARGO_ID_REPRESENTANTE= 101;
    public static final Integer PERSONA_CARGO_ID_DIRECTIVO= 102;
    public static final Integer PERSONA_CARGO_ID_COORDINADOR= 103;
    public static final Integer PERSONA_CARGO_ID_SECRETARIO= 104;
    public static final Integer PERSONA_CARGO_ID_ARBITRO= 105;
}
