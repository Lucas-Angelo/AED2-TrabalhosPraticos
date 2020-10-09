import java.io.*;

public class App {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String idInformado = new String();
		
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
		
		int qtdJogadores = qtdLinhas(leitura);
		
		Jogador[] players = preencherVetorJogador(leitura, qtdJogadores);
		
		do {
			idInformado = in.readLine();
			
			if(!(idInformado.equals("FIM"))) {
				
				players[Integer.parseInt(idInformado)].imprimir();;
				
			}
			
		} while ( !(idInformado.equals("FIM")) );
		
	}
	
	public static int qtdLinhas (ArquivoTextoLeitura leitura) {
		int qtd=0;
		String linhaLida = new String();
		leitura.abrirArquivo("/tmp/players.csv");
		
		leitura.ler(); // O cabecalho, tem que pular a primeira linha
		linhaLida = leitura.ler();
        while (linhaLida != null){
        	qtd++;
        	linhaLida = leitura.ler();
        } 
		
		leitura.fecharArquivo();
		
		return qtd;
	}
	
	public static Jogador[] preencherVetorJogador (ArquivoTextoLeitura leitura, int qtdLinhas) {
		Jogador[] players = new Jogador[qtdLinhas];

		for(int i=0; i<qtdLinhas; i++)
			players[i] = new Jogador();
		
		leitura.abrirArquivo("/tmp/players.csv");
		
		leitura.ler(); // Remove o cabecalho
        for(int i=0; i<qtdLinhas; i++) {
            
            String[] dadosDaLinha = leitura.ler().split(",", 8); // Dividir os dados da linha
                
            players[i].setId(Integer.parseInt((dadosDaLinha[0].toString()))); // Transforma array em string, para transformar em int
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

class Jogador {

	private int id;
	private String nome = new String();
	private int altura;
	private int peso;
	private String universidade = new String();
	private int anoNascimento;
	private String cidadeNascimento = new String();
	private String estadoNascimento = new String();
	
	public Jogador () {
		
	}
	
	public Jogador (int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
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
	public int getId () {
		return this.id;
	}
	
	public String getNome () {
		return this.nome;
	}
	
	public int getAltura() {		
		return this.altura;
	}
	
	public int getPeso () {
		return this.peso;
	}
	
	public String getUniversidade () {
		return this.universidade;
	}
	
	public int getAnoNascimento () {
		return this.anoNascimento;
	}
	
	public String getCidadeNascimento () {
		return this.cidadeNascimento;
	}
	
	public String getEstadoNascimento () {
		return this.estadoNascimento;
	}
	// Fim GETS
	
	// Início SETS
	public void setId (int id) {
		this.id = id;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public void setAltura(int altura) {		
		this.altura = altura;
	}
	
	public void setPeso (int peso) {
		this.peso = peso;
	}
	
	public void setUniversidade (String universidade) {
		this.universidade = universidade;
	}
	
	public void setAnoNascimento (int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}
	
	public void setCidadeNascimento (String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}
	
	public void setEstadoNascimento (String estadoNascimento) {
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
	
	public void imprimir () {
		
        System.out.printf("[%d ## ", this.id);
        
        System.out.printf("%s ## ", this.nome);
        
        System.out.printf("%d ## ", this.altura);
        
        System.out.printf("%d ## ", this.peso);
        
        System.out.printf("%d ## ", this.anoNascimento);
        
        if (this.universidade.trim().length() == 0) { // Se o dado esta vazio
            System.out.printf("nao informado ## ");
        }
        else {
        	System.out.printf("%s ## ", this.universidade);
        }
        
        if (this.cidadeNascimento.trim().length() == 0) { // Se o dado esta vazio
            System.out.printf("nao informado ## ");
        }
        else {
        	System.out.printf("%s ## ", this.cidadeNascimento);
        }
        
        if (this.estadoNascimento.trim().length() == 0) { // Se o dado esta vazio
            System.out.printf("nao informado]\n");
        }
        else {
        	System.out.printf("%s]\n", this.estadoNascimento);
        }
		
	}
	

}

class ArquivoTextoLeitura {

	private BufferedReader entrada;
	
	public void abrirArquivo(String nomeArquivo){	
		
		try {
			entrada = new BufferedReader(new FileReader(nomeArquivo));
		}
		catch (FileNotFoundException excecao) {
			System.out.println("Arquivo nÃ£o encontrado");
		}
	}
	
	public void fecharArquivo() {
		
		try {
			entrada.close();
		}
		catch (IOException excecao) {
			System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);	
		}
	}
	
	public String ler() {
		
		String textoEntrada;
		
		try {
			textoEntrada = entrada.readLine();
		}
		catch (EOFException excecao) { //ExceÃ§Ã£o de final de arquivo.
			return null;
		}
		catch (IOException excecao) {
			System.out.println("Erro de leitura: " + excecao);
			return null;
		}
		return textoEntrada;
	}
}

