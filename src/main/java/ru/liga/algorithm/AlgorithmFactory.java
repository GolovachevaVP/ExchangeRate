package ru.liga.algorithm;

public class AlgorithmFactory {
    public  Algorithm getAlgorithm(String type) {
        if (type.equals("alg lastYear")) {
            return new LastYearAlgorithmImpl();
        } else if (type.equals("alg mist")) {
            return new MysticalAlgorithmImpl();
        } else {
            return new LinearAlgorithmImpl();
        }
    }
}
