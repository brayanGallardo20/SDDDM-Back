package pe.gob.minjus.sisarb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.model.domain.Usuario;
import pe.gob.minjus.sisarb.model.mapper.LoginMapper;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper mapper;

	@Override
	public ResponseEntity<Respuesta<Usuario>> entrarSistema(Usuario request) {		

		Respuesta<Usuario> response = new Respuesta<>();
		Usuario usuario = mapper.loginSistema(request);

		try {
			response.setTotalRegistros(1);
			response.setStatus("200");
			response.setData(usuario);
			response.setMensaje("");
			return new ResponseEntity<>(response, HttpStatus.OK);	

		}catch (Exception e) {
			response.setMensaje(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*
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
	 */

}
