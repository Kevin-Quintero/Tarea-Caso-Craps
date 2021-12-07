package juegoCraps;

import java.util.Vector;

/**
 * ModelCraps apply craps rules.
 * estado = 1 Natural winner
 * estado = 2 Craps looser
 * estado = 3 Establish Punto
 * estado = 4 Punto winner
 * estado = 5 Punto looser
 * @author Kevin Andres Quintero Trochez
 * @version v.1.0.0 date 07/12/2021
 */
public class ModelCraps {
    private Dado dado1, dado2;
    private int tiro, punto, estado, flag;
    private String estadoToString;
    private int[] caras;

    /**
     * Class Constructor
     */
    public ModelCraps() {
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2];
    }

    /**
     * Establish the tiro value according to each dice
     */
    public void calcularTiro() {
        caras[0] = dado1.getCara();
        caras[1] = dado2.getCara();
        tiro = caras[0] + caras[1];
        flag = 0;
    }

    /**
     * Establish game state according to estado attribute value
     * estado = 1 Natural winner
     * estado = 2 Craps looser
     * estado = 3 Establish Punto
     */
    public void determinarJuego() {
        if (flag == 0) {
            if (tiro == 7 || tiro == 11) {
                //natural win
                estado = 1;
            } else {
                if (tiro == 3 || tiro == 2 || tiro == 12) {
                    //craps lose
                    estado = 2;
                } else {
                    //point
                    estado = 3;
                    punto = tiro;
                    flag = 1;

                }
            }
        } else {
            //ronda punto
            rondaPunto();
        }
    }

    /**
     * Establish game state according to estado attribute value
     * estado = 4 Punto winner
     * estado = 5 Punto looser
     */
    private void rondaPunto() {
        if (tiro == punto) {
            //point win
            estado = 4;
            flag = 0;
        }
        if (tiro == 7) {
            //point lose
            estado = 5;
            flag = 0;
        }
    }

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     * Establish message game state according to estado attribute value
     *
     * @return Message for the view class
     */
    public String getEstadoToString() {
        switch (estado) {
            case 1:
                estadoToString = "Sacaste Natural, ¡has ganado!";
                break;
            case 2:
                estadoToString = "Sacaste Craps, ¡has perdido!";
                break;
            case 3:
                estadoToString = "Estableciste punto en " + punto +
                        " ¡Debes seguir lanzando!" +
                        "\n pero si sacas 7 antes que " + punto + ", perderas";
                break;
            case 4:
                estadoToString = "Volviste a sacar " + punto + ", ¡has ganado!";
                break;
            case 5:
                estadoToString = "Sacaste 7 antes que " + punto + ", ¡has perdido!";
                break;
        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }

}