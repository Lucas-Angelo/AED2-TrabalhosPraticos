#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct Jogador {
    int id;
    char *nome;
    int altura;
    int peso;
    char *universidade;
    int anoNascimento;
    char *cidadeNascimento;
    char *estadoNascimento;
} Jogador;

typedef struct Desempilhados {
    char nome[50];
} Desempilhados;

int tamanho;

int qtdLinhas();
void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr);
Jogador** criarJogadores(Jogador **jogadores);
int qtdJogadores;

void construtorPilha();
void empilhar(Jogador **pilha, Jogador *jogador);
Jogador* desempilhar(Jogador **pilha);
int pilhaVazia();
void mostrar(Jogador **pilha);

int main( )
{
    construtorPilha();
    qtdJogadores = qtdLinhas();

    Jogador **players = (Jogador**) calloc(qtdJogadores, sizeof(Jogador*));
    if ( players == NULL) {
        printf ( "erro no calloc\n");
        exit(1);
    }

    Jogador **pilha = (Jogador**) calloc(qtdJogadores, sizeof(Jogador*));

    players = criarJogadores(players);

    int finaliza;
    char texto[50];

    do {
        fgets(texto, sizeof(texto), stdin);
        finaliza = strcmp(texto, "FIM\n");

        if(finaliza){
            empilhar(pilha, players[atoi(texto)]);
        }

    } while (finaliza);

    char acao[50];
    fgets(texto, sizeof(texto), stdin);
    int i = 0, cont=0, qtdDesem=0;
    int qtd = atoi(texto);
    Desempilhados vetorDesempilhados[qtd];

    while (i < qtd) {

        fgets(acao, sizeof(acao), stdin);

        if (acao[0] == 'I') {

            char idString[10];

            for(int n=0; n<10; n++)
                idString[n]='\0';

            int verif=0;
            for(int p=0; p<strlen(acao)-1; p++) {
                if(verif==1) {
                    idString[cont]=acao[p];
                    cont++;
                }
                if(acao[p]==' ')
                    verif=1;
            }
            cont=0;

            empilhar(pilha, players[atoi(idString)]);

        } else {

            strcpy(vetorDesempilhados[qtdDesem].nome, desempilhar(pilha)->nome);
            qtdDesem++;

        }

        i++;
    }

    for(int z=0; z<qtdDesem; z++)
        printf("(R) %s\n", vetorDesempilhados[z].nome);

    mostrar(pilha);

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

Jogador** criarJogadores(Jogador **jogadores)
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

            jogadores[i] = (Jogador*) malloc( sizeof(struct Jogador));
            if ( jogadores[i] == NULL) {
                printf ( "erro no malloc linha");
                exit ( 1);
            }

            jogadores[i]->nome = (char*) malloc( 1 + strlen(informacoes_linha[1]));// memoria para nome + 1 para o '\0'
            if ( jogadores[i]->nome == NULL) {
                printf ( "falha no nome malloc\n");
                exit (1);
            }
            jogadores[i]->universidade = (char*) malloc( 1 + strlen(informacoes_linha[4]));
            if ( jogadores[i]->universidade == NULL) {
                printf ( "falha no universidade malloc\n");
                exit (1);
            }

            jogadores[i]->cidadeNascimento = (char*) malloc( 1 + strlen(informacoes_linha[6]));
            if ( jogadores[i]->cidadeNascimento == NULL) {
                printf ( "falha no cidadeNascimento malloc\n");
                exit (1);
            }

            jogadores[i]->estadoNascimento = (char*) malloc( 1 + strlen(informacoes_linha[7]));
            if ( jogadores[i]->estadoNascimento == NULL) {
                printf ( "falha no estadoNascimento malloc\n");
                exit (1);
            }

            jogadores[i]->id = atoi(informacoes_linha[0]);
            strcpy(jogadores[i]->nome, ((const char*)(informacoes_linha[1])));
            jogadores[i]->altura = atoi(informacoes_linha[2]);
            jogadores[i]->peso = atoi(informacoes_linha[3]);
            strcpy(jogadores[i]->universidade, ((const char*)informacoes_linha[4]));
            jogadores[i]->anoNascimento = atoi(informacoes_linha[5]);
            strcpy(jogadores[i]->cidadeNascimento, ((const char*)informacoes_linha[6]));
            strcpy(jogadores[i]->estadoNascimento, ((const char*)informacoes_linha[7]));

            i++;
        }

    }

    return jogadores;
}
Jogador copiarJogador (Jogador jogador){
    return jogador;
}

void construtorPilha(){
    tamanho=0;
}

void empilhar(Jogador **pilha, Jogador *jogador){
    pilha[tamanho]=jogador;
    tamanho++;
}

Jogador* desempilhar(Jogador **pilha){
    Jogador *retornado;

    if(!pilhaVazia()){
        retornado = pilha[tamanho-1];
        tamanho--;
    }

    return retornado;
}

int pilhaVazia(){
    int resp;

    if(tamanho==0)
        resp=1;
    else
        resp=0;

    return resp;
}

void mostrar(Jogador **pilha){

    for(int i=0; i<tamanho; i++){

        printf("[%d] ## ", i);

        printf("%d ## ", pilha[i]->id);

        printf("%s ## ", pilha[i]->nome);

        printf("%d ## ", pilha[i]->altura);

        printf("%d ## ", pilha[i]->peso);

        printf("%d ## ", pilha[i]->anoNascimento);

        if (strcmp(pilha[i]->universidade,"")==0) { // Se o dado esta vazio
            printf("nao informado ## ");
        }
        else {
            printf("%s ## ", pilha[i]->universidade);
        }

        if (strlen(pilha[i]->cidadeNascimento) <= 0) { // Se o dado esta vazio
            printf("nao informado ## ");
        }
        else {
            printf("%s ## ", pilha[i]->cidadeNascimento);
        }

        if (strlen(pilha[i]->estadoNascimento) <= 0) { // Se o dado esta vazio
            printf("nao informado ##\n");
        }
        else {
            printf("%s ##\n", pilha[i]->estadoNascimento);
        }

    }

}
