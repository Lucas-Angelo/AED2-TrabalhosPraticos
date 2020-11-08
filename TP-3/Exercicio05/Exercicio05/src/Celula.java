public class Celula {

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
