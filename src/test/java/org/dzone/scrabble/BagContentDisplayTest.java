package org.dzone.scrabble;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class BagContentDisplayTest {
    private  Map<Tile,Integer> tileDistribution;
    private final Displayable bagContentsDisplay = new BagContentDisplay();

    @Before
    public void setUp(){
        tileDistribution = new HashMap<>();
        tileDistribution.put( new Tile('A'),4);
        System.out.println((int)'A'+" "+(int)'Z'+" ");
    }

    @Test
    public void showsQuantityAndLetterOfTiles(){
        String expected = "4: A\n3: B";

        verifyDisplayResult(expected);
    }

    @Test
    public void printsResultsForAQuantityInLine(){
        tileDistribution.put( new Tile('B'),3);
        String expected = "4: A\n3: B";

        verifyDisplayResult(expected);
    }

    private void verifyDisplayResult(String expected) {
        TilesBag bag = new TilesBag(tileDistribution);

        String output = bagContentsDisplay.showDetails(bag);

        assertEquals(expected, output);
    }

    @Test
    public void printsResultsForInDescendingOrder(){
        tileDistribution.put( new Tile('B'),6);
        String expected = "6: B\n4: A";

        verifyDisplayResult(expected);
    }

    @Test
    public void printsResultsSkipsZeroQuantityTiles(){
        tileDistribution.put( new Tile('B'),0);
        tileDistribution.put( new Tile('C'),6);
        String expected = "6: C\n4: A\n0: B";

        verifyDisplayResult(expected);
    }

    @Test
    public void forSameQuantityPrintCommaSeparatedLetters(){
        tileDistribution.put( new Tile('B'),4);
        tileDistribution.put( new Tile('C'),6);
        String expected = "6: C\n4: A, B";

        verifyDisplayResult(expected);
    }

    @Test
    public void forBlankSpaceHavingSameQuantity_ShouldShowBlankSpaceAtTheEnd(){
        tileDistribution.put( new Tile('B'),4);
        tileDistribution.put( new Tile('C'),6);
        tileDistribution.put( new Tile(Constants.BLANK_SPACE_REPRESENTATION_CHAR),4);

        String expected = "6: C\n4: A, B, _";

        verifyDisplayResult(expected);
    }


}
