public class ABB {

	private NodoJogador raiz; // referência à raiz da árvore.

	/// Construtor da classe.
	/// Esse construtor cria uma nova árvore binária de alunos vazia. Para isso,
	/// esse método atribui null à raiz da árvore.
	public ABB() {
		raiz = null;
	}

	/// Método booleano que indica se a árvore está vazia ou não.
	/// Retorna:
	/// verdadeiro: se a raiz da árvore for null, o que significa que a árvore
	/// está vazia.
	/// falso: se a raiz da árvore não for null, o que significa que a árvore
	/// não está vazia.
	public Boolean arvoreVazia() {
		/// Se a raiz da árvore for null, a árvore está vazia.
		if (this.raiz == null)
			return true;
		/// Caso contrário, a árvore não está vazia.
		else
			return false;
	}

	/// Método recursivo responsável por adicionar um aluno à árvore.
	/// Parâmetro "raizArvore": raiz da árvore ou sub-árvore em que o aluno será
	/// adicionado.
	/// Parâmetro "alunoNovo": aluno que deverá ser adicionado à árvore.
	/// Retorna a raiz atualizada da árvore ou sub-árvore em que o aluno foi
	/// adicionado.
	private NodoJogador adicionar(NodoJogador raizArvore, Jogador jogadorNovo) {
		/// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então
		/// um novo aluno é inserido.
		if (raizArvore == null)
			raizArvore = new NodoJogador(jogadorNovo);
		else {
			/// Se o número de matrícula do aluno armazenado na raiz da árvore for maior
			/// do que o número de matrícula do aluno que deverá ser inserido na árvore:
			/// adicione esse aluno à sub-árvore esquerda; e atualize a referência para a
			/// sub-árvore esquerda modificada.
			if (raizArvore.item.getId() > jogadorNovo.getId())
				raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
			else {
				/// Se o número de matrícula do aluno armazenado na raiz da árvore for menor
				/// do que o número de matrícula do aluno que deverá ser inserido na árvore:
				/// adicione esse aluno à sub-árvore direita; e atualize a referência para a
				/// sub-árvore direita modificada.
				if (raizArvore.item.getId() < jogadorNovo.getId())
					raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);
				else
					/// O número de matrícula do aluno armazenado na raiz da árvore é igual ao
					/// número de matrícula do aluno que deveria ser inserido na árvore.
					System.out.println("O aluno " + jogadorNovo.getNome() + ", cuja matricula e " + jogadorNovo.getId()
							+ ", ja foi inserido anteriormente na arvore.");
			}
		}
		/// Retorna a raiz atualizada da árvore ou sub-árvore em que o aluno foi
		/// adicionado.
		return raizArvore;
	}

	/// Metodo que encapsula a adição recursiva de alunos à árvore.
	/// Parâmetro "alunoNovo": aluno que deverá ser inserido na árvore.
	public void inserir(Jogador alunoNovo) {
		/// Chama o método recursivo "adicionar", que será responsável por adicionar,
		/// o aluno passado como parâmetro, à árvore.
		/// O método "adicionar" receberá, como primeiro parâmetro, a raiz atual da
		/// árvore; e, como segundo parâmetro, o aluno que deverá ser adicionado à
		/// árvore.
		/// Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo
		/// método "adicionar".
		this.raiz = adicionar(this.raiz, alunoNovo);
	}

