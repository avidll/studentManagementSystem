package sims.domain;

/**
 * 出生年月
 */
public class Birthday {
    private int year;
    private int month;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Birthday(int year, int month) {
        this.year = year;
        this.month = month;
    }
}
