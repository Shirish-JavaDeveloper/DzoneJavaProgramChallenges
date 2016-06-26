package org.dzone.scrabble;


import java.util.Map;

public class TilesBag {
    private final Map<Tile, Integer> tileDistribution;
    public TilesBag(final Map<Tile, Integer> tileDistribution) {
        this.tileDistribution = tileDistribution;
    }

    public Map<Tile, Integer> getTileDistribution() {
        return tileDistribution;
    }
}
