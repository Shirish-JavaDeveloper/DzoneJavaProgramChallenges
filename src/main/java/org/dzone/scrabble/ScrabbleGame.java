package org.dzone.scrabble;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScrabbleGame {
    private final TilesBag bag;
    private final Displayable bagContentDisplay;

    public ScrabbleGame(TilesBag bag, Displayable bagContentDisplay) {
        this.bag = bag;
        this.bagContentDisplay = bagContentDisplay;
    }

    public Map<Tile, Integer> getRemainingTiles(String removedTiles) {
        Map<Tile, Long> removedTilesMap = removedTilesGroupByQuantity(removedTiles);
        return findRemainingTilesInBag(removedTilesMap);
    }

    public String formatTheNumberOfTilesRemainingOutput(String removedTiles){
        return bagContentDisplay.showDetails(new TilesBag(getRemainingTiles(removedTiles)));
    }

    private LinkedHashMap<Tile, Integer> findRemainingTilesInBag(Map<Tile, Long> removedTilesMap) {
        return bag.getTileDistribution()
                .entrySet()
                .stream()
                .map(entry -> removeTilesFromBag(removedTilesMap, entry))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> value2, LinkedHashMap::new));
    }

    private Map.Entry<Tile, Integer> removeTilesFromBag(Map<Tile, Long> removedTilesMap, Map.Entry<Tile, Integer> entry) {
        if (removedTilesMap.containsKey(entry.getKey())) {
           Integer remainingTiles = entry.getValue() - removedTilesMap.get(entry.getKey()).intValue();
           if (remainingTiles < 0) {
               throw new InsufficientQuantityException(String.valueOf( entry.getKey().getLetter()));
           }
           entry.setValue(remainingTiles);
       }
        return entry;
    }

    private Map<Tile, Long> removedTilesGroupByQuantity(String selectedTiles) {
        return selectedTiles.chars()
                .mapToObj(letter -> new Tile((char) letter))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
