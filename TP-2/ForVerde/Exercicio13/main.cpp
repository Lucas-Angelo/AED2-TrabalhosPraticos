#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

typedef struct Jogador {
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[150];
    int anoNascimento;
    char cidadeNascimento[150];
    char estadoNascimento[150];
} Jogador;

int qtdLinhas();
void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr);
void criarJogadores(Jogador jogadores[]);
Jogador copiarJogador (Jogador jogador);
void imprimirJogador(Jogador jogadores);
void selecao(Jogador players[], int n, int indiceI, int indiceJ, int menor,  time_t t_ini);

int qtdJogadores;

int main( )
{
    time_t t_ini;
    t_ini = time(NULL);

    qtdJogadores = qtdLinhas();
    Jogador players[qtdJogadores];

    criarJogadores(players);

    int finaliza;
    char texto[50];
    int i=0;
    int ids[1000];

    do {
        fgets(texto, sizeof(texto), stdin);
        finaliza = strcmp(texto, "FIM\n");

        if(finaliza){
            ids[i] = atoi(texto);
            i++;
        }

    } while (finaliza);

    Jogador playersEntrada[i];

    for(int j=0; j<i; j++){
        playersEntrada[j] = copiarJogador(players[ids[j]]);
    }

    selecao(playersEntrada, i, 0, 1, 0, t_ini);

    for(int j=0; j<i; j++){
        imprimirJogador(playersEntrada[j]);
    }


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

Jogador copiarJogador (Jogador jogador){
    return jogador;
}

void imprimirJogador(Jogador jogadores){
    printf("[%d ## ", jogadores.id);

    printf("%s ## ", jogadores.nome);

    printf("%d ## ", jogadores.altura);

    printf("%d ## ", jogadores.peso);

    printf("%d ## ", jogadores.anoNascimento);

    if (strcmp(jogadores.universidade,"")==0) { // Se o dado esta vazio
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
        printf("nao informado]\n");
    }
    else {
        printf("%s]\n", jogadores.estadoNascimento);
    }
}

int comparacoes=0, movimentacoes=0;

void selecao(Jogador vetor[], int n, int i, int j, int menor, time_t t_ini) {

    if(i < (n - 1)){
        if(j < n){
            if( (strlen(vetor[j].cidadeNascimento) != 0 && strlen(vetor[menor].cidadeNascimento) == 0)
               || (strlen(vetor[j].cidadeNascimento) != 0 && strcmp(vetor[menor].cidadeNascimento, vetor[j].cidadeNascimento) > 0)
               || (strcmp(vetor[menor].cidadeNascimento, vetor[j].cidadeNascimento) == 0 && strcmp(vetor[menor].nome, vetor[j].nome) > 0) ){
                menor = j;
            }
            comparacoes++;
            selecao(vetor, n, i, j+1, menor, t_ini);
        }
        if(j==n){

            Jogador temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
            movimentacoes++;

            selecao(vetor, n, i+1, i+2, i+1, t_ini);
        }
    }

    float tempo;
    time_t t_fim;
    t_fim = time(NULL);

    tempo = difftime(t_fim, t_ini);

    FILE *pont_log;

    pont_log = fopen ("matricula_selecaoRecursiva_C.txt", "wt");
    fprintf (pont_log, "705903,692669,689603 %f %d %d", tempo, comparacoes, movimentacoes);
    fclose (pont_log);

}
