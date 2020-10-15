#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct Jogador {
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[50];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogador;

int qtdLinhas();
void strSplit(char *strTOsplit,char *strArr[], char *strSeparet,int nArr);
void lerJogadores(Jogador jogadores[]);
void imprimirTodos(Jogador jogadores[]);

int qtdJogadores;

int main( )
{
    qtdJogadores = qtdLinhas();
    Jogador players[qtdJogadores];

    criarJogadores(players);
    imprimirTodos(players);


    return 0;
}

int qtdLinhas(){
    FILE *arquivo = fopen("players.csv", "r");
    int qtd=0;
    char linha[255];
    char *informacoes_linha[8];
    while(fgets(linha, sizeof(linha), arquivo)){
        strSplit(linha, informacoes_linha, ",",8);
        if(strcmp(informacoes_linha[0], "id")!=0)
            qtd++;
    }
    return qtd;
}

void strSplit(char *strTOsplit,char *strArr[], char *strSeparet,int nArr)
{
    int i = 0;
    char * pch;

    pch = strtok (strTOsplit,strSeparet);
    for(i = 0;i < nArr;i++)
    {
        //printf ("%s\n",pch);
        if (pch==NULL)
            strcpy (strArr[i], "");
        else
            strArr[i] = pch;
        pch = strtok (NULL,strSeparet);
    }
}

void criarJogadores(Jogador jogadores[])
{
    FILE *arquivo;
    arquivo = fopen("players.csv", "r");

    char linha[255];
    char *result;
    char *informacoes_linha[8];
    int i=0, j;
    while (fgets(linha, sizeof(linha), arquivo))  // Enquando não chegar no fim do arquivo..
    {

        strSplit(linha, informacoes_linha, ",", 8); //Separa os campos e os armazena no vetor de 3 posições chamado informacoes_linha
        if(strcmp(informacoes_linha[0], "id")!=0){
            //isso é uma função eveline :)
            //Cada posição do vetor VetorEmpregados guarda não so uma mas tres informações.
            char aux[50];
            jogadores[i].id = atoi(informacoes_linha[0]);
            strcpy(jogadores[i].nome, ((const char*)(informacoes_linha[1])));
            jogadores[i].altura = atoi(informacoes_linha[2]);
            jogadores[i].peso = atoi(informacoes_linha[3]);
            strcpy(aux,informacoes_linha[4]);
            for (j=0;j<strlen(aux);j++){
                if (aux[j]=='\n')
                    aux[j] = '\0';
            }
            strcpy(jogadores[i].universidade, ((const char*)(aux)));
            jogadores[i].anoNascimento = atoi(informacoes_linha[5]);
            strcpy(aux,informacoes_linha[6]);
            for (j=0;j<strlen(aux);j++){
                if (aux[j]=='\n')
                    aux[j] = '\0';
            }
            strcpy(jogadores[i].cidadeNascimento, ((const char*)(aux)));
            printf("%s\n", jogadores[i].universidade);
            strcpy(aux,informacoes_linha[7]);
            for (j=0;j<strlen(aux);j++){
                if (aux[j]=='\n')
                    aux[j] = '\0';
            }
            strcpy(jogadores[i].estadoNascimento, ((const char*)(aux)));
            i++;
        }

    }
}
Jogador copiarJogador (Jogador jogador){
    return jogador;
}
void imprimirJogador(Jogador jogadores){
    printf("\n[%d ## ", jogadores.id);

    printf("%s ## ", jogadores.nome);

    printf("%d ## ", jogadores.altura);

    printf("%d ## ", jogadores.peso);

    printf("%d ## ", jogadores.anoNascimento);

    if (strcmp(jogadores.universidade,"0")==0) { // Se o dado esta vazio
        printf("nao informado ## ");
    }
    else {
        printf("%s ## ", jogadores.universidade);
    }

    if (!strlen(jogadores.cidadeNascimento)> 0) { // Se o dado esta vazio
        printf("nao informado ## ");
    }
    else {
        printf("%s ## ", jogadores.cidadeNascimento);
    }

    if (!strlen(jogadores.estadoNascimento) > 0) { // Se o dado esta vazio
        printf("nao informado]");
    }
    else {
        printf("%s]", jogadores.estadoNascimento);
    }
}

void imprimirTodos(Jogador jogadores[])
{
    int i;
    for (i = 0; i < qtdJogadores; i++) {
        imprimirJogador(jogadores[i]);
    }
}
