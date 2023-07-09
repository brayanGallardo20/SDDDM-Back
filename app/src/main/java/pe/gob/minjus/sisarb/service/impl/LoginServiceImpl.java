package pe.gob.minjus.sisarb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.psm.ws.domain.Menu;
import pe.gob.minjus.psm.ws.domain.ResponseSecurity;
import pe.gob.minjus.psm.ws.domain.Usuario;
import pe.gob.minjus.sisarb.controller.response.MenuResponse;
import pe.gob.minjus.sisarb.model.domain.MenuRol;
import pe.gob.minjus.sisarb.model.domain.Rol;
import pe.gob.minjus.sisarb.model.domain.RolMenu;
import pe.gob.minjus.sisarb.model.domain.UsuarioLogin;
import pe.gob.minjus.sisarb.model.request.LoginRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ConfiguracionService;
import pe.gob.minjus.sisarb.service.LoginService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.SeguridadS;

import java.util.ArrayList;
import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {


  @Value("${sisarb.entorno.configuracion}")
  private String entornoConfiguracion;

	@Value("${servicio.seguridad.mac}")
	public String servicioSeguridadMac;

	@Autowired
	ConfiguracionService configuracionService;
	
    SeguridadS seguridadS=new SeguridadS();

	@Override
	public ResponseEntity<Respuesta<UsuarioLogin>> entrarSistema(LoginRequest request) {
		
        int idAplicacion=Constantes.ID_APLICACION_SERVICIO_SEGURIDAD;
        String ip=Constantes.IP_SERVICIO_SEGURIDAD;
        
        String urlSeguridad=configuracionService.getWebServiceSeguridad(entornoConfiguracion);
        String mac=servicioSeguridadMac;
        
        ResponseSecurity<Usuario> responseSeguridad=seguridadS.conectarServicioSeguridad(idAplicacion, request.getUsuario(), request.getClave(),ip, mac,urlSeguridad);
		
        Respuesta<UsuarioLogin> response = new Respuesta<>();
        UsuarioLogin usuarioLogin=new UsuarioLogin();
        List<RolMenu> listRolMenu= new ArrayList<>();
        List<MenuRol> listMenu;
        List<Rol> listRol= new ArrayList<>();
		try {
			boolean getSuccess=  responseSeguridad.getSuccess();
	        if(getSuccess){
	        	JSONObject responseSeguridadJson = new JSONObject(responseSeguridad.getJson());
	        	int idUsuario=(Integer)responseSeguridadJson.get("codigo");
	        	usuarioLogin.setNombre(responseSeguridad.getObject().getNombres());
	        	usuarioLogin.setApePat(responseSeguridad.getObject().getApePaterno());
	        	usuarioLogin.setApeMat(responseSeguridad.getObject().getApeMaterno());
	        	usuarioLogin.setIdUsuario(idUsuario);


	        	
	            for(int index=0; index<responseSeguridad.getObject().getPerfiles().size(); index++){
	            	RolMenu rolMenu= new RolMenu();
	            	Rol rol= new Rol();
	            	
	            	rol.setEstado(true);
	            	rol.setIdRol(responseSeguridad.getObject().getPerfiles().get(index).getPerfilId());
	            	rol.setNombre(responseSeguridad.getObject().getPerfiles().get(index).getNombre());
	            	
	            	rolMenu.setEstado(true);
	            	rolMenu.setIdRol(responseSeguridad.getObject().getPerfiles().get(index).getPerfilId());
	            	rolMenu.setNombre(responseSeguridad.getObject().getPerfiles().get(index).getNombre());
	            	
	            	if(responseSeguridad.getObject().getPerfiles().get(index).getPerfilId().equals(responseSeguridad.getObject().getPerfil().getPerfilId())) {
	            		rolMenu.setPrincipal(true);	
		            	rol.setPrincipal(true);
	            	}else {
	            		rolMenu.setPrincipal(false);
		            	rol.setPrincipal(false);
	            	}
	            	//ROL:
	            	listRol.add(rol);
	            	//MENU:
		            listMenu=obtenerMenu(idAplicacion,responseSeguridad.getObject().getPerfiles().get(index).getPerfilId(),request.getUsuario(),urlSeguridad);
		            rolMenu.setMenu(listMenu);
		            listRolMenu.add(rolMenu);
	            }                      
	            usuarioLogin.setRolMenu(listRolMenu);
	            usuarioLogin.setRol(listRol);
				usuarioLogin.setUsuario(request.getUsuario());

	            response.setTotalRegistros(1);
	            response.setStatus("200");
				 response.setData(usuarioLogin);
			     response.setMensaje(responseSeguridad.getMessage());
		         return new ResponseEntity<>(response, HttpStatus.OK);	
	        }else {
	            response.setTotalRegistros(0);
				 response.setData(null);
				 response.setStatus("401");
			     response.setMensaje(responseSeguridad.getMessage());
		         return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);	
	        }
				
			
		}catch (Exception e) {
            response.setMensaje(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public List<MenuRol> obtenerMenu(Integer aplicacionId, Integer rolId, String usuario,String webServicioUrl){
		ArrayList<String> routerLink;
		List<MenuRol> listMenu=new ArrayList<>();
		List<MenuResponse> listSubMenu;
		
        String urlSeguridad=webServicioUrl;
		
        ResponseSecurity<Menu> responseMenu = seguridadS.obtenerMenuAnidado(aplicacionId,rolId,usuario,urlSeguridad);
		boolean getSuccess = responseMenu.getSuccess();

        if(getSuccess){
            for(int index=0; index<responseMenu.getList().size(); index++){
            	MenuRol menuRol=new MenuRol(); 
            	routerLink= new ArrayList<>();
                Menu menu = responseMenu.getList().get(index);
            	listSubMenu=obtenerSubMenu(menu);
            	if(menu.getUrl().equals("")) {
            		routerLink.add(null);
            	}else {
            		routerLink.add(menu.getUrl());
            	}
                menuRol.setIcon(menu.getIcon());
                menuRol.setId(menu.getMenuId());
                menuRol.setIdRol(rolId);
                menuRol.setLabel(menu.getNombre());
                menuRol.setRouterLink(routerLink);
                if(!listSubMenu.isEmpty()) {
                    menuRol.setItems(obtenerSubMenu(menu));	
                }
                listMenu.add(menuRol);
            }                
        }				
		return listMenu;		
	}
	
	public List<MenuResponse> obtenerSubMenu(Menu menuPadre) {
		ArrayList<String> routerLink;
		List<MenuResponse> listMenuResponses= new ArrayList<>();
		List<MenuResponse> listSubMenu;
		
        if(!menuPadre.getSubmenus().isEmpty()){            

            for(int index=0; index<menuPadre.getSubmenus().size(); index++){
            	MenuResponse menuResponse= new MenuResponse();
                Menu menu = menuPadre.getSubmenus().get(index);
                routerLink= new ArrayList<>();
            	if(menu.getUrl().equals("")) {
            		routerLink.add(null);
            	}else {
            		routerLink.add(menu.getUrl());
            	}                
                listSubMenu=obtenerSubMenu(menu);
                menuResponse.setIcon(menu.getIcon());
                menuResponse.setId(menu.getMenuId());
                menuResponse.setLabel(menu.getNombre());
                menuResponse.setRouterLink(routerLink);
                if(!listSubMenu.isEmpty()) {
                    menuResponse.setItems(listSubMenu);                	
                }
                obtenerSubMenu(menu);
                listMenuResponses.add(menuResponse);
            }
        }
        return listMenuResponses;
	}

		
}
