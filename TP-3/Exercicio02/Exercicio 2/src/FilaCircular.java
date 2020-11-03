public class FilaCircular {

	private Jogador players[];
	private int frente;
	private int tras;

	FilaCircular() {

		frente = 0;
		tras = 0;
		int tamanho = 4000;
		players = new Jogador[tamanho];

	}

	FilaCircular(int size) {

		frente = 0;
		tras = 0;
		int tamanho = size;
		players = new Jogador[tamanho];

	}

	public void enfileirar(Jogador players) {

		if (!filaCheia()) {
			players[tras % players.lenght] = players;
			tras++;
		}

	}

	public Jogador desenfileirar() {

		int retirado = -1;

		if (!filaVazia()) {
			retirado = players[frente % players.length];
			frente++;
		}

		return retirado;

	}

	public boolean filaVazia() {

		if (frente == tras)
			return true;
		else
			return false;

	}

	public boolean filaCheia(){
		
		if ((tras + 1) % players.length) == (frente % players.lenght)
				return true;
		else
			return false;
		
	}

	public void mostrar() {

	}

	public double obterMediaAltura() {

	}

}