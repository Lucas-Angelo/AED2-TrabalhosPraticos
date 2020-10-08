import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String idInformado = new String();
		
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
		
		String linhaLida;
		
		Jogador player = new Jogador();

		
		
		do {
			idInformado = in.readLine();
			
			if(!(idInformado.equals("FIM"))) {
				
				leitura.abrirArquivo("players.csv");
				
				leitura.ler(); // O cabecalho, tem que pular a primeira linha
				linhaLida = leitura.ler();
	            while (linhaLida != null){
	            	
	                String[] linhasArray = linhaLida.split("\n"); // Capturar linha
	                
	                for(String linhaUnica : linhasArray)
	                {
	                    
	                    String[] dadosDaLinha = linhaUnica.split(",", 8); // Dividir os dados da linha
	                    
	                    // Setar dados da jogador atual na classe.
	                    if( idInformado.equals(dadosDaLinha[0].toString())) {
		                    player.setId(Integer.parseInt((dadosDaLinha[0].toString()))); // Transforma array em string, para transformar em int
		                    player.setNome(dadosDaLinha[1].toString());
		                    player.setAltura(Integer.parseInt((dadosDaLinha[2].toString())));
		                    player.setPeso(Integer.parseInt((dadosDaLinha[3].toString())));
		                    player.setUniversidade(dadosDaLinha[4].toString());
		                    player.setAnoNascimento(Integer.parseInt((dadosDaLinha[5].toString())));
		                    player.setCidadeNascimento(dadosDaLinha[6].toString());
		                    player.setEstadoNascimento(dadosDaLinha[7].toString());
	                    }
	                    
	                }
                    
	                linhaLida = leitura.ler();
	            }
	            
	            player.imprimir();
				
				leitura.fecharArquivo();
				
			}
			
		} while (!(idInformado.equals("FIM")));
		
	}

}
