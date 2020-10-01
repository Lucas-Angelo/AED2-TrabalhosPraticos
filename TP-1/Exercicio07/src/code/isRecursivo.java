/**
 * 
 */
package code;

/**
 * @author Lucas Ângelo O. M. Rocha
 * github.com/Lucas-Angelo
 */

import java.io.*;
import java.nio.charset.*;

class MyIO {

   private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
   private static String charset = "ISO-8859-1";

   public static void setCharset(String charset_){
      charset = charset_;
      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
   }

   public static void print(){
   }

   public static void print(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(){
   }

   public static void println(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void printf(String formato, double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.printf(formato, x);// "%.2f"
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static double readDouble(){
      double d = -1;
      try{
         d = Double.parseDouble(readString().trim().replace(",","."));
      }catch(Exception e){}
      return d;
   }

   public static double readDouble(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readDouble();
   }

   public static float readFloat(){
      return (float) readDouble();
   }

   public static float readFloat(String str){
      return (float) readDouble(str);
   }

   public static int readInt(){
      int i = -1;
      try{
         i = Integer.parseInt(readString().trim());
      }catch(Exception e){}
      return i;
   }

   public static int readInt(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readInt();
   }

   public static String readString(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != ' ' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n' && tmp != ' ');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readString(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readString();
   }

   public static String readLine(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readLine(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readLine();
   }

   public static char readChar(){
      char resp = ' ';
      try{
         resp  = (char)in.read();
      }catch(Exception e){}
      return resp;
   }

   public static char readChar(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readChar();
   }

   public static boolean readBoolean(){
      boolean resp = false;
      String str = "";

      try{
         str = readString();
      }catch(Exception e){}

      if(str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") || 
            str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")){
         resp = true;
            }

      return resp;
   }

   public static boolean readBoolean(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readBoolean();
   }

   public static void pause(){
      try{
         in.read();
      }catch(Exception e){}
   }

   public static void pause(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      pause();
   }
}



public class isRecursivo {

	/**
	 * @param args
	 */
	
	private static boolean somenteVogais(String str, int indice) {
		boolean res = true;
		
		if(str.length()==indice)
			res=true;
		else if (str.charAt(indice)!='A' && str.charAt(indice)!='E' && str.charAt(indice)!='I' && str.charAt(indice)!='O' && str.charAt(indice)!='U' && str.charAt(indice)!='a' && str.charAt(indice)!='e' && str.charAt(indice)!='i' && str.charAt(indice)!='o' && str.charAt(indice)!='u')
			res = false;
		else
			res = somenteVogais(str, ++indice);
		
		return res;
		
	}
		
	
	private static boolean somenteConsoantes(String str, int indice) {
		
		boolean res = true;
		
		if(str.length()==indice) 
			res=true;
		else if(str.charAt(indice)!='B' && str.charAt(indice)!='C' && str.charAt(indice)!='D' && str.charAt(indice)!='F' && str.charAt(indice)!='G' && str.charAt(indice)!='H' && str.charAt(indice)!='J' && str.charAt(indice)!='K' && str.charAt(indice)!='L' && str.charAt(indice)!='M' && str.charAt(indice)!='N' && str.charAt(indice)!='P' && str.charAt(indice)!='Q' && str.charAt(indice)!='R' && str.charAt(indice)!='S' && str.charAt(indice)!='T' && str.charAt(indice)!='V' && str.charAt(indice)!='W' && str.charAt(indice)!='X' && str.charAt(indice)!='Y' && str.charAt(indice)!='Z' && str.charAt(indice)!='b' && str.charAt(indice)!='c' && str.charAt(indice)!='d' && str.charAt(indice)!='f' && str.charAt(indice)!='g' && str.charAt(indice)!='h' && str.charAt(indice)!='j' && str.charAt(indice)!='k' && str.charAt(indice)!='l' && str.charAt(indice)!='m' && str.charAt(indice)!='n' && str.charAt(indice)!='p' && str.charAt(indice)!='q' && str.charAt(indice)!='r' && str.charAt(indice)!='s' && str.charAt(indice)!='t' && str.charAt(indice)!='v' && str.charAt(indice)!='w' && str.charAt(indice)!='x' && str.charAt(indice)!='y' && str.charAt(indice)!='z')				
			res = false;
		else
			res = somenteConsoantes(str, ++indice);
		
		return res;
		
	}
	
	private static boolean isInteiro(String str, int indice) {
		
		boolean res = true;
		
		if(str.length()==indice)
			res=true;
		else if (!(str.charAt(indice)>='0' && str.charAt(indice)<='9')) 
			res = false;
		else
			res = isInteiro(str, ++indice);
		
		return res;
		
	}
	
	private static boolean isReal(String str, int indice, int cont) {
		
		boolean res = true;
		
		if(str.length()==indice)
			res=true;
		else if ((!(str.charAt(indice)>='0' && str.charAt(indice)<='9') && (str.charAt(indice)!=',' && str.charAt(indice)!='.')) || cont>1) {
			res = false;
		}
		else {
			if(str.charAt(indice)=='.' || str.charAt(indice)==',')
				++cont;
			res = isReal(str, ++indice, cont);
		}
		
		if(!(res==true && (cont==0 || cont==1)))
			res = false;

		return res;
		
	}
	
	public static boolean passarSomenteVogais(String str) {
		return somenteVogais(str, 0);
	}
	
	public static boolean passarSomenteConsoantes(String str) {
		return somenteConsoantes(str, 0);
	}
	
	public static boolean passarSomenteInteiro(String str) {
		return isInteiro(str, 0);
	}
	
	public static boolean passarSomenteReal(String str) {
		return isReal(str, 0, 0);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str;
		
		do {
			str = MyIO.readLine();
			
			if(!str.equals("FIM"))
			{
				if(passarSomenteVogais(str)) {
					MyIO.print("SIM ");
				}
				else {
					MyIO.print("NAO ");
				}
				
				if(passarSomenteConsoantes(str)) {
					MyIO.print("SIM ");
				}
				else {
					MyIO.print("NAO ");
				}
				
				if(passarSomenteInteiro(str)) {
					MyIO.print("SIM ");
				}
				else {
					MyIO.print("NAO ");
				}
				
				if(passarSomenteReal(str)) {
					MyIO.println("SIM");
				}
				else {
					MyIO.println("NAO");
				}
			}
			
		} while(!str.equals("FIM"));

	}

}
