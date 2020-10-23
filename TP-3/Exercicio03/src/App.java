import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
	public static void main(String[] args) throws NumberFormatException, Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		int qtdJogadores = qtdLinhas(leitura);

		Lista listaTodosJogadores = preencherJogadores(leitura, qtdJogadores); // Lista que salva todos os jogadores
		Lista listaJogadores = new Lista(qtdJogadores); // Lista preenchida com alguns jogadores

		String idInformado = new String();
		do {
			idInformado = in.readLine(); // Qual jogador deseja procurar

			if (!(idInformado.equals("FIM"))) {

				// Captura da lista que possui todos os jogadores, e insere ele na outra lista
				listaJogadores.inserirFim(listaTodosJogadores.getJogador(Integer.parseInt(idInformado)));

			}

		} while (!(idInformado.equals("FIM")));

		String[] dadosAcao;

		int i = 0;
		int id, posicao;

		int qtd = Integer.parseInt(in.readLine());

		int removidos = 0;
		Jogador removido[] = new Jogador[qtd];
		for (int z = 0; z < qtd; z++)
			removido[z] = new Jogador();

		while (i < qtd) {

			String acao = in.readLine();

			if (acao.charAt(0) == 'I' && acao.charAt(1) == 'I') {
				dadosAcao = acao.split(" ", 2);
				id = Integer.parseInt(dadosAcao[1].toString());
				listaJogadores.inserirInicio(listaTodosJogadores.getJogador(id));
			}

			if (acao.charAt(0) == 'I' && acao.charAt(1) == '*') {
				dadosAcao = acao.split(" ", 3);
				id = Integer.parseInt(dadosAcao[2].toString());
				posicao = Integer.parseInt(dadosAcao[1].toString());
				listaJogadores.inserir(listaTodosJogadores.getJogador(id), posicao);
			}

			if (acao.charAt(0) == 'I' && acao.charAt(1) == 'F') {
				dadosAcao = acao.split(" ", 2);
				id = Integer.parseInt(dadosAcao[1].toString());
				listaJogadores.inserirFim(listaTodosJogadores.getJogador(id));
			}

			if (acao.charAt(0) == 'R' && acao.charAt(1) == 'I') {
				removido[removidos] = listaJogadores.removerInicio();
			}

			if (acao.charAt(0) == 'R' && acao.charAt(1) == '*') {
				dadosAcao = acao.split(" ", 2);
				posicao = Integer.parseInt(dadosAcao[1].toString());
				removido[removidos] = listaJogadores.remover(posicao);
			}

			if (acao.charAt(0) == 'R' && acao.charAt(1) == 'F') {
				removido[removidos] = listaJogadores.removerFim();
			}

			if (acao.charAt(0) == 'R') {
				removidos++;
			}

			i++;
		}

		for (int z = 0; z < removidos; z++) {
			System.out.println("(R) " + removido[z].getNome());
		}

		listaJogadores.mostrar();

	}

	public static int qtdLinhas(ArquivoTextoLeitura leitura) {
		int qtd = 0;
		String linhaLida = new String();
		leitura.abrirArquivo("players.csv");

		leitura.ler(); // O cabecalho, tem que pular a primeira linha
		linhaLida = leitura.ler();
		while (linhaLida != null) {
			qtd++;
			linhaLida = leitura.ler();
		}

		leitura.fecharArquivo();

		return qtd; // Retorna a quantidade de jogadores/Quantidade de linhas
	}

	public static Lista preencherJogadores(ArquivoTextoLeitura leitura, int qtdJogadores) throws Exception {
		Jogador atual = new Jogador();
		Lista listaTodosJogadores = new Lista(qtdJogadores); // Reserva o armazenamento

		leitura.abrirArquivo("players.csv");

		leitura.ler(); // Remove o cabecalho
		for (int i = 0; i < qtdJogadores; i++) {

			String[] dadosDaLinha = leitura.ler().split(",", 8); // Dividir os dados da linha

			// Caso necessite de remover os asterisco só tirar o comenário das linhas abaixo
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

			listaTodosJogadores.inserirFim(atual);
			atual = new Jogador();
		}

		leitura.fecharArquivo();

		return listaTodosJogadores;
	}

}
