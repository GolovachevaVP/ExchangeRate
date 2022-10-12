package ru.liga.graph;

import ru.liga.predication.IPredication;

import java.io.IOException;
import java.util.List;

public interface IGraph {
    public void initUI(List<String> numberOfCurr, IPredication predicator, String algorithmType) throws IOException;
}
