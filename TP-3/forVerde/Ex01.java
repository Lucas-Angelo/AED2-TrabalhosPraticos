import java.io.*;

public class Ex01 {
    public static void main(String[] args) throws Exception {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

        int qtdJogadores = qtdLinhas(leitura);

        Jogador allPlayers[] = preencherJogadores(leitura, qtdJogadores);
        Pilha stack = new Pilha(qtdJogadores);
        int id;
    
        String read;
        do{
            read = in.readLine();

            if (!read.equals("FIM")){
                id = Integer.parseInt(read);
                stack.empilhar(allPlayers[id]);
            }

        }while(!read.equals("FIM"));

        int qtdcomandos = Integer.parseInt(in.readLine());
        int toStackUpId;

        String comando = new String();
        for (int i=0;i<qtdcomandos;i++){

            comando = in.readLine();

            if (comando.charAt(0) == 'I'){

                toStackUpId = Integer.parseInt(comando.substring(2, comando.length()));
                stack.empilhar(allPlayers[toStackUpId]);
            }
            else if (comando.charAt(0) == 'R')
                System.out.println("(R) " + stack.desemplihar().getNome());
                
        }

        stack.mostrar();

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

			// Caso necessite de remover os asterisco s� tirar o comen�rio das linhas abaixo
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
			System.out.printf("nao informado ##\n");
		} else {
			System.out.printf("%s ##\n", this.estadoNascimento);
		}

	}

}


class Pilha {

    private int tamanho;
    private int topo;
    private Jogador players[];

    Pilha (){
        topo = 0;
        tamanho = 4000;
        players = new Jogador[tamanho];
        topo = -1;
    }
    Pilha (int size){
        topo = 0;
        tamanho = size;
        players = new Jogador[tamanho];
        topo = -1;
    }

    public void empilhar (Jogador player){
        topo++;
        if (topo == tamanho){
            System.out.println("Fail to stack up: Stack is already full");
            topo--;
        }
        else{
            players[topo] = player.clone();
        }
    }

    public Jogador desemplihar(){
        if (topo == -1){
            System.out.println("Fail to unstack: Stack is empty");
            return null;
        }
        Jogador aux = new Jogador();
        aux = players[topo].clone();
        players[topo] = null;
        topo--;
        return aux;
    }

    public void mostrar(){
        for (int i=0;i<topo+1;i++){
            System.out.printf("[%d] ", i);
            players[i].imprimir();
        }
    }

}