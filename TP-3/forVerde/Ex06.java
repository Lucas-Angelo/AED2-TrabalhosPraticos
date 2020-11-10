import java.io.*;

public class Ex06 {
	public static void main(String[] args) throws NumberFormatException, Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Fila filaJogadores = new Fila();

		Jogador[] players = preencherJogadores();

		String idInformado = new String();
		do {
			idInformado = in.readLine(); // Qual jogador deseja procurar

			if (!(idInformado.equals("FIM"))) {

				filaJogadores.enfileirar(players[Integer.parseInt(idInformado)]);
				System.out.println(((int) filaJogadores.obterMediaAltura()));

			}

		} while (!(idInformado.equals("FIM")));

		String[] dadosAcao;
		Jogador desenfilierado = new Jogador();

		int i = 0, id;
		int qtd = Integer.parseInt(in.readLine());

		while (i < qtd) {

			String acao = in.readLine();

			if (acao.charAt(0) == 'I') {
				dadosAcao = acao.split(" ", 2);
				id = Integer.parseInt(dadosAcao[1].toString());
				filaJogadores.enfileirar(players[id]);
				System.out.println(((int) filaJogadores.obterMediaAltura()));
			} else if (acao.charAt(0) == 'R') {
				desenfilierado = filaJogadores.desenfileirar();
				System.out.println("(R) " + desenfilierado.getNome());
			}

			i++;
		}

		filaJogadores.mostrar();

	}

	public static int qtdLinhas(ArquivoTextoLeitura leitura) {
		int qtd = 0;
		String linhaLida = new String();
		leitura.abrirArquivo("/tmp/players.csv");

		leitura.ler(); // O cabecalho, tem que pular a primeira linha
		linhaLida = leitura.ler();
		while (linhaLida != null) {
			qtd++;
			linhaLida = leitura.ler();
		}

		leitura.fecharArquivo();

		return qtd; // Retorna a quantidade de jogadores/Quantidade de linhas
	}

	public static Jogador[] preencherJogadores() throws Exception {
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
		int qtdJogadores = qtdLinhas(leitura);

		Jogador atual = new Jogador();
		Jogador[] todosJogadores = new Jogador[qtdJogadores];
		for (int i = 0; i < qtdJogadores; i++)
			todosJogadores[i] = new Jogador();

		leitura.abrirArquivo("/tmp/players.csv");

		leitura.ler(); // Remove o cabecalho
		for (int i = 0; i < qtdJogadores; i++) {

			String[] dadosDaLinha = leitura.ler().split(",", 8); // Dividir os dados da linha

			// Caso necessite de remover os asterisco só tirar o comenário das linhas abaixo
			/*
			 * String nome = dadosDaLinha[1].toString(); char ultima =
			 * nome.charAt(nome.length()-1);
			 * 
			 * if(ultima=='*') { dadosDaLinha[1]=""; for(int z=0; z<nome.length()-1; z++) {
			 * dadosDaLinha[1] += nome.charAt(z); } }
			 */

			atual.setId(Integer.parseInt((dadosDaLinha[0].toString())));
			atual.setNome(dadosDaLinha[1].toString());
			atual.setAltura(Integer.parseInt((dadosDaLinha[2].toString())));
			atual.setPeso(Integer.parseInt((dadosDaLinha[3].toString())));
			atual.setUniversidade(dadosDaLinha[4].toString());
			atual.setAnoNascimento(Integer.parseInt((dadosDaLinha[5].toString())));
			atual.setCidadeNascimento(dadosDaLinha[6].toString());
			atual.setEstadoNascimento(dadosDaLinha[7].toString());

			todosJogadores[i] = atual;
			atual = new Jogador();
		}

		leitura.fecharArquivo();

		return todosJogadores;
	}

}

class Jogador {

	private int id;
	private String nome = new String();
	private int altura;
	private int peso;
	private String universidade = new String();
	private int anoNascimento;
	private String cidadeNascimento = new String();
	private String estadoNascimento = new String();

	public Jogador() {

	}

