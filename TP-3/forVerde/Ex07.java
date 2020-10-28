import java.io.*;

public class Ex07 {
    public static void main(String[] args) throws Exception {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

        int qtdJogadores = qtdLinhas(leitura);

        Jogador allPlayers[] = preencherJogadores(leitura, qtdJogadores);
        Lista list = new Lista();
        int id;

        String read;
        do{
            read = in.readLine();

            if (!read.equals("FIM")){
                id = Integer.parseInt(read);
                list.inserirFim(allPlayers[id]);
            }

        }while(!read.equals("FIM"));

        int qtdcomandos = Integer.parseInt(in.readLine());
		String split[] = new String[3];
		int tolistUpId;
		int position=1;
		for (int i=0;i<3;i++)
			split[i] = new String();

        String comando = new String();
        for (int i=0;i<qtdcomandos;i++){

            comando = in.readLine();

            if (comando.charAt(0) == 'I'){

				split = comando.split(" ");
				if ( split.length == 2 )
					tolistUpId = Integer.parseInt(split[1]);
				else{
					tolistUpId = Integer.parseInt(split[2]);
					position = Integer.parseInt(split[1]);
				}
					

				if (comando.charAt(1) == 'I')
					list.inserirInicio(allPlayers[tolistUpId]);

				else if (comando.charAt(1) == 'F')
					list.inserirFim(allPlayers[tolistUpId]);

				else if (comando.charAt(1) == '*')
					list.inserir(allPlayers[tolistUpId], position);

            }
			else if (comando.charAt(0) == 'R'){
				
				if (comando.charAt(1) == 'I')
					System.out.println("(R) " + list.removerInicio().getNome());

				else if (comando.charAt(1) == 'F')
					System.out.println("(R) " + list.removerFim().getNome());

				else if (comando.charAt(1) == '*'){
					position = Integer.parseInt(comando.substring(3, comando.length()));
					System.out.println("(R) " + list.remover(position).getNome());
				} 

			}
                
		}

        list.mostrar();

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


class Lista {

    private int tamanho;
    private Celula primeiro;
    private Celula ultimo;

    Lista (){
        primeiro = new Celula();
        ultimo = primeiro;
        tamanho=0;
    }

    public void inserirInicio (Jogador player){
        Celula aux = primeiro.proximo;
        primeiro.proximo = new Celula();
        primeiro.proximo.proximo = aux;

        primeiro.proximo.item = player.clone();

        tamanho++;
    }

    public void inserir (Jogador player, int posicao){
        
        if (posicao == 1)
            inserirInicio(player);

        else if (posicao == tamanho)
            inserirFim(player);

        else{
            Celula find = primeiro;

            for (int i=0 ; i<posicao; i++){
                find = find.proximo;
            }

            Celula aux = find.proximo;
            find.proximo = new Celula();
            find.proximo.proximo = aux;

            find.proximo.item = player.clone();

            tamanho++;
        }
    }

    public void inserirFim (Jogador player){
        ultimo.proximo = new Celula();
        ultimo = ultimo.proximo;

        ultimo.item = player.clone();

        tamanho++;
    }

    public Jogador removerInicio(){
        if (primeiro == ultimo){
            System.out.println("Fail to remove: The list is empty");
            return null;
        }

        Jogador toReturn = new Jogador();
        toReturn = primeiro.proximo.item.clone();

        primeiro.proximo = primeiro.proximo.proximo;

        tamanho--;

        return toReturn;
    }

    public Jogador remover(int posicao){
        if (primeiro == ultimo){
            System.out.println("Fail to remove: The list is empty");
            return null;
        }

        if (posicao == 1)
            return removerInicio();

        else if (posicao == tamanho+1)
            return removerFim();


        Celula find = primeiro;
        for (int i=0;i<posicao;i++){
            find = find.proximo;
        }

        Jogador toReturn = new Jogador();
        toReturn = find.proximo.item.clone();
        
        find.proximo = find.proximo.proximo;

        tamanho--;

        return toReturn;
    }

    public Jogador removerFim(){
        if (primeiro == ultimo){
            System.out.println("Fail to remove: The list is empty");
            return null;
        }

        Jogador toReturn = new Jogador();
        toReturn = ultimo.item.clone();

        Celula find = primeiro;
        for (int i=0;i<tamanho-1;i++){
            find = find.proximo;
        }

        ultimo = find;
        ultimo.proximo = null;

        tamanho--;

        return toReturn;
    }

    public void mostrar(){
        Celula atual = primeiro.proximo;
        for (int i=0 ; i<tamanho ; i++ , atual = atual.proximo){
            System.out.printf("[%d] ", i);
            atual.item.imprimir();
        }
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

