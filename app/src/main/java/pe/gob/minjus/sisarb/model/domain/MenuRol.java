package pe.gob.minjus.sisarb.model.domain;

import lombok.*;
import pe.gob.minjus.sisarb.controller.response.MenuResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuRol {
	private int id;
	private int idRol;
	private String label;
	private String icon;
	private ArrayList<String> routerLink;
	private List<MenuResponse> items;
	private String url;
	private boolean preventExact;
	
	
	
}
