package ru.liga.graph;

import ru.liga.predication.Predication;

import java.io.IOException;
import java.util.List;

public interface Graph {
    public void initUI(List<String> numberOfCurr, Predication predicator, String algorithmType) throws IOException;
}
