package mx.uach.compiladores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.text.html.HTMLEditorKit;


/**
 * <ul>
 * <li>prog -> conjprods</li>
 * <li>conjprods -> conjprod | prod</li>
 * <li>prod -> variable def exp;</li>
 * <li>expr -> expr ALT term | term</li>
 * <li>term -> term & factor | factor</li>
 * <li>factor -> {expr} | [expr] | primario</li>
 * <li>primario -> (expr) |variable|terminal|</li>
 * <li></li>
 * <li></li>
 * </ul>
 *
 * @author Luis Perez
 * @version 1.0
 * @since 18/03/2015
 */
public class Analizadorsintactico {
    private static final  int Concatena = '&';
    private static final  int Alterna = '|';
    private static final  int Cerradura1_ini= '{';
    private static final  int Cerradura1_fin = '}';
    private static final  int Cerradura2_ini = '[';
    private static final  int Cerradura2_fin = ']';
    private static final  int Defini1 = ':';
    private static final  int Defini2 = '=';
    private static final  int FIN_PROD = ';';
    private static final  int FIN_ARCHIVO = '.';
   
    int Var=100;
    
    
  private Integer linea = 1;  
  private StringTokenizer tokenizer = null;
  
    //Alfabeto
     private StringTokenizer gettTokenizer(String codigoFuente){
        if(this.tokenizer == null){
            //;+-*/()
            String alfabeto = String.format("%s%s%s%s%s%s%s%s%s%s",
                    (char) Concatena,
                    (char) Alterna,(char)Cerradura1_ini,(char)Cerradura1_fin,
                    (char)Cerradura2_ini,(char)Cerradura2_fin,(char)Defini1,
                    (char)Defini2, (char)FIN_PROD,(char)FIN_ARCHIVO);
            this.tokenizer = new StringTokenizer(codigoFuente.trim(),alfabeto,true);
        }
        return tokenizer;
    }
     private Token lex(String codigoFuente){
         Token token=null;
         
         String currentToken = this.gettTokenizer(codigoFuente).nextToken();
         if (esNumero(currentToken)) {
             token = new Token(this.linea, Var , currentToken);
         }else{
            int tokenSimple = currentToken.charAt(0);
            switch(tokenSimple){
                case Concatena:
               linea++;
               token=new Token(this.linea,Concatena, String.format("%s", (char)tokenSimple));
                   break;
                   case Alterna:
               linea++;
               token=new Token(this.linea,Alterna, String.format("%s", (char)tokenSimple));
                   break;
                   
               case Cerradura1_ini:
               linea++;
               token=new Token(this.linea,Cerradura1_ini, String.format("%s", (char)tokenSimple));
                   break;
               case Cerradura1_fin:
               linea++;
               token = new Token(this.linea,Cerradura1_fin, String.format("%s", (char)tokenSimple));
                   break;
                case Cerradura2_ini:
               linea++;
               token = new Token(this.linea,Cerradura2_ini, String.format("%s", (char)tokenSimple));
                   break;
                case Cerradura2_fin:
               linea++;
               token = new Token(this.linea,Cerradura2_fin, String.format("%s", (char)tokenSimple));
                   break;
                    case Defini1:
               linea++;
               token = new Token(this.linea,Defini1, String.format("%s", (char)tokenSimple));
                   break;
                    case Defini2:
               linea++;
               token = new Token(this.linea, Defini2,String.format("%s", (char)tokenSimple));
                   break;     
                    case FIN_PROD:
               linea++;
               token = new Token(this.linea, FIN_PROD,String.format("%s", (char)tokenSimple));
                     break;
               case FIN_ARCHIVO:
               linea++;
               token = new Token(this.linea, FIN_ARCHIVO,String.format("%s", (char)tokenSimple));
                   break;
                   default:
                        throw new Error("ERROR de lexico: "
                                +"el caracter no esta dentro del alfabeto");
            }
            
         }
         return token;
     }
     
     
     
     
      public static Boolean esNumero(String textorevisar){
         Boolean esNumero = true;
         for (int  i = 0;  i < textorevisar.length();  i++) {
             esNumero = esNumero &&
                     Character.isDigit(textorevisar.charAt(i));
         }
        return esNumero;
     }
     
      public static void main(String[] args) {
      File file = new File("C:/Users/Lic.Angélica/Desktop/prueba.txt");
      
        Analizadorsintactico analizador = new Analizadorsintactico();
        
        
        while (analizador.gettTokenizer("::={3&3}").hasMoreTokens()) {
            Token t = analizador.lex("");
            System.out.println(t);
       
        }
        
       
 
		try (FileInputStream fis = new FileInputStream(file)) {
 
			System.out.println("Total file size to read (in bytes) : "+ fis.available());
 
			int content;
                        
			while ((content = fis.read()) != -1) {
				// convert to char and display it
                           
				System.out.print((char) content);
                         
                    }
 
		} catch (IOException e) {
		}
        
    }
}
