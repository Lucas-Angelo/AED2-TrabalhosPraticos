#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef struct Jogador{
  int id;
	char nome[75];
	int altura;
	int peso;
	char universidade[75];
	int anoNascimento;
	char cidadeNascimento[75];
	char estadoNascimento[75];
}Jogador;

Jogador* newJogadorV();

Jogador* newJogador(int id, char* nome, int altura, int peso, char* universidade, int anoNascimento, char* cidadeNascimento, char* estadoNascimento);

Jogador* clone( Jogador* toClone);

void imprimir(Jogador* toPrint);



typedef struct Celula Celula;

typedef struct Celula {
  Jogador* item;
  Celula* proximo;
} Celula;

Celula* newCelula();

//Fila
typedef struct Fila{
  Celula* frente;
  Celula* traz;
  int quantidade;
} Fila;

Fila* newFila();

void enfileirar (Fila* line,Jogador* player);

Jogador* desenfileirar (Fila* line);

void mostrarFila (Fila* line);

double  obterMediaAltura(Fila* line);




int qtdLinhas();
void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr);
void criarJogadores(Jogador jogadores[]);

int qtdJogadores;

int main(void) {
    qtdJogadores = qtdLinhas();
    Jogador players[qtdJogadores];

    criarJogadores(players);

    int finaliza;
    char texto[50];

    Fila *line = newFila();

    do {
        fgets(texto, sizeof(texto), stdin);
        finaliza = strcmp(texto, "FIM\n");

        if(finaliza){
            enfileirar(line, &players[atoi(texto)]);
            printf("%.0f\n", obterMediaAltura(line));
        }

    } while (finaliza);

    int qtdcomandos;
    int toLineUpId;

    scanf("%d", &qtdcomandos);

    char comando[10];
    int i;
    for (i=0;i<qtdcomandos;i++){

        fgets(comando, sizeof(comando), stdin);

        if (comando[0] == 'I'){

            toLineUpId = atoi(strpbrk(comando, " "));
            enfileirar(line, &players[toLineUpId]);
            printf("%.0f\n", obterMediaAltura(line));
        }
        else if (comando[0] == 'R')
            printf("(R) %s\n", desenfileirar(line)->nome);
        else
          i--;
            
    }

    mostrarFila(line);
    
    return 0;
}

int qtdLinhas(){
    FILE *arquivo = fopen("/tmp/players.csv", "r");
    int qtd=0;
    char linha[255];
    char *informacoes_linha[8];
    while(fgets(linha, sizeof(linha), arquivo)){
        strSplit(linha, informacoes_linha, ',',8);
        if(strcmp(informacoes_linha[0], "id")!=0)
            qtd++;
    }
    return qtd;
}

void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr)
{
    /*(strTOsplit[j] != strSeparet || i==nArr-1) && (strTOsplit[j] != '\n' && strTOsplit[j] != '\0')*/
    int i, j = 0;
    for (i=0;i<nArr;i++){
        strArr[i] = (char *) calloc( sizeof(char), 150 );
        strcpy(strArr[i],"");
        for (;(strTOsplit[j] != strSeparet || i==nArr-1) && (strTOsplit[j] != '\n' && strTOsplit[j] != '\0');j++){
            int tamanho = strlen(strArr[i]);
            strArr[i][tamanho] = strTOsplit[j];
            strArr[i][tamanho+1] = '\0';
        }
        j++;
    }
}

void criarJogadores(Jogador jogadores[])
{
    FILE *arquivo;
    arquivo = fopen("/tmp/players.csv", "r");

    char linha[255];
    char *informacoes_linha[8];
    int i=0;
    while (fgets(linha, sizeof(linha), arquivo))  // Enquando não chegar no fim do arquivo..
    {

        strSplit(linha, informacoes_linha, ',', 8); //Separa os campos e os armazena no vetor de 3 posições chamado informacoes_linha
        if(strcmp(informacoes_linha[0], "id")!=0){
            //isso é uma função eveline :)
            //Cada posição do vetor VetorEmpregados guarda não so uma mas tres informações.
            jogadores[i].id = atoi(informacoes_linha[0]);
            strcpy(jogadores[i].nome, ((const char*)(informacoes_linha[1])));
            jogadores[i].altura = atoi(informacoes_linha[2]);
            jogadores[i].peso = atoi(informacoes_linha[3]);
            strcpy(jogadores[i].universidade, ((const char*)informacoes_linha[4]));
            jogadores[i].anoNascimento = atoi(informacoes_linha[5]);
            strcpy(jogadores[i].cidadeNascimento, ((const char*)informacoes_linha[6]));
            strcpy(jogadores[i].estadoNascimento, ((const char*)informacoes_linha[7]));
            i++;
        }

    }
}



