package com.duyj.work.java8;

import com.duyj.work.utils.Q;

import java.time.*;

public class Times {

    public static void main(String[] args) {

        Q.p("Get the system clock as UTC offset");
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        Q.p();
        Q.p("Get the local date");
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(date);
        System.out.println(dateFromClock);

        Q.p();
        Q.p("Get the local time");
        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(time);
        System.out.println(timeFromClock);


        Q.p();
        Q.p("Get the local date/time");
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);
        System.out.println(datetime);
        System.out.println(datetimeFromClock);


        Q.p();
        Q.p("Get the zoned date/time");
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        final ZonedDateTime zonedDatetimeFromZone2 = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.println(zonedDatetime);
        System.out.println(zonedDatetimeFromClock);
        System.out.println(zonedDatetimeFromZone);
        System.out.println(zonedDatetimeFromZone2);


        Q.p();
        Q.p("Get duration between two dates");
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);
        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());
    }

}
