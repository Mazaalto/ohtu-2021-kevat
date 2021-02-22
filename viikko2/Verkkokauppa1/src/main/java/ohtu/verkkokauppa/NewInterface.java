/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author mazaalto
 */
public interface NewInterface {

    void aloitaAsiointi();

    boolean tilimaksu(String nimi, String tiliNumero);
    
}