Jogador* newJogadorV(){
  Jogador *player = (Jogador *) malloc(sizeof(Jogador));
  return player;
}

Jogador* newJogador(int id, char* nome, int altura, int peso, char* universidade, int anoNascimento, char* cidadeNascimento, char* estadoNascimento){

  Jogador* player = (Jogador*) malloc(sizeof(Jogador));

  player->id = id;
  strcpy(player->nome, nome);
  player->altura = altura;
  player->peso = peso;
  strcpy(player->universidade, universidade);
  player->anoNascimento = anoNascimento;
  strcpy(player->cidadeNascimento, cidadeNascimento);
  strcpy(player->estadoNascimento, estadoNascimento);

  return player;
}

Jogador* clone( Jogador* toClone) {

		Jogador *jogador = newJogador(toClone->id,toClone->nome,toClone->altura,toClone->peso,toClone->universidade,toClone->anoNascimento,toClone->cidadeNascimento,toClone->estadoNascimento);

		return jogador;
	}

	void imprimir(Jogador* toPrint) {

		printf("## %d ## ", toPrint->id);

		printf("%s ## ", toPrint->nome);

		printf("%d ## ", toPrint->altura);

		printf("%d ## ", toPrint->peso);

		printf("%d ## ", toPrint->anoNascimento);

		if (strlen(toPrint->universidade) == 0) { // Se o dado esta vazio
			printf("nao informado ## ");
		} else {
			printf("%s ## ", toPrint->universidade);
		}

		if (strlen(toPrint->cidadeNascimento) == 0) { // Se o dado esta vazio
			printf("nao informado ## ");
		} else {
			printf("%s ## ", toPrint->cidadeNascimento);
		}

		if (strlen(toPrint->estadoNascimento) == 0) { // Se o dado esta vazio
			printf("nao informado ##\n");
		} else {
			printf("%s ##\n", toPrint->estadoNascimento);
		}

	}



//celula
Celula* newCelula(){
  Celula* cell = (Celula*) malloc(sizeof(Celula));
  cell->item = newJogadorV();
  cell->proximo = NULL;

  return cell;
}

//Fila
Fila* newFila(){
  Fila* line = (Fila*) malloc(sizeof(Fila));
  line->frente = line->traz = newCelula();
  line->quantidade = 0;

  return line;
}

void enfileirar (Fila* line,Jogador* player){
  
  if (line->quantidade == 5)
    desenfileirar(line);

  Celula *aux = newCelula();

  aux = line->traz;

  line->traz = newCelula();
  line->traz->item = player;
  line->traz->proximo = NULL;

  aux->proximo = line->traz;

  line->quantidade++;
  
}

Jogador* desenfileirar (Fila* line){
  if (line->frente == line->traz)//linea vazia
    return NULL;

  Celula *aux = newCelula();
  aux = line->frente->proximo;

  line->frente->proximo = line->frente->proximo->proximo;
  line->quantidade--;

  Jogador *player = clone(aux->item);
  free(aux);

  return player;
}

void mostrarFila (Fila* line){
  int i;
  Celula *atual;
  for ( atual = line->frente->proximo , i=0 ; atual!=NULL ; atual = atual->proximo , i++){
    printf("[%d] ", i);
    imprimir(atual->item);
  }

}

double  obterMediaAltura(Fila* line){
  int somaAltura=0;

  for ( Celula *atual = line->frente->proximo ; atual!=NULL ; atual = atual->proximo ){
    somaAltura += atual->item->altura;
  }

  double mediaAltura = line->quantidade!=0 ? (double) somaAltura/line->quantidade : 0;
  return mediaAltura;
}
