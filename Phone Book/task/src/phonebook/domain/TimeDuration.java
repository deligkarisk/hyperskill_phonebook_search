package phonebook.domain;

public class TimeDuration {

    long minutes;
    long seconds;
    long milliseconds;

    public TimeDuration(long timeStart, long timeEnd) {
        long timeTakenLinear = (timeEnd - timeStart);
        this.minutes = timeTakenLinear/60000;
        this.seconds = (timeTakenLinear - this.minutes*60000)/1000;
        this.milliseconds =
                (timeTakenLinear - this.minutes *60000 - this.seconds*1000);
    }

    public long getMinutes() {
        return minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}
