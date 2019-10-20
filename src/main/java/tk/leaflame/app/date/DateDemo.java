package tk.leaflame.app.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DateDemo {

    public static void main(String[] args) throws InterruptedException {
        testLocalDate();
        testLocalTime();
        testLocalDateTime();
        testInstant();
        testDuration();
        testPeriod();
        testDateFormat();
        testDateParse();
    }

    /*thread safe*/
    private static void testLocalDate() {//check the api
        System.out.println("testLocalDate:");
        LocalDate localDate = LocalDate.of(2019, 7, 28);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.get(ChronoField.DAY_OF_WEEK));
        System.out.println(LocalDate.MAX);
        System.out.println(LocalDate.MIN);
        System.out.println(LocalDate.now());
    }

    private static void testLocalTime() {
        System.out.println("testLocalTime:");
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
    }

    private static void testLocalDateTime() {
        System.out.println("testLocalDateTime:");
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime.toString());
        System.out.println(LocalDateTime.now());
    }

    private static void testInstant() throws InterruptedException {
        System.out.println("testInstant:");
        Instant start = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    private static void testDuration() {
        System.out.println("testDuration:");
        LocalTime time = LocalTime.now();
        LocalTime beforeTime = time.minusHours(1);
        System.out.println(Duration.between(time, beforeTime).toHours());
    }

    private static void testPeriod() {
        System.out.println("testPeriod:");
        Period period = Period.between(LocalDate.of(1998, 10, 30), LocalDate.of(2019, 7, 28));
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    private static void testDateFormat(){
        System.out.println("testDateFormat:");
        LocalDate localDate=LocalDate.now();
        String format=localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(format);
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(localDate.format(dateTimeFormatter));
    }

    private static void testDateParse(){
        System.out.println("testDateParse:");
        String date="20190728";
        LocalDate localDate=LocalDate.parse(date,DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);
    }
}
