public class Celula {

	Jogador item; // Dados do jogador
	Celula proximo; // Apontar da frente ate atras na fila

	public Celula() { // Sentinela, n�o fala o item que vai colocar na c�lula
		this.item = new Jogador();
		proximo = null;
	}

	public Celula(Jogador player) { // Preencher, o que t�m que colocar na c�lula
		this.item = player;
		proximo = null;
	}

}
