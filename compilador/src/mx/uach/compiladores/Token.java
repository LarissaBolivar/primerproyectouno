package mx.uach.compiladores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Token es el modelo que contiene los elementos que confirma la expresión 
 * divididos en los elementos que describe el alfabeto y la gramatica.
 * @author Larissa Bolívar Vzqz
 */
public class Token {
    private Integer linea;
    private int token;
    private String lexema;
    
    
    public Token(Integer linea, int token, String lexema){
           this.linea = linea;
          this.token = token;
          this.lexema = lexema;
          }

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    @Override
    public String toString(){
        File f;
        f=new File("ejemplo.txt");
        
        try {
            FileWriter w=new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(w);
            PrintWriter wr=new PrintWriter(bw);
            wr.write(String.format("%s -- %s -- %s \r\n", 
                this.linea,this.token,this.lexema));
            wr.append(String.format("%s -- %s -- %s", 
                this.linea,this.token,this.lexema));
            wr.close();
            bw.close();
        } catch (Exception e) {}
        
        return String.format("%s --- %s -- %s", 
            this.linea,this.token,this.lexema);  
        }
}
