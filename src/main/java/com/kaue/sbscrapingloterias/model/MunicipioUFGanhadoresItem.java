package com.kaue.sbscrapingloterias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MunicipioUFGanhadoresItem {
	private String uf;
	private Integer posicao;
	private String municipio;
	private Integer ganhadores;
	private String serie;
	private String nomeFatansiaUL;

	@Override
 	public String toString(){
		return 
			"ListaMunicipioUFGanhadoresItem{" + 
			"uf = '" + uf + '\'' + 
			",posicao = '" + posicao + '\'' + 
			",municipio = '" + municipio + '\'' + 
			",ganhadores = '" + ganhadores + '\'' + 
			",serie = '" + serie + '\'' + 
			",nomeFatansiaUL = '" + nomeFatansiaUL + '\'' + 
			"}";
		}
}
