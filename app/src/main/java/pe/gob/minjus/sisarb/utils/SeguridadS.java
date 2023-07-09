package pe.gob.minjus.sisarb.utils;

import pe.gob.minjus.psm.ws.SecurityClientWS;
import pe.gob.minjus.psm.ws.domain.Menu;
import pe.gob.minjus.psm.ws.domain.ResponseSecurity;
import pe.gob.minjus.psm.ws.domain.Usuario;

public class SeguridadS {
    
    public ResponseSecurity<Usuario> conectarServicioSeguridad(Integer aplicacionId, String usuario,String clave, String ip, String mac,String urlSecurity){
        	return new SecurityClientWS(urlSecurity).login(aplicacionId, usuario, clave, ip, mac);
    }
    
    public ResponseSecurity<Menu> obtenerMenu(Integer aplicacionId, Integer rolId, String usuario,String urlSecurity){
        SecurityClientWS client=new SecurityClientWS(urlSecurity);
    	return client.obtenerMenus(aplicacionId, rolId, usuario);
}
    
    public ResponseSecurity<Menu> obtenerMenuAnidado(Integer aplicacionId, Integer rolId, String usuario,String urlSecurity){
        SecurityClientWS client=new SecurityClientWS(urlSecurity);
    	return client.obtenerMenusAnidado(aplicacionId, rolId, usuario);
}
    
    
    

}
