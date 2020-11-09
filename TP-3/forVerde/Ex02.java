import java.io.*;

public class Ex02 {
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		int qtdJogadores = qtdLinhas(leitura);

		Jogador allPlayers[] = preencherJogadores(leitura, qtdJogadores);
		FilaCircular fila = new FilaCircular(5);
		int id;

		String read;
		do {
			read = in.readLine();

			if (!read.equals("FIM")) {
				id = Integer.parseInt(read);
				fila.enfileirar(allPlayers[id]);
				System.out.println((int) fila.obterMediaAltura());
			}

		} while (!read.equals("FIM"));

		int qtdcomandos = Integer.parseInt(in.readLine());
		int toStackUpId;

		String comando = new String();
		for (int i = 0; i < qtdcomandos; i++) {

			comando = in.readLine();

			if (comando.charAt(0) == 'I') {

				toStackUpId = Integer.parseInt(comando.substring(2, comando.length()));
				fila.enfileirar(allPlayers[toStackUpId]);

				System.out.println((int) fila.obterMediaAltura());

			} else if (comando.charAt(0) == 'R')
				System.out.println("(R) " + fila.desenfileirar().getNome());

		}

		fila.mostrar();

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

	public static Jogador[] preencherJogadores(ArquivoTextoLeitura leitura, int qtdJogadores) throws Exception {
		Jogador atual = new Jogador();
		Jogador listaTodosJogadores[] = new Jogador[qtdJogadores]; // Reserva o armazenamento

		leitura.abrirArquivo("/tmp/players.csv");

		leitura.ler(); // Remove o cabecalho
		for (int i = 0; i < qtdJogadores; i++) {

			String[] dadosDaLinha = leitura.ler().split(",", 8); // Dividir os dados da linha

			// Caso necessite de remover os asterisco sï¿½ tirar o comenï¿½rio das linhas
			// abaixo
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

			listaTodosJogadores[i] = atual.clone();
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

	// Inï¿½cio GETS
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

	// Inï¿½cio SETS
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
			System.out.printf("nao informado ##\n");
		} else {
			System.out.printf("%s ##\n", this.estadoNascimento);
		}

	}

}

class FilaCircular {

	private int tamanho;
	private int frente;
	private int tras;
	private Jogador fila[];

	public FilaCircular() {
		this.tamanho = 5;
		this.frente = this.tras = -1;
		this.fila = new Jogador[5];
	}

	public FilaCircular(int tamanho) {
		this.tamanho = tamanho;
		this.frente = this.tras = -1;
		this.fila = new Jogador[tamanho];
	}

	public boolean filaVazia() {
		boolean res;
		if (frente == -1)
			res = true;
		else
			res = false;

		return res;
	}

	public boolean filaCheia() {
		boolean res;
		if ((frente == 0 && tras == tamanho - 1) || (tras == (frente - 1) % (tamanho - 1)))
			res = true;
		else
			res = false;

		return res;
	}

	public void enfileirar(Jogador novo) {

		if (filaCheia()) {
			desenfileirar();
		}

		if (filaVazia()) {
			frente = 0;
			tras = 0;
			fila[tras] = novo;
		}

		else if (tras == tamanho - 1 && frente != 0) {
			tras = 0;
			fila[tras] = novo;
		}

		else {
			tras = (tras + 1);

			fila[tras] = novo;
		}
	}

	public Jogador desenfileirar() {
		Jogador temp;

		if (frente == -1) {
			System.out.print("fila is Empty");

			return null;
		}

		temp = fila[frente];

		if (frente == tras) {
			frente = -1;
			tras = -1;
		}

		else if (frente == tamanho - 1) {
			frente = 0;
		} else {
			frente = frente + 1;
		}

		return temp;
	}

	public void mostrar() {

		if (!filaVazia()) {

			if (tras >= frente) {
				for (int i = frente; i <= tras; i++) {
					System.out.printf("[%d] ## ", i - 1);

					System.out.printf("%d ## ", fila[i].getId());

					System.out.printf("%s ## ", fila[i].getNome());

					System.out.printf("%d ## ", fila[i].getAltura());

					System.out.printf("%d ## ", fila[i].getPeso());

					System.out.printf("%d ## ", fila[i].getAnoNascimento());

					if (fila[i].getUniversidade().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getUniversidade());
					}

					if (fila[i].getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getCidadeNascimento());
					}

					if (fila[i].getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ##\n");
					} else {
						System.out.printf("%s ##\n", fila[i].getEstadoNascimento());
					}

				}
			} else {

				for (int i = frente; i < tamanho; i++) {
					System.out.printf("[%d] ## ", i - 1);

					System.out.printf("%d ## ", fila[i].getId());

					System.out.printf("%s ## ", fila[i].getNome());

					System.out.printf("%d ## ", fila[i].getAltura());

					System.out.printf("%d ## ", fila[i].getPeso());

					System.out.printf("%d ## ", fila[i].getAnoNascimento());

					if (fila[i].getUniversidade().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getUniversidade());
					}

					if (fila[i].getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getCidadeNascimento());
					}

					if (fila[i].getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ##\n");
					} else {
						System.out.printf("%s ##\n", fila[i].getEstadoNascimento());
					}
				}

				for (int i = 0; i <= tras; i++) {
					System.out.printf("[%d] ## ", i - 1);

					System.out.printf("%d ## ", fila[i].getId());

					System.out.printf("%s ## ", fila[i].getNome());

					System.out.printf("%d ## ", fila[i].getAltura());

					System.out.printf("%d ## ", fila[i].getPeso());

					System.out.printf("%d ## ", fila[i].getAnoNascimento());

					if (fila[i].getUniversidade().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getUniversidade());
					}

					if (fila[i].getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getCidadeNascimento());
					}

					if (fila[i].getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ##\n");
					} else {
						System.out.printf("%s ##\n", fila[i].getEstadoNascimento());
					}
				}

			}

		}

	}

	public double obterMediaAltura() {

		double somatorio = 0;
		int cont = 0;

		if (tras >= frente) {

			for (int i = frente; i <= tras; i++) {
				somatorio += fila[i].getAltura();
				cont++;
			}

		}

		else {
			for (int i = frente; i < tamanho; i++) {
				somatorio += fila[i].getAltura();
				cont++;
			}

			for (int i = 0; i <= tras; i++) {
				somatorio += fila[i].getAltura();
				cont++;
			}

		}

		double media = somatorio / cont;

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


