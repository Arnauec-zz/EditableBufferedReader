/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labpad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author epicur
 */
public class KeyIdentifier extends BufferedReader{
    public KeyIdentifier(Reader in, int sz){
        super(in,sz);
    }
    public KeyIdentifier(Reader in){
        super(in);
    }
    
    public int read() throws IOException{
        //  Jo inicialitzo un *Reader* i vaig llegint de teclat per anar veient que em van introduint en el teclat
        //  Llavors identifico tots els símbols i els tracto com a tal.
        try{
            int count = 0;
            while(count < 7){
                System.out.print("Tecla"+ count + ": " + super.read() +"\r\n");
                count++;
            }
            return 1;
        } catch (IOException e){
            System.out.println("Exception happened - here's what I know: ");
            return -1;
        }
        
    }
    public String readLine() throws IOException {
        read();
        return "";
    }
}
