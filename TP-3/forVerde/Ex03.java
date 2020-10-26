import java.io.*;

public class Ex03 {
	public static void main(String[] args) throws NumberFormatException, Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		int qtdJogadores = qtdLinhas(leitura);

		Lista listaTodosJogadores = preencherJogadores(leitura, qtdJogadores); // Lista que salva todos os jogadores
		Lista listaJogadores = new Lista(qtdJogadores); // Lista preenchida com alguns jogadores

		String idInformado = new String();
		do {
			idInformado = in.readLine(); // Qual jogador deseja procurar

			if (!(idInformado.equals("FIM"))) {

				// Captura da lista que possui todos os jogadores, e insere ele na outra lista
				listaJogadores.inserirFim(listaTodosJogadores.getJogador(Integer.parseInt(idInformado)));

			}

		} while (!(idInformado.equals("FIM")));

		String[] dadosAcao;

		int i = 0;
		int id, posicao;

		int qtd = Integer.parseInt(in.readLine());

		int removidos = 0;
		Jogador removido[] = new Jogador[qtd];
		for (int z = 0; z < qtd; z++)
			removido[z] = new Jogador();

		while (i < qtd) {

			String acao = in.readLine();

			if (acao.charAt(0) == 'I' && acao.charAt(1) == 'I') {
				dadosAcao = acao.split(" ", 2);
				id = Integer.parseInt(dadosAcao[1].toString());
				listaJogadores.inserirInicio(listaTodosJogadores.getJogador(id));
			} else if (acao.charAt(0) == 'I' && acao.charAt(1) == '*') {
				dadosAcao = acao.split(" ", 3);
				id = Integer.parseInt(dadosAcao[2].toString());
				posicao = Integer.parseInt(dadosAcao[1].toString());
				listaJogadores.inserir(listaTodosJogadores.getJogador(id), posicao);
			} else if (acao.charAt(0) == 'I' && acao.charAt(1) == 'F') {
				dadosAcao = acao.split(" ", 2);
				id = Integer.parseInt(dadosAcao[1].toString());
				listaJogadores.inserirFim(listaTodosJogadores.getJogador(id));
			} else if (acao.charAt(0) == 'R' && acao.charAt(1) == 'I') {
				removido[removidos] = listaJogadores.removerInicio();
			} else if (acao.charAt(0) == 'R' && acao.charAt(1) == '*') {
				dadosAcao = acao.split(" ", 2);
				posicao = Integer.parseInt(dadosAcao[1].toString());
				removido[removidos] = listaJogadores.remover(posicao);
			} else if (acao.charAt(0) == 'R' && acao.charAt(1) == 'F') {
				removido[removidos] = listaJogadores.removerFim();
			}

			if (acao.charAt(0) == 'R') {
				removidos++;
			}

			i++;
		}

		for (int z = 0; z < removidos; z++) {
			System.out.println("(R) " + removido[z].getNome());
		}

		listaJogadores.mostrar();

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

	public static Lista preencherJogadores(ArquivoTextoLeitura leitura, int qtdJogadores) throws Exception {
		Jogador atual = new Jogador();
		Lista listaTodosJogadores = new Lista(qtdJogadores); // Reserva o armazenamento

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

			listaTodosJogadores.inserirFim(atual);
			atual = new Jogador();
		}

		leitura.fecharArquivo();

		return listaTodosJogadores;
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

class Lista {

	private Jogador[] listaJogador;

	private int primeiro;
	private int ultimo;

	private int tamanho;

	public Lista() {
		listaJogador = new Jogador[3922];

		primeiro = ultimo = 0;
	}

	public Lista(int tamanho) {
		listaJogador = new Jogador[tamanho];

		this.tamanho = tamanho;
		primeiro = ultimo = 0;
	}

	public boolean listaCheia() {
		boolean resp;

		if (ultimo == tamanho)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public boolean listaVazia() {
		boolean resp;

		if (primeiro == ultimo)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public void inserir(Jogador jogador, int posicao) throws Exception {

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaCheia()) {

				for (int j = ultimo; j > posicao; j--) {
					listaJogador[j] = listaJogador[j - 1]; // Chegando todos uma posição para frente
				}

				listaJogador[posicao] = jogador;
				ultimo++;

			} else {
				new Exception("Erro ao tentar inserir na lista: a lista está cheia");
			}

		} else {
			new Exception("Erro ao tentar inserir na lista: posição inválida! Lista ficaria com buracos");
		}

	}

