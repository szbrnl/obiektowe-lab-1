package agh.cs.lab;

/**
 * Created by student31 on 2017-11-21.
 */
public interface IPositionChangeObserver {
    void positionChanged(Position old, Position pNew);
}
