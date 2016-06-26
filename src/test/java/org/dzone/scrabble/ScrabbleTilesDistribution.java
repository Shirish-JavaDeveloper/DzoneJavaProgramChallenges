package org.dzone.scrabble;

import java.util.HashMap;
import java.util.Map;

/**
 * URL : http://scrabblewizard.com/scrabble-tile-distribution/
 * SCRABBLE TILE DISTRIBUTION
 Tile count and value ordered by letter:
 Tile	Count	Value
 A	9	1
 B	2	3
 C	2	3
 D	4	2
 E	12	1
 F	2	4
 G	3	2
 H	2	4
 I	9	1
 J	1	8
 K	1	5
 L	4	1
 M	2	3
 N	6	1
 O	8	1
 P	2	3
 Q	1	10
 R	6	1
 S	4	1
 T	6	1
 U	4	1
 V	2	4
 W	2	4
 X	1	8
 Y	2	4
 Z	1	10
 Blank 2 0
 */

public class ScrabbleTilesDistribution {
    private final  Map<Tile, Integer> tilesDistribution;
    public ScrabbleTilesDistribution() {
        tilesDistribution = new HashMap<Tile, Integer>(){{
            put(new Tile('A'),9) ;
            put(new Tile('B'),2) ;
            put(new Tile('C'),2) ;
            put(new Tile('D'),4) ;
            put(new Tile('E'),12) ;
            put(new Tile('F'),2);
            put(new Tile('G'),3) ;
            put(new Tile('H'),2) ;
            put(new Tile('I'),9) ;
            put(new Tile('J'),1) ;
            put(new Tile('K'),1) ;
            put(new Tile('L'),4) ;
            put(new Tile('M'),2) ;
            put(new Tile('N'),6) ;
            put(new Tile('O'),8) ;
            put(new Tile('P'),2) ;
            put(new Tile('Q'),1) ;
            put(new Tile('R'),6) ;
            put(new Tile('S'),4) ;
            put(new Tile('T'),6) ;
            put(new Tile('U'),4) ;
            put(new Tile('V'),2) ;
            put(new Tile('W'),2) ;
            put(new Tile('X'),1) ;
            put(new Tile('Y'),2) ;
            put(new Tile('Z'),1) ;
            put(new Tile('_'),2);
        } };

    }

    public Map<Tile, Integer> getDistributionRequiredForChallenge() {
        return tilesDistribution;
    }
}
