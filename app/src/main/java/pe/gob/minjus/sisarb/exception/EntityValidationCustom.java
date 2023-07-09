package pe.gob.minjus.sisarb.exception;

public class EntityValidationCustom extends  RuntimeException{
    public EntityValidationCustom(String mensaje){
        super(mensaje);
    }
}
