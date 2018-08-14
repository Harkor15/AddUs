package harkor.addus.interfaces;

import harkor.addus.model.Square;

public interface IGame {
    void timeout();
    Square getSquare(int id);
    void addPoints(int value);
    void nextSecond();
    void setterSquare(int id,Square square);
}
