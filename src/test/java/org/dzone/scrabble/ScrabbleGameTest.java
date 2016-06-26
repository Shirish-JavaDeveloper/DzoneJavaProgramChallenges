package org.dzone.scrabble;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Refer below URL for Problem statement
 * https://dzone.com/articles/java-code-challenge-scrabble-sets
 */
public class ScrabbleGameTest {
   private Map<Tile,Integer> tileDistribution ;
   private final Tile tile = new Tile('A');
   private Map<Tile, Integer> expectedRemainingTilesQuantity;

    @Before
    public void setUp(){
        tileDistribution = new HashMap<>();
        expectedRemainingTilesQuantity = new HashMap<>();
        tileDistribution.put(tile,4);
    }

    @Test
    public void bagContainsOnlyOneTile(){
        expectedRemainingTilesQuantity.put(new Tile('A'),2);

        verifyRemainingTiles(expectedRemainingTilesQuantity, "AA");
    }


    @Test
    public void bagContainsTwoTilesWithDifferentQuantity(){
        tileDistribution.put(new Tile('B'),10);
        expectedRemainingTilesQuantity.put(new Tile('A'),2);
        expectedRemainingTilesQuantity.put(new Tile('B'),7);

        verifyRemainingTiles(expectedRemainingTilesQuantity, "BAABB");
    }

    private void verifyRemainingTiles(Map<Tile, Integer> expectedRemainingTilesQuantity, String removedTiles) {
        TilesBag bag = new TilesBag(tileDistribution);
        Map<Tile, Integer> remainingTiles = new ScrabbleGame(bag,new BagContentDisplay()).getRemainingTiles(removedTiles);

        assertEquals(expectedRemainingTilesQuantity, remainingTiles);
    }

    @Test(expected = InsufficientQuantityException.class)
    public void whenRemovedMoreQuantityThanAvailableThenShouldThrowAnException(){
        tileDistribution.put(new Tile('B'), 10);
        TilesBag bag = new TilesBag(tileDistribution);
        ScrabbleGame game =  new ScrabbleGame(bag,new BagContentDisplay());

        game.getRemainingTiles("BAAAAABBA");
    }

    @Test
    public void dzoneChallengeFirstTestScenario(){
        String expected = "10: E\n" +
                "9: I\n" +
                "8: A\n" +
                "7: O\n" +
                "5: N, R, T\n" +
                "4: D, L, U\n" +
                "3: G, S\n" +
                "2: F, H, P, V, W\n" +
                "1: B, C, J, K, M, Q, Y, Z, _\n" +
                "0: X";
        String removedTiles = "AEERTYOXMCNB_S";

        verifyScrabbleWordScenarios(expected, removedTiles);
    }

    private void verifyScrabbleWordScenarios(String expected, String removedTiles) {
        ScrabbleTilesDistribution distribution = new ScrabbleTilesDistribution();
        Map<Tile,Integer> tileDistribution = distribution.getDistributionRequiredForChallenge();


        ScrabbleGame game = new ScrabbleGame(new TilesBag(tileDistribution),new BagContentDisplay());
        String actual = game.formatTheNumberOfTilesRemainingOutput(removedTiles);

        assertEquals(expected,actual);
    }

    @Test
    public void dzoneChallengeSecondTestScenario(){
        String expected = "10: E\n" +
                "7: A, I\n" +
                "6: N, O\n" +
                "5: T\n" +
                "4: D, L, R\n" +
                "3: S, U\n" +
                "2: B, C, F, G, M, V, Y\n" +
                "1: H, J, K, P, W, X, Z, _\n" +
                "0: Q";
        String removedTiles = "PQAREIOURSTHGWIOAE_";

        verifyScrabbleWordScenarios(expected, removedTiles);
    }


    @Test
    public void dzoneChallengeThirdTestScenario(){
        String expected =
                "11: E\n" +
                "9: A, I\n" +
                "6: R\n" +
                "5: N, O\n" +
                "4: D, S, T, U\n" +
                "3: G, L\n" +
                "2: B, C, H, M, P, V, W, Y, _\n" +
                "1: K, X\n" +
                "0: F, J, Q, Z";
        String removedTiles = "LQTOONOEFFJZT";

        verifyScrabbleWordScenarios(expected, removedTiles);
    }

    @Test(expected = InsufficientQuantityException.class)
    public void dzoneChallengeFourthTestScenario(){
        String removedTiles = "AXHDRUIOR_XHJZUQEE";

        verifyScrabbleWordScenarios("", removedTiles);
    }

}
