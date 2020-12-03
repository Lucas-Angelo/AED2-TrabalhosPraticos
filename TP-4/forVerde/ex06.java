import java.io.*;

public class ex06 {

	public static void main(String[] args) throws NumberFormatException, Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		String idInformado = new String();

		int qtdJogadores = qtdLinhas(leitura); // Procurar quantas linhas/jogadores existem para deixar mais limpo o
												// codigo

		Jogador[] players = preencherVetorJogador(leitura, qtdJogadores); // Primeiro preencher o vetor com os dados da
																			// tabela

		TabelaHash tabela = new TabelaHash(37);

		do {
			idInformado = in.readLine(); // Qual jogador deseja procurar

			if (!(idInformado.equals("FIM"))) {

				tabela.inserir(players[Integer.parseInt(idInformado)]);

			}

		} while (!(idInformado.equals("FIM")));

		String nome;
		do {
			nome = in.readLine(); // Qual jogador deseja procurar

			if (!(nome.equals("FIM"))) {

				Jogador buscado = null;
				for (int i = 0; i < players.length; i++)
					if (players[i].getNome().equals(nome))
						buscado = players[i];

				if (tabela.pesquisar(buscado) == null)
					System.out.print("NAO\n");
				else
					System.out.print("SIM\n");

			}

		} while (!(nome.equals("FIM")));

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

	public static Jogador[] preencherVetorJogador(ArquivoTextoLeitura leitura, int qtdLinhas) {
		Jogador[] players = new Jogador[qtdLinhas]; // Reserva o armazenamento

		for (int i = 0; i < qtdLinhas; i++)
			players[i] = new Jogador(); // Cria o objeto para cada um

		leitura.abrirArquivo("/tmp/players.csv");

		leitura.ler(); // Remove o cabecalho
		for (int i = 0; i < qtdLinhas; i++) {

			String[] dadosDaLinha = leitura.ler().split(",", 8); // Dividir os dados da linha

			// Caso necessite de remover os asterisco só tirar o comenário das linhas abaixo
			/*
			 * String nome = dadosDaLinha[1].toString(); char ultima =
			 * nome.charAt(nome.length()-1);
			 * 
			 * if(ultima=='*') { dadosDaLinha[1]=""; for(int z=0; z<nome.length()-1; z++) {
			 * dadosDaLinha[1] += nome.charAt(z); } }
			 */

			// Seta os dados de cada linha para cada atributo do player atual
			players[i].setId(Integer.parseInt((dadosDaLinha[0].toString()))); // Transforma array em string, para
																				// transformar em int
			players[i].setNome(dadosDaLinha[1].toString());
			players[i].setAltura(Integer.parseInt((dadosDaLinha[2].toString())));
			players[i].setPeso(Integer.parseInt((dadosDaLinha[3].toString())));
			players[i].setUniversidade(dadosDaLinha[4].toString());
			players[i].setAnoNascimento(Integer.parseInt((dadosDaLinha[5].toString())));
			players[i].setCidadeNascimento(dadosDaLinha[6].toString());
			players[i].setEstadoNascimento(dadosDaLinha[7].toString());

		}

		leitura.fecharArquivo();

		return players;
	}

}

class TabelaHash {

	private Lista[] tabela;
	private int M; // Bom ser numero primo e ser grande o suficiente pra caber tudo

	public TabelaHash(int tamanho) {
		this.M = tamanho; // Se quiser configurar pra ser numero primo
		this.tabela = new Lista[tamanho];
		for (int i = 0; i < tamanho; i++) {
			this.tabela[i] = new Lista();
		}
	}

	// h(x) = x % M
	private int funcaoHash(int x) {
		return (x % 37);
	}

	public int inserir(Jogador elemento) throws Exception {

		// Encontrar posicao da tabela por meio da funcao hash
		int posicao = funcaoHash(elemento.getAltura());

		// Verificar se esse elemento ja esta na lista
		if (tabela[posicao].localizar(elemento.getNome()) == null)
			// Elemento nao inserido ainda
			tabela[posicao].inserir(elemento, 0); // Nao sabe quantos elementos lista vai ter, sempre inserir no inicio
		else
			// Elemento ja inserido
			posicao = -1;

		return posicao;

	}

