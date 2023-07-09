package pe.gob.minjus.sisarb.controller.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {
	
	private int id;
	private String label;
	private String icon;
	private ArrayList<String> routerLink;
	private List<MenuResponse> items;
	private String url;
	private boolean preventExact;
	

}
