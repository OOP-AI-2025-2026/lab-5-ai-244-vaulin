package ua.opnu;

public class TimeSpan {
    private final int ONE_HOUR_IN_MINUTES = 60;

    private int hours;
    private int minutes;

    public TimeSpan() {
        this.hours = 0;
        this.minutes = 0;
    }

    public TimeSpan(int minutes) {
        this.hours = minutes / ONE_HOUR_IN_MINUTES;
        this.minutes = minutes % ONE_HOUR_IN_MINUTES;
    }

    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes >= ONE_HOUR_IN_MINUTES) {
            this.hours = 0;
            this.minutes = 0;
            return;
        }
        this.hours = hours;
        this.minutes = minutes;
    }

    public TimeSpan(TimeSpan time) {
        this.hours = time.getHours();
        this.minutes = time.getMinutes();
    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void add(int addingHours, int addingMinutes) {
        if (addingHours < 0 || addingMinutes < 0 || addingMinutes >= ONE_HOUR_IN_MINUTES) {
            return;
        }

        this.hours += addingHours;
        this.minutes += addingMinutes;

        if (this.minutes >= ONE_HOUR_IN_MINUTES) {
            this.hours += this.minutes / ONE_HOUR_IN_MINUTES;
            this.minutes = this.minutes % ONE_HOUR_IN_MINUTES;
        }
    }

    public void add(int addingMinutes) {
        if (addingMinutes < 0) return;

        this.minutes += addingMinutes;

        if (this.minutes >= ONE_HOUR_IN_MINUTES) {
            this.hours += this.minutes / ONE_HOUR_IN_MINUTES;
            this.minutes = this.minutes % ONE_HOUR_IN_MINUTES;
        }
    }

    public void add(TimeSpan timespan) {
        this.add(timespan.getHours(), timespan.getMinutes());
    }

    public void subtract(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes >= ONE_HOUR_IN_MINUTES)
            return;

        int totalThis = this.getTotalMinutes();
        int totalSubtract = (hours * ONE_HOUR_IN_MINUTES) + minutes;

        if (totalSubtract > totalThis)
            return;

        int newTotal = totalThis - totalSubtract;
        this.hours = newTotal / ONE_HOUR_IN_MINUTES;
        this.minutes = newTotal % ONE_HOUR_IN_MINUTES;
    }

    public void subtract(int minutes) {
        this.subtract(0, minutes);
    }

    public void subtract(TimeSpan span) {
        this.subtract(span.getHours(), span.getMinutes());
    }

    public double getTotalHours() {
        return this.hours + ((double) this.minutes / ONE_HOUR_IN_MINUTES);
    }

    public int getTotalMinutes() {
        return (this.hours * ONE_HOUR_IN_MINUTES) + this.minutes;
    }

    public void scale(int factor) {
        if (factor <= 0) return;

        int total = this.getTotalMinutes() * factor;
        this.hours = total / ONE_HOUR_IN_MINUTES;
        this.minutes = total % ONE_HOUR_IN_MINUTES;
    }
}
