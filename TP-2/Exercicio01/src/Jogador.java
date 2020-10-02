import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Jogador {

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
	public void setId () {
		this.id = id;
	}
	
	public void setNome () {
		this.nome = nome;
	}
	
	public void setAltura() {		
		this.altura = altura;
	}
	
	public void setPeso () {
		this.peso = peso;
	}
	
	public void setUniversidade () {
		this.universidade = universidade;
	}
	
	public void setAnoNascimento () {
		this.anoNascimento = anoNascimento;
	}
	
	public void setCidadeNascimento () {
		this.cidadeNascimento = cidadeNascimento;
	}
	
	public void setEstadoNascimento () {
		this.estadoNascimento = estadoNascimento;
	}
	// Fim SETS
	
	public Jogador clone() {
		Jogador jogador = new Jogador();
		return jogador;
	}
	
	public void imprimir () {
		
		System.out.printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## #s]", id, nome, altura, peso, anoNascimento, universidade, cidadeNascimento, estadoNascimento);
		
	}
	

}
