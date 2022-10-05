package ru.liga;

import java.time.LocalDate;

public class DateAndCourse {
    private double course;
    private LocalDate getDate;

    public Double getCourse() {
        return this.course;
    }
    public LocalDate getDate() {
        return this.getDate;
    }

    public DateAndCourse(Double course, LocalDate date) {
        this.course = course;
        this.getDate = date;
    }
}
