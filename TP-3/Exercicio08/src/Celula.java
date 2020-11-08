public class Celula {

	Jogador item;
	Celula anterior;
	Celula proximo;

	public Celula(Jogador novo) {
		this.item = novo;
		this.proximo = null;
		this.anterior = null;
	}

	public Celula() {
		this.item = new Jogador();
		this.proximo = null;
		this.anterior = null;
	}

}
