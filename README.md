![Readme Card](https://github-readme-stats.vercel.app/api/pin?username=kako13&repo=sb-scraping-loterias&show_icons=true&theme=codeSTACKr&hide_border=true&bg_color=00000000)
####

<summary><i>Scraping Loterias CAIXA</i></summary>

Batch em Spring Batch para a raspagem de dados (scraping) de sorteios das loterias CAIXA:


- [**Mega Sena**](https://servicebus2.caixa.gov.br/portaldeloterias/api/megasena/)

- [**Lotofácil**](https://servicebus2.caixa.gov.br/portaldeloterias/api/lotofacil/)



A aplicação realiza requisições nas web APIs das loterias acima e grava os retornos em arquivos JSON. Desta forma é possível
aproveitar os dados obtidos em importar para outro ambiente que utilize o `pandas` por exemplo.


_As informações a serem obtidas são disponibilizadas por meio de uma web API pública, Singletown Resource. Então é 
importante utilizá-la com cautela, a fim de evitar a indisponibilidade do serviço. Utilizei um range de apenas 10 sorteios,
para não exagerar._