	public Jogador pesquisar(Jogador procurado) {
		int posicao = funcaoHash(procurado.getAltura());

		Jogador encontrado = tabela[posicao].localizar(procurado.getNome());
		if (encontrado != null)
			System.out.print(posicao + " ");

		return encontrado; // Se for null, nao tem na tabela
	}

	public Jogador remover(int chave) throws Exception {
		int posicao = funcaoHash(chave);
		return tabela[posicao].remover(chave); // Retorna o retirado
	}

	public void imprimir() {
		for (int i = 0; i < this.M; i++)
			tabela[i].mostrar(); // Imprimir toda a tabela
	}

}

class Lista {

	private int tamanho;
	private Celula primeiro;
	private Celula ultimo;

	Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
		tamanho = 0;
	}

	public void inserirInicio(Jogador player) {
		Celula aux = primeiro.proximo;
		primeiro.proximo = new Celula();
		primeiro.proximo.proximo = aux;

		primeiro.proximo.item = player.clone();

		tamanho++;
	}

	public void inserir(Jogador player, int posicao) {

		if (posicao == 1)
			inserirInicio(player);

		else if (posicao == tamanho)
			inserirFim(player);

		else {
			Celula find = primeiro;

			for (int i = 0; i < posicao; i++) {
				find = find.proximo;
			}

			Celula aux = find.proximo;
			find.proximo = new Celula();
			find.proximo.proximo = aux;

			find.proximo.item = player.clone();

			tamanho++;
		}
	}

	public void inserirFim(Jogador player) {
		ultimo.proximo = new Celula();
		ultimo = ultimo.proximo;

		ultimo.item = player.clone();

		tamanho++;
	}

	public Jogador removerInicio() {
		if (primeiro == ultimo) {
			System.out.println("Fail to remove: The list is empty");
			return null;
		}

		Jogador toReturn = new Jogador();
		toReturn = primeiro.proximo.item.clone();

		primeiro.proximo = primeiro.proximo.proximo;

		tamanho--;

		return toReturn;
	}

	public Jogador remover(int posicao) {
		if (primeiro == ultimo) {
			System.out.println("Fail to remove: The list is empty");
			return null;
		}

		if (posicao == 1)
			return removerInicio();

		else if (posicao == tamanho + 1)
			return removerFim();

		Celula find = primeiro;
		for (int i = 0; i < posicao; i++) {
			find = find.proximo;
		}

		Jogador toReturn = new Jogador();
		toReturn = find.proximo.item.clone();

		find.proximo = find.proximo.proximo;

		tamanho--;

		return toReturn;
	}

	public Jogador removerFim() {
		if (primeiro == ultimo) {
			System.out.println("Fail to remove: The list is empty");
			return null;
		}

		Jogador toReturn = new Jogador();
		toReturn = ultimo.item.clone();

		Celula find = primeiro;
		for (int i = 0; i < tamanho - 1; i++) {
			find = find.proximo;
		}

		ultimo = find;
		ultimo.proximo = null;

		tamanho--;

		return toReturn;
	}

	public void mostrar() {
		Celula atual = primeiro.proximo;
		for (int i = 0; i < tamanho; i++, atual = atual.proximo) {
			System.out.printf("[%d] ", i);
			atual.item.imprimir();
		}
	}

	public Jogador localizar(String procurado) {
		Celula aux;
		Jogador encontrado = null;

		aux = primeiro.proximo;
		while (aux != null && encontrado == null) {
			if (aux.item.getNome().equals(procurado))
				encontrado = aux.item;
			else
				aux = aux.proximo;
		}

		return encontrado;
	}

}

class Celula {
    Jogador item;
    Celula proximo;

    Celula(){
        item = new Jogador();
        proximo = null;
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

	// In�cio GETS
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

	// In�cio SETS
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

		System.out.printf("## %d ## ", this.id);

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
			System.out.printf("nao informado ## \n");
		} else {
			System.out.printf("%s ## \n", this.estadoNascimento);
		}

	}

}

class ArquivoTextoLeitura {

	private BufferedReader entrada;

	public void abrirArquivo(String nomeArquivo) {

		try {
			entrada = new BufferedReader(new FileReader(nomeArquivo));
		} catch (FileNotFoundException excecao) {
			System.out.println("Arquivo não encontrado");
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
		} catch (EOFException excecao) { // Exceção de final de arquivo.
			return null;
		} catch (IOException excecao) {
			System.out.println("Erro de leitura: " + excecao);
			return null;
		}
		return textoEntrada;
	}
}

