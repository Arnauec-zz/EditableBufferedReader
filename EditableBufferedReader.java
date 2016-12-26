/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * java -cp build/classes/ labpad.main comana important
 * java -cp ~/NetBeansProjects/LABPAD/build/classes/ labpad.TestReadLine
 * echo -e "\033[?9h" -> activar mode ratolí
 * echo -e "\033[?9l" -> desactivar mode ratolí
 * para que el sistema se entere del rezise de la ventana tenemos que hacer Ctr+a+f
 */
package labpad;

/**
 *
 * @author Epicur
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class EditableBufferedReader extends BufferedReader {
    static final int ESCAPE = 27;
    static final int CARRIAGE_RETURN = 13;
    static final int BACKSPACE = 127;
    static final int FLETXA_DRETA = 300;
    static final int FLETXA_ESQUERRA = 301;
    static final int CLIC_DRET = 302;
    static final int CLIC_ESQUERRA = 303;
    static final int RODA_AMUNT = 304;
    static final int RODA_AVALL = 305;
    static final int INSERTAR = 306;
    static final int FIN = 307;
    static final int INICI = 308;
    static final int SUPRIMIR = 309;
    private Line linia;
    private Console consola;
    private int[] car;
    
    public EditableBufferedReader(InputStreamReader in){
        super(in);
        this.consola = new Console();
        this.linia  = new Line(this.consola);
    }
    public void setRaw(){
        try{
            
            List<String> comana = new ArrayList<String>();
            comana.add("/bin/sh" );
            comana.add("-c");
            comana.add("stty -echo raw </dev/tty");
            ProcessBuilder p = new ProcessBuilder(comana);
            p.start();
            System.out.print("\033[?9h");
            
            
        }catch (IOException e){
            System.out.println("Exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        
    }
    
    public void unsetRaw(){
        try{
            List<String> comana = new ArrayList<String>();
            comana.add("/bin/sh" );
            comana.add("-c");
            comana.add("stty echo -raw </dev/tty ");
            ProcessBuilder p = new ProcessBuilder(comana);
            p.start();
            System.out.print("\033[?9l");
            
            
        }catch (IOException e){
            System.out.println("Exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    /**
     * HOME: ESC 0 H, ESC [ 1 - (numeric)
     * END:  ESC 0 F, ESC [ 4 - (numeric)
     * RIGHT:ESC [ C
     * LEFT: ESC [ D
     * INS:  ESC [ 2 - (numeric)
     * DEL:  ESC [ 3 - (numeric)
     */
    public int read(int[] pos) throws IOException{
        try{ 
            car = new int[10];
            if(car[0] == '\r') return CARRIAGE_RETURN;
            if(match("\033[D")) return FLETXA_ESQUERRA;
            if(match("\033[C")) return FLETXA_DRETA;
            if(match("\033[1;5A")) return INSERTAR;
            if(match("\033[1;5B")) return SUPRIMIR;
            if(match("\033[1;5C")) return FIN;
            if(match("\033[1;5D")) return INICI;
            if(match("\033[M ")) {
                pos[0] = super.read();
                pos[1] = super.read();
                return CLIC_DRET;
            }
            if(match("\033[M`")) {
                pos[0] = super.read();
                pos[1] = super.read();
                return RODA_AVALL;
            }
            if(match("\033[Ma")) {
                pos[0] = super.read();
                pos[1] = super.read();
                return RODA_AMUNT;
            }
            if(match("\033[M\"")) {
                pos[0] = super.read();
                pos[1] = super.read();
                return CLIC_ESQUERRA;
            }
            return car[0];
        } catch (IOException e){
            System.out.println("Exception happened - here's what I know: ");
            e.printStackTrace();
            return -1;
        }
    }
    public String readLine() throws IOException{
        setRaw();
        int[] pos = new int[2];
        boolean loop = false;
        while(!loop){
            int i = this.read(pos);
            char car;
            switch(i){
                case CARRIAGE_RETURN:
                    unsetRaw();
                    loop = true;
                    linia.carReturn();
                    break;
                case BACKSPACE:
                    linia.deleteChar();
                    break;
                case FLETXA_DRETA:
                    linia.moveCursorRight();
                    break;
                case FLETXA_ESQUERRA:
                    linia.moveCursorLeft();
                    break;
                case RODA_AMUNT:
                    linia.moveCursorRight();
                    break;
                case RODA_AVALL:
                    linia.moveCursorLeft();
                    break;
                case CLIC_DRET:
                    int posicio = (char) pos[0] - 33;
                    linia.clicDret(posicio);
                    break;
                case CLIC_ESQUERRA:
                    break;
                case INSERTAR:
                    linia.setMode();
                    break;
                case INICI:
                    linia.home();
                    break;
                case FIN:
                    linia.end();
                    break;
                case SUPRIMIR:
                    linia.suprimirChar();
                    break;
                default:
                    if(!linia.getMode()){
                        car = (char) i;
                        linia.addChar(car);
                        break;
                    } else {
                        car = (char) i;
                        linia.addChar(car);
                        break;
                    }
                }
        }
        return linia.toString();
    }
    
    public boolean match(String str) throws IOException{
        int i = 0;
        while(i < str.length()){
            if(car[i] == 0) car[i] = super.read();
            if(!(car[i] == str.charAt(i))) return false;
            i++;
        }
        return true;
    }
}