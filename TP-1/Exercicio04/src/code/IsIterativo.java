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



public class IsIterativo {

	/**
	 * @param args
	 */
	
	public static boolean somenteVogais(String str) {
		boolean res = true;
		
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)!='A' && str.charAt(i)!='E' && str.charAt(i)!='I' && str.charAt(i)!='O' && str.charAt(i)!='U' && str.charAt(i)!='a' && str.charAt(i)!='e' && str.charAt(i)!='i' && str.charAt(i)!='o' && str.charAt(i)!='u')
					res=false;
		}
		
		return res;
	}
	
	public static boolean somenteConsoantes(String str) {
		boolean res = true;
		
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)!='B' && str.charAt(i)!='C' && str.charAt(i)!='D' && str.charAt(i)!='F' && str.charAt(i)!='G' && str.charAt(i)!='H' && str.charAt(i)!='J' && str.charAt(i)!='K' && str.charAt(i)!='L' && str.charAt(i)!='M' && str.charAt(i)!='N' && str.charAt(i)!='P' && str.charAt(i)!='Q' && str.charAt(i)!='R' && str.charAt(i)!='S' && str.charAt(i)!='T' && str.charAt(i)!='V' && str.charAt(i)!='W' && str.charAt(i)!='X' && str.charAt(i)!='Y' && str.charAt(i)!='Z' && str.charAt(i)!='b' && str.charAt(i)!='c' && str.charAt(i)!='d' && str.charAt(i)!='f' && str.charAt(i)!='g' && str.charAt(i)!='h' && str.charAt(i)!='j' && str.charAt(i)!='k' && str.charAt(i)!='l' && str.charAt(i)!='m' && str.charAt(i)!='n' && str.charAt(i)!='p' && str.charAt(i)!='q' && str.charAt(i)!='r' && str.charAt(i)!='s' && str.charAt(i)!='t' && str.charAt(i)!='v' && str.charAt(i)!='w' && str.charAt(i)!='x' && str.charAt(i)!='y' && str.charAt(i)!='z')				
				res=false;
		}
		
		return res;
	}
	
	public static boolean isInteiro(String str) {
		boolean res = true;
		
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)!='0' && str.charAt(i)!='1' && str.charAt(i)!='2' && str.charAt(i)!='3' && str.charAt(i)!='4' && str.charAt(i)!='5' && str.charAt(i)!='6' && str.charAt(i)!='7' && str.charAt(i)!='8' && str.charAt(i)!='9')
				res=false;
		}
		
		return res;
	}
	
	public static boolean isReal(String str) {
		boolean res = true;
		int cont=0;
		
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)!='0' && str.charAt(i)!='1' && str.charAt(i)!='2' && str.charAt(i)!='3' && str.charAt(i)!='4' && str.charAt(i)!='5' && str.charAt(i)!='6' && str.charAt(i)!='7' && str.charAt(i)!='8' && str.charAt(i)!='9' && str.charAt(i)!=',' && str.charAt(i)!='.') {
				res=false;
			}
			if(str.charAt(i)==',' || str.charAt(i)=='.')
				++cont;
		}
		
		if(res==true && (cont==1 || cont==0))
			return res;
		else
			return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str;
		
		do {
			str = MyIO.readLine();
			
			if(!str.equals("FIM"))
			{
				if(somenteVogais(str)) {
					MyIO.print("SIM ");
				}
				else {
					MyIO.print("NAO ");
				}
				
				if(somenteConsoantes(str)) {
					MyIO.print("SIM ");
				}
				else {
					MyIO.print("NAO ");
				}
				
				if(isInteiro(str)) {
					MyIO.print("SIM ");
				}
				else {
					MyIO.print("NAO ");
				}
				
				if(isReal(str)) {
					MyIO.println("SIM");
				}
				else {
					MyIO.println("NAO");
				}
			}
			
		} while(!str.equals("FIM"));

	}

}
