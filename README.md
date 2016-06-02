# compilador
<p>Trabalho de implementação do compilador para matéria INE5622 Introdução a Compiladores 2016.1</p>
<p>Raphael Martins - Thiago Mohr</p>

IV.5 – Especificação Léxica da LSI-161

* Identificadores
* caracteres válidos : letras, dígitos, “@” e “_”
* regras de formação:
	* começa com letra ou “@”
	* não pode terminar com “_” e “@”
	* não possui caracteres especiais “_” e “@”consecutivos (ou seja, não possui “@@”, “__”, “@_” e “_@”)
	* não possui limite de tamanho

* Palavras reservadas
	* casos especiais de identificadores
		* programa, var, caracter, cadeia, procedimento, inicio, fim, inteiro, booleano, funcao, se, entao,
senao, leia, escreva, ou, e, nao, falso, verdadeiro, de, faca, real, vetor, enquanto OBS.: lista a ser atualizada quando da esp.
sintática da LSI161
	
* Constantes numéricas
	* Inteiras e reais sem sinal (com e sem parte exponencial)
		* Reais na forma .25, 2.5 e 25.
		* Expoente composto por: “E” ou “e”, sinal opcional (“+” ou “-” ) e pelo menos 2 digitos: Exemplos: 2.5e-10, 3E10, 123.e+99
		* fornecer tokens distintos para constantes inteiras e para constantes reais 

* Constantes literais
	* Usar ‘ (caracter aspa) como delimitador
	* Sem limite de tamanho
	* No meio de um literal, o caracter ‘ (aspa) deve ser representado por duas aspas simples justapostas Ex. ‘pato d’’agua’
	* Permitir continuação em outra linha
	* Literal não fechado – erro léxico
	* Literais podem conter quaisquer caracteres (mesmo os caracteres inválidos para outros fins)

* Comentários de linha
	* Começa com “#”
	* Termina no final da linha
	
* Comentários de bloco
	* notação: qualquer sequência de caracteres entre os delimitadores /* e */ - pode conter "*\*" e “/”, mas não “*/”.
	* não analisar sequências de caracteres internas
	* sem limite de tamanho
	* comentário não fechado = erro léxico

* Símbolos especiais (lista a ser atualizada de acordo com a especificação sintática)
	* Token específico para cada símbolo
		* Simples: ; , . > < = ( ) [ ] + - * / :
		* Duplos: := .. <> <= 
