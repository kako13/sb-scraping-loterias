package com.kaue.sbscrapingloterias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RateioPremioItem {
	private String descricaoFaixa;
	private Integer numeroDeGanhadores;
	private String valorPremio;
	private Integer faixa;

	@Override
 	public String toString(){
		return 
			"RateioPremioItem{" +
			"descricaoFaixa = '" + descricaoFaixa + '\'' + 
			",numeroDeGanhadores = '" + numeroDeGanhadores + '\'' + 
			",valorPremio = '" + valorPremio + '\'' + 
			",faixa = '" + faixa + '\'' + 
			"}";
		}
}
