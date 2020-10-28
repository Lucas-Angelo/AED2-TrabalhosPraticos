public class Lista {

    private int tamanho;
    private Celula primeiro;
    private Celula ultimo;

    Lista (){
        primeiro = new Celula();
        ultimo = primeiro;
        tamanho=0;
    }

    public void inserirInicio (Jogador player){
        Celula aux = primeiro.proximo;
        primeiro.proximo = new Celula();
        primeiro.proximo.proximo = aux;

        primeiro.proximo.item = player.clone();

        tamanho++;
    }

    public void inserir (Jogador player, int posicao){
        
        if (posicao == 1)
            inserirInicio(player);

        else if (posicao == tamanho)
            inserirFim(player);

        else{
            Celula find = primeiro;

            for (int i=0 ; i<posicao; i++){
                find = find.proximo;
            }

            Celula aux = find.proximo;
            find.proximo = new Celula();
            find.proximo.proximo = aux;

            find.proximo.item = player.clone();

            tamanho++;
        }
    }

    public void inserirFim (Jogador player){
        ultimo.proximo = new Celula();
        ultimo = ultimo.proximo;

        ultimo.item = player.clone();

        tamanho++;
    }

    public Jogador removerInicio(){
        if (primeiro == ultimo){
            System.out.println("Fail to remove: The list is empty");
            return null;
        }

        Jogador toReturn = new Jogador();
        toReturn = primeiro.proximo.item.clone();

        primeiro.proximo = primeiro.proximo.proximo;

        tamanho--;

        return toReturn;
    }

    public Jogador remover(int posicao){
        if (primeiro == ultimo){
            System.out.println("Fail to remove: The list is empty");
            return null;
        }

        if (posicao == 1)
            return removerInicio();

        else if (posicao == tamanho+1)
            return removerFim();


        Celula find = primeiro;
        for (int i=0;i<posicao;i++){
            find = find.proximo;
        }

        Jogador toReturn = new Jogador();
        toReturn = find.proximo.item.clone();
        
        find.proximo = find.proximo.proximo;

        tamanho--;

        return toReturn;
    }

    public Jogador removerFim(){
        if (primeiro == ultimo){
            System.out.println("Fail to remove: The list is empty");
            return null;
        }

        Jogador toReturn = new Jogador();
        toReturn = ultimo.item.clone();

        Celula find = primeiro;
        for (int i=0;i<tamanho-1;i++){
            find = find.proximo;
        }

        ultimo = find;
        ultimo.proximo = null;

        tamanho--;

        return toReturn;
    }

    public void mostrar(){
        Celula atual = primeiro.proximo;
        for (int i=0 ; i<tamanho ; i++ , atual = atual.proximo){
            System.out.printf("[%d] ", i);
            atual.item.imprimir();
        }
    }

}