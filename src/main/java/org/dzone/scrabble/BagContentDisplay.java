package org.dzone.scrabble;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BagContentDisplay implements Displayable  {

    @Override
    public String showDetails(final TilesBag bag) {
        Map<Integer, List<Map.Entry<Tile, Integer>>> tilesListGroupedByQuantity = getTilesListGroupedByQuantity(bag);
        String finalOutPut = generateOutput(tilesListGroupedByQuantity);
        System.out.println(finalOutPut);
        return finalOutPut;
    }

    private String generateOutput(Map<Integer, List<Map.Entry<Tile, Integer>>> tilesListGroupedByQuantity) {
        return tilesListGroupedByQuantity.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getKey().compareTo(entry1.getKey()))
                .map(entry -> entry.getKey() + ": " + commaSeparatedTileLettersForAQuantity(entry))
                .reduce((value1, value2) -> value1 + Constants.NEW_LINE_CHARACTER + value2)
                .get();
    }

    private String commaSeparatedTileLettersForAQuantity(Map.Entry<Integer, List<Map.Entry<Tile, Integer>>> entry) {
        return entry.getValue()
                .stream()
                .sorted((tileEntry1, tileEntry2) -> tileEntry1.getKey().getLetter().compareTo(tileEntry2.getKey().getLetter()))
                .map(tileEntry1 -> "" + String.valueOf(tileEntry1.getKey().getLetter()))
                .reduce((letter1, letter2) ->
                        letter1 + ", " + letter2).get();
    }

    private Map<Integer, List<Map.Entry<Tile, Integer>>> getTilesListGroupedByQuantity(TilesBag bag) {
        return bag.getTileDistribution()
                    .entrySet()
                    .stream()
                    .collect(Collectors.groupingBy(Map.Entry::getValue));
    }
}
