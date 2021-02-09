/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mazaalto
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 100, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void hakutoimiiTest() {
        assertEquals(stats.search("Semenko").getName(), "Semenko");

    }
    @Test
    public void hakuPalauttaaNull() {
        assertEquals(stats.search("Aaltonen"), null);

    }
    @Test
    public void tiimiHakuToimii() {
        List <Player> joukkue = stats.team("EDM");
        assertEquals(joukkue.size(), 3);

    }
    @Test
    public void topScorersToimii() {
        List<Player> parhaat = stats.topScorers(1);
        assertEquals(parhaat.get(0).getPoints(), 189);

    }

}
