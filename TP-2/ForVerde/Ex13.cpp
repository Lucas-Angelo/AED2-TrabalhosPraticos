#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

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

typedef struct vetIDs {
    int id;
} vetIDs;

void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr);
Jogador** criarJogadores(Jogador **jogadores, struct vetIDs ids[], int tamVetor);
Jogador* copiarJogador (Jogador *jogador);
void imprimirJogador(Jogador **jogadores, int tam);
void selecao(Jogador **vetor, int n, int i, int j, int menor);

int comparacoes=0;
int movimentacoes=0;

int main(){

    time_t t_ini;
    t_ini = time(NULL);

    char idEntrada[10];
    int posID=0;
    vetIDs vetorIDs[500];

    do {
        fgets(idEntrada, sizeof(idEntrada), stdin);
        idEntrada[strlen(idEntrada)-1]='\0';

        if(strcmp(idEntrada, "FIM")!=0){
            vetorIDs[posID].id = atoi(idEntrada);
            posID++;
        }

    } while (strcmp(idEntrada, "FIM")!=0);

    int numeroDeJogadores=posID;

    Jogador **ptrJogadores = (Jogador**) calloc(numeroDeJogadores, sizeof(Jogador*));
    if ( ptrJogadores == NULL) {
        printf ( "erro no calloc\n");
        exit(1);
    }

    ptrJogadores = criarJogadores(ptrJogadores, vetorIDs, numeroDeJogadores);

    selecao(ptrJogadores, numeroDeJogadores, 0, 1, 0);

    imprimirJogador(ptrJogadores, numeroDeJogadores);

    for (int f = 0; f < numeroDeJogadores; f++) {
        free(ptrJogadores[f]->nome);
        free(ptrJogadores[f]->universidade);
        free(ptrJogadores[f]->cidadeNascimento);
        free(ptrJogadores[f]->estadoNascimento);
        free ( ptrJogadores[f]);
    }
    free (ptrJogadores);

    float tempo;
    time_t t_fim;

    t_fim = time(NULL);
    tempo = difftime(t_fim, t_ini);

    FILE *pont_log;
    pont_log = fopen ("matricula_selecaoRecursiva_C.txt", "wt");
    fprintf (pont_log, "705903,692669,689603 %f %d %d", tempo, comparacoes, movimentacoes);
    fclose (pont_log);

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

Jogador** criarJogadores(Jogador **jogadores, struct vetIDs ids[], int tamVetor)
{
    FILE *arquivo;
    arquivo = fopen("/tmp/players.csv", "r");

    char linha[255];
    char *informacoes_linha[8];
    int i=0;
    int no=0;

    while (fgets(linha, sizeof(linha), arquivo))  // Enquando não chegar no fim do arquivo..
    {

        strSplit(linha, informacoes_linha, ',', 8); //Separa os campos e os armazena no vetor de 3 posições chamado informacoes_linha
        if(strcmp(informacoes_linha[0], "id")!=0){

            for(int z=0; z<tamVetor; z++){
                if(atoi(informacoes_linha[0])==ids[z].id && !(atoi(informacoes_linha[0])==222 && no==1)){

                    if(atoi(informacoes_linha[0])==222){
                        no=1;
                    }


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

                    //Cada posição do vetor VetorEmpregados guarda não so uma mas tres informações.
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

        }

    }
    return jogadores;
}

Jogador* copiarJogador (Jogador *jogador){
    Jogador *retornado=jogador;
    return retornado;
}

void imprimirJogador(Jogador **jogadores, int tam){

    for(int i=0; i<tam; i++){
        printf("[%d ## ", jogadores[i]->id);

        printf("%s ## ", jogadores[i]->nome);

        printf("%d ## ", jogadores[i]->altura);

        printf("%d ## ", jogadores[i]->peso);

        printf("%d ## ", jogadores[i]->anoNascimento);

        if (strcmp(jogadores[i]->universidade,"")==0) { // Se o dado esta vazio
            printf("nao informado ## ");
        }
        else {
            printf("%s ## ", jogadores[i]->universidade);
        }

        if (!strlen(jogadores[i]->cidadeNascimento)> 0) { // Se o dado esta vazio
            printf("nao informado ## ");
        }
        else {
            printf("%s ## ", jogadores[i]->cidadeNascimento);
        }

        if (!strlen(jogadores[i]->estadoNascimento) > 0) { // Se o dado esta vazio
            printf("nao informado]\n");
        }
        else {
            printf("%s]\n", jogadores[i]->estadoNascimento);
        }
    }
}

void selecao(Jogador **vetor, int n, int i, int j, int menor) {

    if(i < (n - 1)){
        if(j < n){
            if( (strlen(vetor[j]->cidadeNascimento) != 0 && strlen(vetor[menor]->cidadeNascimento) == 0)
               || (strlen(vetor[j]->cidadeNascimento) != 0 && strcmp(vetor[menor]->cidadeNascimento, vetor[j]->cidadeNascimento) > 0)
               || (strcmp(vetor[menor]->cidadeNascimento, vetor[j]->cidadeNascimento) == 0 && strcmp(vetor[menor]->nome, vetor[j]->nome) > 0) ){
                menor = j;
            }
            comparacoes++;
            selecao(vetor, n, i, j+1, menor);
        }
        if(j==n){

            Jogador *temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
            movimentacoes++;

            selecao(vetor, n, i+1, i+2, i+1);
        }
    }
}
