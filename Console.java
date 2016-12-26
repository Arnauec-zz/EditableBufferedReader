/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labpad;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author epicur
 */
public class Console implements Observer {

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
    Line linia;

    public Console() {
    }

    static class Command {

        int i, pos;

        Command(int i, int pos) {
            this.i = i;
            this.pos = pos;
        }
    }

    @Override
    public void update(Observable ob, Object o) {
        char car;
        Command com = (Command) o;
        switch (com.i) {
            case CARRIAGE_RETURN:
                System.exit(0);
                break;
            case BACKSPACE:
                System.out.print("\u001B\u005b\u0044\u001B\u005b\u0050");
                break;
            case FLETXA_DRETA:
                System.out.print("\u001B\u005b\u0043");
                break;
            case FLETXA_ESQUERRA:
                System.out.print("\u001B\u005b\u0044");
                break;
            case RODA_AMUNT:
                System.out.print("\u001B\u005b\u0043");
                break;
            case RODA_AVALL:
                System.out.print("\u001B\u005b\u0044");
                break;
            case CLIC_DRET:
                    if (com.pos > 0) {
                        System.out.print("\u001B\u005b" + com.pos + "\u0043");
                    } else if (com.pos < 0) {
                        System.out.print("\u001B\u005b" + (com.pos*-1) + "\u0044");
                    }
                break;
            case CLIC_ESQUERRA:
                break;
            case INSERTAR:
                break;
            case INICI:
                System.out.print("\u001B\u005b" + com.pos + "\u0044");
                break;
            case FIN:
                System.out.print("\u001B\u005b" + com.pos + "\u0043");
                break;
            case SUPRIMIR:
                System.out.print("\u001B\u005b\u0050");
                break;
            default:
                if (com.pos == 0) {
                    car = (char) com.i;
                    System.out.print(Character.toString(car));
                    break;
                } else {
                    car = (char) com.i;
                    System.out.print("\u001B\u005b\u0040");
                    System.out.print(Character.toString(car));
                    break;
                }
        }
    }

}