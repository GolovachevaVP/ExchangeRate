package ru.liga.predication;

public class PredicationFactory {
    public Predication getPredication(String type) {
        if (type.equals("week")) {
            return new WeekPredicationPredicationImpl();
        } else if (type.equals("month")) {
            return new MonthPredicationPredicationImpl();
        } else if (type.equals("tomorrow")) {
            return new TomorrowPredicationPredicationImpl();
        } else {
            return new FutureDatePredicationPredicationImpl(type);
        }
    }
}