	public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
			String cidadeNascimento, String estadoNascimento) {
		this.id = id;
		this.nome = nome;
		this.altura = altura;
		this.peso = peso;
		this.universidade = universidade;
		this.anoNascimento = anoNascimento;
		this.cidadeNascimento = cidadeNascimento;
		this.estadoNascimento = estadoNascimento;
	}

	// Início GETS
	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public int getAltura() {
		return this.altura;
	}

	public int getPeso() {
		return this.peso;
	}

	public String getUniversidade() {
		return this.universidade;
	}

	public int getAnoNascimento() {
		return this.anoNascimento;
	}

	public String getCidadeNascimento() {
		return this.cidadeNascimento;
	}

	public String getEstadoNascimento() {
		return this.estadoNascimento;
	}
	// Fim GETS

	// Início SETS
	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}

	public void setAnoNascimento(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public void setEstadoNascimento(String estadoNascimento) {
		this.estadoNascimento = estadoNascimento;
	}
	// Fim SETS

	public Jogador clone() {

		Jogador jogador = new Jogador();
		jogador.id = this.id;
		jogador.nome = this.nome;
		jogador.altura = this.altura;
		jogador.peso = this.peso;
		jogador.universidade = this.universidade;
		jogador.anoNascimento = this.anoNascimento;
		jogador.cidadeNascimento = this.cidadeNascimento;
		jogador.estadoNascimento = this.estadoNascimento;

		return jogador;
	}

	public void imprimir() {

		System.out.printf("[%d ## ", this.id);

		System.out.printf("%s ## ", this.nome);

		System.out.printf("%d ## ", this.altura);

		System.out.printf("%d ## ", this.peso);

		System.out.printf("%d ## ", this.anoNascimento);

		if (this.universidade.trim().length() == 0) { // Se o dado esta vazio
			System.out.printf("nao informado ## ");
		} else {
			System.out.printf("%s ## ", this.universidade);
		}

		if (this.cidadeNascimento.trim().length() == 0) { // Se o dado esta vazio
			System.out.printf("nao informado ## ");
		} else {
			System.out.printf("%s ## ", this.cidadeNascimento);
		}

		if (this.estadoNascimento.trim().length() == 0) { // Se o dado esta vazio
			System.out.printf("nao informado]\n");
		} else {
			System.out.printf("%s]\n", this.estadoNascimento);
		}

	}

}

class Celula {

	Jogador item; // Dados do jogador
	Celula proximo; // Apontar da frente ate atras na fila

	public Celula() { // Sentinela
		this.item = new Jogador();
		proximo = null;
	}

	public Celula(Jogador player) { // Preencher
		this.item = player;
		proximo = null;
	}

}

class Fila {

	private Celula frente;
	private Celula tras;
	private int filaTamanho;

	public Fila() {
		Celula sentinela;

		sentinela = new Celula();
		frente = sentinela;
		tras = sentinela;
		filaTamanho = 0;
	}

	public boolean filaVazia() {

		boolean resp;

		if (frente == tras)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public boolean filaCheia() {
		boolean resp;

		if (filaTamanho == 5)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public void enfileirar(Jogador novo) {

		Celula novaCelula;

		if (!filaCheia()) {

			novaCelula = new Celula(novo);
			tras.proximo = novaCelula;
			tras = novaCelula; // ou: tras = tras.proximo;
			filaTamanho++;

		} else {

			desenfileirar();

			novaCelula = new Celula(novo);
			tras.proximo = novaCelula;
			tras = novaCelula; // ou: tras = tras.proximo;
			filaTamanho++;

		}

	}

	public Jogador desenfileirar() {

		Celula aux;

		aux = frente.proximo; // Na frente da sentinela
		frente.proximo = aux.proximo; // Sentinela pula uma celula no apontamento
		aux.proximo = null; // Anula celula desenfileirada

		if (aux == tras)
			tras = frente;

		filaTamanho--;

		return (aux.item);
	}

	public void mostrar() {

		int cont = 0;
		if (!filaVazia()) {
			Celula aux;

			aux = frente.proximo;
			while (aux != null) {

				System.out.print("[" + cont + "] ## ");

				System.out.printf("%d ## ", aux.item.getId());

				System.out.printf("%s ## ", aux.item.getNome());

				System.out.printf("%d ## ", aux.item.getAltura());

				System.out.printf("%d ## ", aux.item.getPeso());

				System.out.printf("%d ## ", aux.item.getAnoNascimento());

				if (aux.item.getUniversidade().trim().length() == 0) { // Se o dado esta vazio
					System.out.printf("nao informado ## ");
				} else {
					System.out.printf("%s ## ", aux.item.getUniversidade());
				}

				if (aux.item.getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
					System.out.printf("nao informado ## ");
				} else {
					System.out.printf("%s ## ", aux.item.getCidadeNascimento());
				}

				if (aux.item.getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
					System.out.printf("nao informado ## \n");
				} else {
					System.out.printf("%s ## \n", aux.item.getEstadoNascimento());
				}

				cont++;
				aux = aux.proximo;
			}

		}
	}

	public double obterMediaAltura() {
		double media;

		int cont = 0;
		double somatorio = 0.0;
		if (!filaVazia()) {
			Celula aux;

			aux = frente.proximo;
			while (aux != null) {

				somatorio += aux.item.getAltura();

				cont++;
				aux = aux.proximo;
			}
		}
		media = somatorio / cont;

		return Math.round(media);
	}

}

class ArquivoTextoLeitura {

	private BufferedReader entrada;

	public void abrirArquivo(String nomeArquivo) {

		try {
			entrada = new BufferedReader(new FileReader(nomeArquivo));
		} catch (FileNotFoundException excecao) {
			System.out.println("Arquivo nÃ£o encontrado");
		}
	}

	public void fecharArquivo() {

		try {
			entrada.close();
		} catch (IOException excecao) {
			System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
		}
	}

	public String ler() {

		String textoEntrada;

		try {
			textoEntrada = entrada.readLine();
		} catch (EOFException excecao) { // ExceÃ§Ã£o de final de arquivo.
			return null;
		} catch (IOException excecao) {
			System.out.println("Erro de leitura: " + excecao);
			return null;
		}
		return textoEntrada;
	}
}


