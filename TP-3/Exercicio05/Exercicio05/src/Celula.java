public class Celula {

	Jogador item; // Dados do jogador
	Celula proximo; // Apontar da frente ate atras na fila

	public Celula() { // Sentinela, não fala o item que vai colocar na célula
		this.item = new Jogador();
		proximo = null;
	}

	public Celula(Jogador player) { // Preencher, o que têm que colocar na célula
		this.item = player;
		proximo = null;
	}

}
