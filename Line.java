/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labpad;

import java.util.*;

/**
 *
 * @author epicur
 */
public class Line extends Observable {

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
    private int numChar;
    private int posx;
    // private int posy; ho comento perque de moment només hem de tractar una línia
    private boolean insercio; // true - inserció ; false - sobreescriptura
    private MyStringBuffer string;

    public Line(Console console) {
        this.insercio = false;
        this.string = new MyStringBuffer();
        this.addObserver(console);
    }

    public void carReturn() {
        this.notifyObservers();
    }

    public void end() {
        posx = numChar;
    }

    public void home() {
        posx = 0;
        setChanged();
        notifyObservers(new Console.Command(INICI, 0));
    }

    public void addChar(char a) {
        if (!this.insercio) {
            string.setChar(posx, a);
            if (posx == numChar) {
                numChar++;
            }
        } else {
            string.insert(posx, a);
            numChar++;
        }
        posx++;
        int o = 0;
        if (insercio) {
            o = 1;
        }
        setChanged();
        notifyObservers(new Console.Command(a, o));
    }

    public void setMode() {
        this.insercio = !this.insercio;
    }

    public boolean getMode() {
        return this.insercio;
    }

    public void deleteChar() {
        string.deleteCharAt(posx);
        posx--;
        numChar--;
        setChanged();
        notifyObservers(new Console.Command(BACKSPACE, 0));
    }

    public void suprimirChar() {
        string.deleteCharAt(posx);
        numChar--;
        this.setChanged();
        this.notifyObservers(new Console.Command(SUPRIMIR, 0));
    }

    public boolean moveCursorLeft() {
        if ((posx == 0)) {
            return false;
        }
        posx--;
        setChanged();
        notifyObservers(new Console.Command(FLETXA_ESQUERRA, 0));
        return true;
    }

    public boolean moveCursorRight() {
        if (posx == numChar) {
            return false;
        }
        posx++;
        setChanged();
        notifyObservers(new Console.Command(FLETXA_DRETA, 0));
        return true;
    }

    public int getPosx() {
        return this.posx;
    }

    public void clicDret(int posCursor) {
        int moviment = 0;
        if (!(posCursor > numChar)) {
            moviment = posCursor - posx;
            posx = posCursor;
        }
        setChanged();
        notifyObservers(new Console.Command(CLIC_DRET, moviment));
    }

    public int getNumChar() {
        return this.numChar;
    }

    public String toString() {
        return this.string.toString();
    }

    public void debugMode() {
        System.out.print("Posició cursor: " + posx + "\r\n");
        System.out.print("Número de caràcters totals: " + numChar + "\r\n");
        System.out.print("Mode: " + insercio + "\r\n");
        int count = 0;
        while (count < numChar) {
            System.out.print(string);
            count++;
        }
        System.out.print("\r\n");
    }
}
