package org.dzone.scrabble;

public class InsufficientQuantityException extends RuntimeException {
    public InsufficientQuantityException(String tileLetter) {
        super("Invalid input. More "+tileLetter+"'s have been taken from the bag than possible.");
    }
}
