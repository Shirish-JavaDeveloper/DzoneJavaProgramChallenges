package org.dzone.scrabble;

public class Tile {
    private final Character letter;

    public Tile(Character letter) {
        this.letter = letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return !(letter != null ? !letter.equals(tile.letter) : tile.letter != null);

    }

    @Override
    public int hashCode() {
        return letter != null ? letter.hashCode() : 0;
    }

    public Character getLetter() {
        return letter;
    }


    @Override
    public String toString() {
        return "Tile{" +
                "letter=" + letter +
                '}';
    }
}
