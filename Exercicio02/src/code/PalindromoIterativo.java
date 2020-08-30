package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromoIterativo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String texto, invertida;
		boolean igual, finaliza=false;
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			texto = entrada.readLine();
			igual = true;
			invertida = "";
			
			if(texto.charAt(0)=='F' && texto.charAt(1)=='I' && texto.charAt(2)=='M')
				finaliza=true;
			
			if(finaliza!=true){
				for(int i=0;i<texto.length();i++) {
					invertida = invertida + texto.charAt(texto.length()-i-1);
				}
				
				for(int i=0;i<texto.length();i++) {
					if(texto.charAt(i)!=invertida.charAt(i))
						igual = false;
				}
				
				if(igual)
					System.out.println("SIM");
				else
					System.out.println("NAO");
			}
			
		} while (finaliza!=true);

	}

}