//	/// Método recursivo responsável por localizar na árvore ou sub-árvore o
//	/// antecessor do nó que deverá ser retirado.
//	/// O antecessor do nó que deverá ser retirado da árvore corresponde
//	/// ao nó que armazena os dados do aluno cujo número de matrícula é o maior,
//	/// dentre os números de matrícula menores do que o número de matrícula do
//	/// nó que deverá ser retirado.
//	/// Depois de ser localizado na árvore ou sub-árvore,
//	/// o antecessor do nó que deverá ser retirado da árvore o substitui.
//	/// Adicionalmente, a árvore ou sub-árvore é atualizada com a remoção do
//	/// antecessor.
//	/// Parâmetro "alunoRetirar": referência ao nó que armazena o aluno que
//	/// deverá ser retirado da árvore.
//	/// Parâmetro "raizArvore": raiz da árvore ou sub-árvore em que o antecessor
//	/// do nó que deverá ser retirado deverá ser localizado.
//	/// Retorna: raiz atualizada da árvore ou sub-árvore após a remoção do
//	/// antecessor do nó que foi retirado da árvore.
//	private NodoJogador antecessor(NodoJogador alunoRetirar, NodoJogador raizArvore) {
//		/// Se o antecessor do nó que deverá ser retirado da árvore ainda não foi
//		/// encontrado...
//		if (raizArvore.direita != null) {
//			/// Pesquise o antecessor na sub-árvore direita.
//			raizArvore.direita = antecessor(alunoRetirar, raizArvore.direita);
//			return raizArvore;
//		}
//		/// O antecessor do nó que deverá ser retirado da árvore foi encontrado.
//		else {
//			/// O antecessor do nó que deverá ser retirado da árvore foi localizado e
//			/// deverá substitui-lo.
//			alunoRetirar.item.setNumMatricula(raizArvore.item.getId());
//			alunoRetirar.item.setNome(raizArvore.item.getNome());
//			alunoRetirar.item.setNota(raizArvore.item.getNota());
//			/// A raiz da árvore ou sub-árvore é atualizada com os descendentes à
//			/// esquerda do antecessor.
//			/// Ou seja, retira-se o antecessor da árvore.
//			return raizArvore.esquerda;
//		}
//	}
//
//	/// Método recursivo responsável por localizar um aluno na árvore e
//	/// retirá-lo da árvore.
//	/// Parâmetro "raizArvore": raiz da árvore ou sub-árvore da qual o aluno
//	/// será retirado.
//	/// Parâmetro "matricula": número de matrícula do aluno que deverá ser
//	/// localizado e removido da árvore.
//	/// Retorna a raiz atualizada da árvore ou sub-árvore da qual o aluno foi
//	/// retirado; ou null, caso não tenha sido localizado aluno com o número de
//	/// matrícula informado.
//	private NodoJogador retirar(NodoJogador raizArvore, int matricula) {
//		/// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e o
//		/// aluno, que deveria ser retirado dessa árvore, não foi encontrado.
//		/// Nesse caso, deve-se retornar null.
//		if (raizArvore == null) {
//			System.out.println("O aluno, cuja matricula e " + matricula + ", nao foi encontrado.");
//			return raizArvore;
//		} else {
//			/// O número de matrícula do aluno armazenado na raiz da árvore é igual ao
//			/// número de matrícula do aluno que deve ser retirado dessa árvore.
//			/// Ou seja, o aluno que deve ser retirado da árvore foi encontrado.
//			if (raizArvore.item.getId() == matricula) {
//				/// O nó da árvore que será retirado não possui descendentes à direita.
//				/// Nesse caso, os descendentes à esquerda do nó que está sendo retirado da
//				/// árvore passarão a ser descendentes do nó-pai do nó que está sendo
//				/// retirado.
//				if (raizArvore.direita == null)
//					return (raizArvore.esquerda);
//				else
//				/// O nó da árvore que será retirado não possui descendentes à esquerda.
//				/// Nesse caso, os descendentes à direita do nó que está sendo retirado da
//				/// árvore passarão a ser descendentes do nó-pai do nó que está sendo
//				/// retirado.
//				if (raizArvore.esquerda == null)
//					return (raizArvore.direita);
//				else {
//					/// O nó que está sendo retirado da árvore possui descendentes à esquerda e
//					/// à direita.
//					/// Nesse caso, o antecessor do nó que está sendo retirado é localizado na
//					/// sub-árvore esquerda desse nó.
//					/// O antecessor do nó que está sendo retirado da árvore corresponde
//					/// ao nó que armazena o aluno cujo número de matrícula é o maior,
//					/// dentre os números de matrícula menores do que o número de matrícula do
//					/// nó que está sendo retirado.
//					/// Depois de ser localizado na sub-árvore esquerda do nó que está sendo
//					/// retirado,
//					/// o antecessor desse nó o substitui.
//					/// A sub-árvore esquerda do nó que foi retirado é atualizada com a remoção
//					/// do antecessor.
//					raizArvore.esquerda = antecessor(raizArvore, raizArvore.esquerda);
//					/// Retorna a raiz atualizada da árvore ou sub-árvore da qual o aluno foi
//					/// retirado.
//					return (raizArvore);
//				}
//			} else {
//				/// Se o número de matrícula do aluno armazenado na raiz da árvore for maior
//				/// do que o número de matrícula do aluno que deverá ser localizado e
//				/// retirado da árvore:
//				/// pesquise e retire esse aluno da sub-árvore esquerda.
//				if (raizArvore.item.getId() > matricula)
//					raizArvore.esquerda = retirar(raizArvore.esquerda, matricula);
//				else
//					/// Se o número de matrícula do aluno armazenado na raiz da árvore for menor
//					/// do que o número de matrícula do aluno que deverá ser localizado e
//					/// retirado da árvore:
//					/// pesquise e retire esse aluno da sub-árvore direita.
//					raizArvore.direita = retirar(raizArvore.direita, matricula);
//				/// Retorna a raiz atualizada da árvore ou sub-árvore da qual o aluno foi
//				/// retirado.
//				return raizArvore;
//			}
//		}
//	}
//
//	/// Método que encapsula a retirada recursiva de um aluno da árvore.
//	/// Parâmetro "matriculaRemover": número de matrícula do aluno que deverá
//	/// ser localizado e removido da árvore.
//	public void remover(int matriculaRemover) {
//		/// Chama o método recursivo "retirar", que será responsável por pesquisar o
//		/// aluno, cujo número de matrícula foi passado como parâmetro, na árvore e
//		/// retirá-lo da árvore.
//		/// O método "retirar" receberá, como primeiro parâmetro, a raiz atual da
//		/// árvore; e, como segundo parâmetro, o número de matrícula do aluno que
//		/// deverá ser localizado e retirado dessa árvore.
//		this.raiz = retirar(this.raiz, matriculaRemover);
//	}

	public void imprimirEmOrdem() {
		imprimirEmOrdem(raiz);
	}

	private void imprimirEmOrdem(NodoJogador raizArvore) { // Caso queira imprirmir decrescente s� trocar o .esquerda

		if (raizArvore != null) { // Verificar nao o no de agora nao esta nulo
			imprimirEmOrdem(raizArvore.esquerda);
			System.out.print(raizArvore.item.getNome() + " | ");
			imprimirEmOrdem(raizArvore.direita);
		}

	}

	public Jogador menorNumeroMatricula() {
		Jogador menor = null;

		if (!arvoreVazia())
			menor = pesquisarMenor(raiz).item;

		return menor;
	}

	private NodoJogador pesquisarMenor(NodoJogador raizArvore) {

		if (raizArvore != null)
			if (raizArvore.esquerda == null)
				return raizArvore;
			else
				return pesquisarMenor(raizArvore.esquerda);
		else
			return null;

	}

	public Jogador buscar(int matricula) {
		Jogador pesquisado;

		NodoJogador resultado = pesquisar(raiz, matricula);

		if (resultado == null)
			pesquisado = null;
		else
			pesquisado = resultado.item;

		return pesquisado;
	}

	private NodoJogador pesquisar(NodoJogador raizArvore, int matricula) {

		NodoJogador pesquisado;

		if (raizArvore == null)
			pesquisado = null;
		else {
			if (raizArvore.item.getId() == matricula)
				pesquisado = raizArvore;
			else if (raizArvore.item.getId() > matricula)
				pesquisado = pesquisar(raizArvore.esquerda, matricula);
			else
				pesquisado = pesquisar(raizArvore.direita, matricula);
		}

		return pesquisado;
	}

	public int numJogadors() {
		return contarNumJogadors(raiz);
	}

	private int contarNumJogadors(NodoJogador raizArvore) {

		if (raizArvore == null)
			return 0;
		else
			return 1 + contarNumJogadors(raizArvore.esquerda) + contarNumJogadors(raizArvore.direita);
	}

}
