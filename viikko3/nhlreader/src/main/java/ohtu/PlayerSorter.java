/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.Comparator;

/**
 *
 * @author mazaalto
 */
public class PlayerSorter implements Comparator<Player> {

    @Override
    public int compare(Player t, Player t1) {
        return t.goalsAndAssists().compareTo(t1.goalsAndAssists());

    }
}
