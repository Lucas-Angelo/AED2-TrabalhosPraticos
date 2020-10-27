public class Pilha {

    private int tamanho;
    private int topo;
    private Jogador players[];

    Pilha (){
        topo = 0;
        tamanho = 4000;
        players = new Jogador[tamanho];
        topo = -1;
    }
    Pilha (int size){
        topo = 0;
        tamanho = size;
        players = new Jogador[tamanho];
        topo = -1;
    }

    public void empilhar (Jogador player){
        topo++;
        if (topo == tamanho){
            System.out.println("Fail to stack up: Stack is already full");
            topo--;
        }
        else{
            players[topo] = player.clone();
        }
    }

    public Jogador desemplihar(){
        if (topo == -1){
            System.out.println("Fail to unstack: Stack is empty");
            return null;
        }
        Jogador aux = new Jogador();
        aux = players[topo].clone();
        players[topo] = null;
        topo--;
        return aux;
    }

    public void mostrar(){
        for (int i=0;i<topo+1;i++){
            System.out.printf("[%d] ", i);
            players[i].imprimir();
        }
    }

}