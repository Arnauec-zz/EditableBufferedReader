/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labpad;

import java.io.IOException;
import static labpad.EditableBufferedReader.CARRIAGE_RETURN;

/**
 *
 * @author epicur
 */
public class TrashClass {
    public void setRaw(){
        //Fem-ho primer amb Runtime
        /* Métode més vell i rudimentari
        String s = null;

        try {

	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Process p = Runtime.getRuntime().exec(cmd);
            
           
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("Exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        
        */
        
        
        
                /*
            int res = 0;
            int valor1 = super.read();
            res = valor1;
            if (valor1 == 27) {
                int valor2 = super.read();
                res = valor2;
                int valor3 = super.read();
                if(valor2 == 91){
                    if (valor3 == 77){
                        int valor4 = super.read();
                        switch(valor4){
                            case 32:
                                pos[0] = super.read();
                                pos[1] = super.read(); 
                                res = CLIC_DRET;
                                break;
                            case 34:
                                pos[0] = super.read();
                                pos[1] = super.read(); 
                                res = CLIC_ESQUERRA;
                                break;
                            case 97:
                                pos[0] = super.read();
                                pos[1] = super.read(); 
                                res = RODA_AMUNT;
                                break;
                            case 96:
                                pos[0] = super.read();
                                pos[1] = super.read(); 
                                res = RODA_AVALL;
                                break;
                            case 49:
                                int valor5, valor6, valor7;
                                valor5 = super.read();
                                if(valor5 == 59){
                                    valor6 = super.read();
                                    if (valor6 == 53){
                                        valor7 = super.read();
                                        switch(valor7){
                                            case 65:
                                                res = INSERTAR;
                                                break;
                                            case 66:
                                                res = SUPRIMIR;
                                                break;
                                            case 67:
                                                res = FIN;
                                                break;
                                            case 68:
                                                res = INICI;
                                                break;
                                            }
                                        }
                                    }
                                }
                        }
                    }else{
                        switch (valor3) {
                            case 67: 
                                res = FLETXA_DRETA; //fletxa dreta
                                break;
                            case 68: 
                                res = FLETXA_ESQUERRA; //fletxa esquerra
                                break;
                            }
                    }
                    
                }
        return res;*/
                
                        
        
        /*BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            str = in.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nline is: " + str);*/
                
    }
    
    /*public int read(int[] pos) throws IOException{
        int ch = super.read();
        try{ 
            switch(ch){
                case '\r':
                    return CARRIAGE_RETURN;
                case ESCAPE:
                    ch = super.read();
                    switch(ch){
                        case '[':
                            ch = super.read();
                            switch(ch){
                                case 'C': 
                                    return FLETXA_DRETA; //fletxa dreta
                                case 'D': 
                                    return FLETXA_ESQUERRA; //fletxa esquerra  
                                case 'M':
                                    ch = super.read();
                                    switch(ch){
                                        case ' ':
                                            pos[0] = super.read();
                                            pos[1] = super.read(); 
                                            return CLIC_DRET;
                                        case 34:
                                            pos[0] = super.read();
                                            pos[1] = super.read(); 
                                            res = CLIC_ESQUERRA;
                                            break;
                                        case '`':
                                            pos[0] = super.read();
                                            pos[1] = super.read(); 
                                            return RODA_AVALL;
                                        case 'a':
                                            pos[0] = super.read();
                                            pos[1] = super.read(); 
                                            return RODA_AMUNT;
                                        case '1':
                                            ch = super.read();
                                            switch(ch){
                                                case ';':
                                                    ch = super.read();
                                                    switch(ch){
                                                        case '5':
                                                            ch = super.read();
                                                            switch(ch){
                                                                case 'A':
                                                                    return INSERTAR;
                                                                case 'B':
                                                                    return SUPRIMIR;
                                                                case 'C':
                                                                    return FIN;
                                                                case 'D':
                                                                    return INICI;
                                                            }
                                                    }
                                            }
                                    }
                            }
                                 
                    }
                }
        } catch (IOException e){
            System.out.println("Exception happened - here's what I know: ");
            e.printStackTrace();
            return -1;
        }

        return ch;
    }*/
}
