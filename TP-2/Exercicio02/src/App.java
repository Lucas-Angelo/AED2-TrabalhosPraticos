import java.io.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jogador player[] = new Jogador[10];
		
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
