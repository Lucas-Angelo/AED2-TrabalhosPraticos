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

void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr);
void criarJogadores(Jogador *jogadores, int ids[], int i);
Jogador copiarJogador (Jogador jogador);
void imprimirJogador(Jogador *jogadores, int tam);
//void selecao(Jogador *players, int n, int indiceI, int indiceJ, int menor);
void selecao(Jogador *vetor, int i, int j, int size, int flag);

int main( )
{
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

    Jogador *playersEntrada = (Jogador *) calloc(sizeof(Jogador), i);

    criarJogadores(playersEntrada, ids, i);

    selecao(playersEntrada, 0, 0, i, 0);

    imprimirJogador(playersEntrada, i);

    free(playersEntrada);
    playersEntrada = NULL;

    return 0;
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

void criarJogadores(Jogador *jogadores, int ids[], int tamVetor)
{
    FILE *arquivo;
    arquivo = fopen("players.csv", "r");

    char linha[255];
    char *informacoes_linha[8];
    int i=0;
    while (fgets(linha, sizeof(linha), arquivo))  // Enquando não chegar no fim do arquivo..
    {

        strSplit(linha, informacoes_linha, ',', 8); //Separa os campos e os armazena no vetor de 3 posições chamado informacoes_linha
        if(strcmp(informacoes_linha[0], "id")!=0){

            for(int z=0; z<tamVetor; z++){
                if(atoi(informacoes_linha[0])==ids[z]){
                    jogadores[i].nome = (Jogador *) calloc(sizeof(char), strlen(informacoes_linha[1]));
                    jogadores[i].universidade = (Jogador *) calloc(sizeof(char), strlen(informacoes_linha[4]));
                    jogadores[i].cidadeNascimento = (Jogador *) calloc(sizeof(char), strlen(informacoes_linha[6]));
                    jogadores[i].estadoNascimento = (Jogador *) calloc(sizeof(char), strlen(informacoes_linha[7]));
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

    }
}

Jogador copiarJogador (Jogador jogador){
    return jogador;
}

void imprimirJogador(Jogador *jogadores, int tam){

    for(int i=0; i<tam; i++){
        printf("[%d ## ", jogadores[i].id);

        printf("%s ## ", jogadores[i].nome);

        printf("%d ## ", jogadores[i].altura);

        printf("%d ## ", jogadores[i].peso);

        printf("%d ## ", jogadores[i].anoNascimento);

        if (strcmp(jogadores[i].universidade,"")==0) { // Se o dado esta vazio
            printf("nao informado ## ");
        }
        else {
            printf("%s ## ", jogadores[i].universidade);
        }

        if (!strlen(jogadores[i].cidadeNascimento)> 0) { // Se o dado esta vazio
            printf("nao informado ## ");
        }
        else {
            printf("%s ## ", jogadores[i].cidadeNascimento);
        }

        if (!strlen(jogadores[i].estadoNascimento) > 0) { // Se o dado esta vazio
            printf("nao informado]\n");
        }
        else {
            printf("%s]\n", jogadores[i].estadoNascimento);
        }
    }
}

/*void selecao(Jogador *vetor, int n, int i, int j, int menor) {

    if(i < (n - 1)){
        if(j < n){
            if( (strlen(vetor[j].cidadeNascimento) != 0 && strlen(vetor[menor].cidadeNascimento) == 0)
               || (strlen(vetor[j].cidadeNascimento) != 0 && strcmp(vetor[menor].cidadeNascimento, vetor[j].cidadeNascimento) > 0)
               || (strcmp(vetor[menor].cidadeNascimento, vetor[j].cidadeNascimento) == 0 && strcmp(vetor[menor].nome, vetor[j].nome) > 0) ){
                menor = j;
            }
            selecao(vetor, n, i, j+1, menor);
        }
        if(j==n){

            Jogador temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;

            selecao(vetor, n, i+1, i+2, i+1);
        }
    }
}*/

void selecao(Jogador *vetor, int i, int j, int size, int flag)
{
    Jogador temp;

    if (i < size - 1)
    {
        if (flag)
        {
            j = i + 1;
        }
        if (j < size)
        {
            //if (list[i] > list[j])
            if ( (strlen(vetor[i].cidadeNascimento) == 0 && strlen(vetor[j].cidadeNascimento) != 0)
               || (strlen(vetor[i].cidadeNascimento) != 0 && strcmp(vetor[j].cidadeNascimento, vetor[i].cidadeNascimento) < 0)
               || (strcmp(vetor[j].cidadeNascimento, vetor[i].cidadeNascimento) == 0 && strcmp(vetor[j].nome, vetor[i].nome) < 0) )
            {
                temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
            selecao(vetor, i, j + 1, size, 0);
        }
        selecao(vetor, i + 1, 0, size, 1);
    }
}
