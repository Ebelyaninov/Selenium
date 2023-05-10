package ui.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;

import static utils.RandomData.randomInt;
public class DateFormat {
    public static final String EXTENDED_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static String secondsToStringDate(long dateInMilliseconds, String format) {
        LocalDateTime date = Instant.ofEpochMilli(dateInMilliseconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);
        return date.format(timeFormatter);
    }

    public static String secondsToStringDate(long dateInMilliseconds) {
        return secondsToStringDate(dateInMilliseconds, DEFAULT_DATE_FORMAT);
    }

    public static String getCurrentDate(String format) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now().format(timeFormatter);
    }

    public static String getCurrentDate() {
        return getCurrentDate(DEFAULT_DATE_FORMAT);
    }

    public static String getCurrentDateTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now()).toString();
    }

    public static long getCurrentDateInMillis() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String getYesterdayDate() {
        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        return LocalDateTime.ofInstant(yesterday, ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    public static String getFirstDayOfCurrentWeek() {
        LocalDateTime firstDayOfWeek = LocalDateTime.now().with(WeekFields.ISO.getFirstDayOfWeek());
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(firstDayOfWeek);
    }

    public static String getFirstDayOfCurrentMonth() {
        LocalDateTime firstDayOfMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(firstDayOfMonth);
    }

    public static String getFirstDayOfCurrentYear() {
        LocalDateTime firstDayOfYear = LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear());
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(firstDayOfYear);
    }

    public static String getFirstDayOfNextYear() {
        LocalDateTime firstDayOfNextYear = LocalDateTime.now().with(TemporalAdjusters.firstDayOfNextYear());
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(firstDayOfNextYear);
    }

    public static String getFirstDayOfPreviousWeek() {
        LocalDateTime weekStart = LocalDateTime.now().minusDays(7 + LocalDateTime.now().getDayOfWeek().getValue() - 1);
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(weekStart);
    }

    public static String getLastDayOfPreviousWeek() {
        LocalDateTime weekEnd = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfWeek().getValue());
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(weekEnd);
    }

    public static String getThirtyDaysAgoDate() {
        LocalDateTime thirtyDaysAgoDate = LocalDateTime.now().minusMonths(1);
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(thirtyDaysAgoDate);
    }

    public static String getFirstDayOfPreviousMonth() {
        LocalDateTime monthStart = LocalDateTime.now().minusMonths(1).withDayOfMonth(1);
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(monthStart);
    }

    public static Instant getDayOfPreviousMonthInstant(int day) {
        LocalDateTime monthStart = LocalDateTime.now().minusMonths(1).withDayOfMonth(day);
        return monthStart.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static String getLastDayOfPreviousMonth() {
        LocalDateTime monthEnd = LocalDateTime.now().minusMonths(1).withDayOfMonth(LocalDateTime.now().minusMonths(1).getMonth().maxLength());
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).format(monthEnd);
    }

    public static LocalDate parseDateFromStringToDateFormat(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    public static LocalDateTime parseDateFromStringToDateFormat(String dateStr, String format) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(format));
    }

    public static String formatDateTime(LocalDateTime dateTime, String format) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(timeFormatter);
    }

    public static boolean checkIfDateMatchesToPeriod(String checkableDataStr, String periodFrom, String periodTo) {
        return checkIfDateMatchesToPeriod(parseDateFromStringToDateFormat(checkableDataStr),
                parseDateFromStringToDateFormat(periodFrom), parseDateFromStringToDateFormat(periodTo));
    }

    public static boolean checkIfDateMatchesToPeriod(LocalDate checkableData, LocalDate periodFrom, LocalDate periodTo) {
        return !(checkableData.isBefore(periodFrom) && checkableData.isAfter(periodTo));
    }

    public static Instant getFutureDateInstant() {
        LocalDateTime randomFutureDate = LocalDateTime.now().plusDays(randomInt(7, 14));
        return randomFutureDate.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static long getFutureDateMilli() {
        return getFutureDateInstant().toEpochMilli();
    }

    public static long getTodayDateMilli() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long getFutureDateSecond() {
        return getFutureDateInstant().getEpochSecond();
    }

    public static LocalDateTime getFutureDate() {
        return LocalDateTime.now().plusDays(randomInt(7, 14));
    }

    public static LocalDateTime getFutureDate(int startInclusive, int endInclusive) {
        return LocalDateTime.now().plusDays(randomInt(startInclusive, endInclusive));
    }
}