	public void inserirInicio(Jogador jogador) throws Exception {

		int posicao = 0;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaCheia()) {

				for (int j = ultimo; j > posicao; j--) {
					listaJogador[j] = listaJogador[j - 1]; // Chegando todos uma posição para frente
				}

				listaJogador[posicao] = jogador;
				ultimo++;

			} else {
				new Exception("Erro ao tentar inserir na lista: a lista está cheia");
			}

		} else {
			new Exception("Erro ao tentar inserir na lista: posição inválida! Lista ficaria com buracos");
		}

	}

	public void inserirFim(Jogador jogador) throws Exception {

		int posicao = ultimo;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaCheia()) {

				listaJogador[posicao] = jogador;
				ultimo++;

			} else {
				new Exception("Erro ao tentar inserir na lista: a lista está cheia");
			}

		} else {
			new Exception("Erro ao tentar inserir na lista: posição inválida! Lista ficaria com buracos");
		}

	}

	public Jogador remover(int posicao) throws Exception {

		Jogador retirado = null;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaVazia()) {

				retirado = listaJogador[posicao].clone();
				ultimo--;
				for (int j = posicao; j < ultimo; j++) {
					listaJogador[j] = listaJogador[j + 1];
				}

			} else {
				new Exception("Erro ao tentar remover na lista: a lista está vazia");
			}

		} else {
			new Exception("Erro ao tentar remover na lista: posição inválida! Lista não possui valor nessa posição");
		}

		return retirado;

	}

	public Jogador removerInicio() throws Exception {

		int posicao = primeiro;
		Jogador retirado = null;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaVazia()) {

				retirado = listaJogador[posicao].clone();
				ultimo--;
				for (int j = posicao; j < ultimo; j++) {
					listaJogador[j] = listaJogador[j + 1];
				}

			} else {
				new Exception("Erro ao tentar remover na lista: a lista está vazia");
			}

		} else {
			new Exception("Erro ao tentar remover na lista: posição inválida! Lista não possui valor nessa posição");
		}

		return retirado;

	}

	public Jogador removerFim() throws Exception {

		int posicao = ultimo - 1;
		Jogador retirado = null;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaVazia()) {

				retirado = listaJogador[posicao].clone();
				ultimo--;
				for (int j = posicao; j < ultimo; j++) {
					listaJogador[j] = listaJogador[j + 1];
				}

			} else {
				new Exception("Erro ao tentar remover na lista: a lista está vazia");
			}

		} else {
			new Exception("Erro ao tentar remover na lista: posição inválida! Lista não possui valor nessa posição");
		}

		return retirado;

	}

	public Jogador getJogador(int posicao) {
		Jogador encontrado = null;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaVazia()) {

				encontrado = listaJogador[posicao];

			} else {
				new Exception("Erro ao tentar encontrar na lista: a lista está vazia");
			}

		} else {
			new Exception("Erro ao tentar encontrar na lista: posição inválida! Lista não possui valor nessa posição");
		}

		return encontrado;
	}

	public void mostrar() {

		if (!listaVazia()) {

			for (int aux = primeiro; aux < ultimo; aux++) {
				System.out.print("[" + aux + "] ## ");

				System.out.printf("%d ## ", listaJogador[aux].getId());

				System.out.printf("%s ## ", listaJogador[aux].getNome());

				System.out.printf("%d ## ", listaJogador[aux].getAltura());

				System.out.printf("%d ## ", listaJogador[aux].getPeso());

				System.out.printf("%d ## ", listaJogador[aux].getAnoNascimento());

				if (listaJogador[aux].getUniversidade().trim().length() == 0) { // Se o dado esta vazio
					System.out.printf("nao informado ## ");
				} else {
					System.out.printf("%s ## ", listaJogador[aux].getUniversidade());
				}

				if (listaJogador[aux].getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
					System.out.printf("nao informado ## ");
				} else {
					System.out.printf("%s ## ", listaJogador[aux].getCidadeNascimento());
				}

				if (listaJogador[aux].getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
					System.out.printf("nao informado ## \n");
				} else {
					System.out.printf("%s ## \n", listaJogador[aux].getEstadoNascimento());
				}

			}

		}

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

