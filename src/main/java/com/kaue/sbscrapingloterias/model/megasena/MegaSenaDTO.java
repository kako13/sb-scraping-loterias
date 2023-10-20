package com.kaue.sbscrapingloterias.model.megasena;

import com.kaue.sbscrapingloterias.model.MunicipioUFGanhadoresItem;
import com.kaue.sbscrapingloterias.model.RateioPremioItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MegaSenaDTO{
	private String observacao;
	private Integer numero;
	private String valorAcumuladoConcurso_0_5;
	private Integer numeroConcursoAnterior;
	private List<String> listaDezenas;
	private String valorEstimadoProximoConcurso;
	private String premiacaoContingencia;
	private String tipoJogo;
	private Object listaResultadoEquipeEsportiva;
	private String valorTotalPremioFaixaUm;
	private String nomeMunicipioUFSorteio;
	private Integer numeroConcursoFinal_0_5;
	private String id;
	private Integer indicadorConcursoEspecial;
	private String nomeTimeCoracaoMesSorte;
	private List<MunicipioUFGanhadoresItem> listaMunicipioUFGanhadores;
	private Boolean acumulado;
	private String dataApuracao;
	private Integer numeroConcursoProximo;
	private List<RateioPremioItem> listaRateioPremio;
	private Boolean ultimoConcurso;
	private String dataProximoConcurso;
	private String valorAcumuladoConcursoEspecial;
	private String valorAcumuladoProximoConcurso;
	private String valorArrecadado;
	private Object listaDezenasSegundoSorteio;
	private String valorSaldoReservaGarantidora;
	private Integer tipoPublicacao;
	private String localSorteio;
	private Boolean exibirDetalhamentoPorCidade;
	private Integer numeroJogo;
	private List<String> dezenasSorteadasOrdemSorteio;

	@Override
 	public String toString(){
		return 
			"MegaSenaDTO{" + 
			"observacao = '" + observacao + '\'' + 
			",numero = '" + numero + '\'' + 
			",valorAcumuladoConcurso_0_5 = '" + valorAcumuladoConcurso_0_5 + '\'' +
			",numeroConcursoAnterior = '" + numeroConcursoAnterior + '\'' + 
			",listaDezenas = '" + listaDezenas + '\'' + 
			",valorEstimadoProximoConcurso = '" + valorEstimadoProximoConcurso + '\'' + 
			",premiacaoContingencia = '" + premiacaoContingencia + '\'' + 
			",tipoJogo = '" + tipoJogo + '\'' + 
			",listaResultadoEquipeEsportiva = '" + listaResultadoEquipeEsportiva + '\'' + 
			",valorTotalPremioFaixaUm = '" + valorTotalPremioFaixaUm + '\'' + 
			",nomeMunicipioUFSorteio = '" + nomeMunicipioUFSorteio + '\'' + 
			",numeroConcursoFinal_0_5 = '" + numeroConcursoFinal_0_5 + '\'' +
			",id = '" + id + '\'' + 
			",indicadorConcursoEspecial = '" + indicadorConcursoEspecial + '\'' + 
			",nomeTimeCoracaoMesSorte = '" + nomeTimeCoracaoMesSorte + '\'' + 
			",listaMunicipioUFGanhadores = '" + listaMunicipioUFGanhadores + '\'' + 
			",acumulado = '" + acumulado + '\'' + 
			",dataApuracao = '" + dataApuracao + '\'' + 
			",numeroConcursoProximo = '" + numeroConcursoProximo + '\'' + 
			",listaRateioPremio = '" + listaRateioPremio + '\'' + 
			",ultimoConcurso = '" + ultimoConcurso + '\'' + 
			",dataProximoConcurso = '" + dataProximoConcurso + '\'' + 
			",valorAcumuladoConcursoEspecial = '" + valorAcumuladoConcursoEspecial + '\'' + 
			",valorAcumuladoProximoConcurso = '" + valorAcumuladoProximoConcurso + '\'' + 
			",valorArrecadado = '" + valorArrecadado + '\'' + 
			",listaDezenasSegundoSorteio = '" + listaDezenasSegundoSorteio + '\'' + 
			",valorSaldoReservaGarantidora = '" + valorSaldoReservaGarantidora + '\'' + 
			",tipoPublicacao = '" + tipoPublicacao + '\'' + 
			",localSorteio = '" + localSorteio + '\'' + 
			",exibirDetalhamentoPorCidade = '" + exibirDetalhamentoPorCidade + '\'' + 
			",numeroJogo = '" + numeroJogo + '\'' + 
			",dezenasSorteadasOrdemSorteio = '" + dezenasSorteadasOrdemSorteio + '\'' + 
			"}";
		}
}