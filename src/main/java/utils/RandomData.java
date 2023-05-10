package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

@Slf4j
public class RandomData extends RandomStringUtils {
    public static String createdWithAutotestsRandom() {
        return "Created with Autotests: " + LocalDateTime.now();
    }

    public static String randomAlphanumeric() {
        return randomAlphanumeric(10);
    }

    public static int randomNegativeNumber() {
        return (randomInt(10) + 1) * (-1);
    }

    public static String randomFloatingNumberWithTwoDots() {
        return randomNumeric(1, 5) + ".3.3";
    }

    public static <T> T randomArrayValue(T[] inputArray) {
        return inputArray[randomInt(inputArray.length)];
    }

    public static String[] randomArrayValues(String[] inputArray, int limit) {
        int returnedSize = Math.min(inputArray.length, limit);
        HashSet<String> resultSet = new HashSet<>();
        Collections.addAll(resultSet, inputArray);
        if (resultSet.size() > returnedSize) {
            resultSet.clear();
            while (resultSet.size() != returnedSize) {
                resultSet.add(randomArrayValue(inputArray));
            }
        }
        return resultSet.toArray(new String[returnedSize]);
    }

    public static boolean randomBoolean() {
        return RandomUtils.nextBoolean();
    }

    public static int randomInt(int endExclusive) {
        return randomInt(0, endExclusive);
    }

    public static int randomInt(int startInclusive, int endExclusive) {
        return RandomUtils.nextInt(startInclusive, endExclusive);
    }

    public static double randomDouble(double endExclusive) {
        return randomDouble(0, endExclusive, 2);
    }

    public static double randomDouble(int endExclusive) {
        return randomDouble(0, endExclusive, 2);
    }

    public static double randomDouble(int startInclusive, int endExclusive) {
        return randomDouble(startInclusive, endExclusive, 2);
    }

    public static String randomMail() {
        return random(10,true,true) +"@gmail.com";
    }

    public static double randomDouble(int startInclusive, int endExclusive, int decimalCount) {
        return randomDouble((double) startInclusive, (double) endExclusive, decimalCount);
    }

    public static double randomDouble(double startInclusive, double endExclusive, int decimalCount) {
        StringBuilder decimal = new StringBuilder();
        for (int i = 0; i < decimalCount - 1; i++) {
            decimal.append(randomInt(0, 9));
        }
        decimal.append(randomInt(1, 9));
        return Double.parseDouble(randomInt((int) Math.round(startInclusive), (int) Math.round(endExclusive)) + "." + decimal);
    }

    public static double randomDouble(double startInclusive, double endExclusive, int decimalCount, boolean decimalCountCouldBeLess) {
        if (decimalCountCouldBeLess) {
            return BigDecimal.valueOf(RandomData.randomDouble(startInclusive, endExclusive))
                    .setScale(decimalCount, RoundingMode.CEILING)
                    .doubleValue();
        } else
            return randomDouble(startInclusive, endExclusive, decimalCount);
    }

    public static double randomDouble(double startInclusive, double endExclusive) {
        return nextDouble(startInclusive, endExclusive);
    }

    public static String randomUrl(int randomAlphabeticCount) {
        return "http://www." + RandomData.randomAlphabetic(randomAlphabeticCount) + ".com";
    }

    public static String randomEmail() {
        return randomAlphanumeric(15) + "@gmail.com";
    }

    public static String getRandomFutureDate(String format) {
        LocalDateTime randomFutureDate = LocalDateTime.now().plusDays(randomInt(1000, 2000));
        return DateTimeFormatter.ofPattern(format).format(randomFutureDate);
    }

    public static String getRandomInvalidDate() {
        String year = String.valueOf(randomInt(2000, 3000));
        String invalidMonth = String.valueOf(randomInt(13, 99));
        String invalidDay = String.valueOf(randomInt(32, 99));
        return year + "-" + invalidMonth + "-" + invalidDay;
    }

    /**
     * This method can be used to get some amount of random values from the list
     *
     * @param list                 - list to get random values from
     * @param numberOfRandomValues - (Optional) number of values to be gotten from the list
     * @param <T>                  - type of objects that list stores
     * @return - new list of randomized objects from the provided list
     */
    public static <T> List<T> randomListValues(List<T> list, Integer... numberOfRandomValues) {
        if (numberOfRandomValues == null || numberOfRandomValues.length == 0) {
            numberOfRandomValues = new Integer[]{list.size() / 2};
        } else if (list.size() < numberOfRandomValues[0]) {
            log.warn("Number of values to be randomized from the list is more then list size. Method will return number of values: "
                    + list.size() / 2 + " (Half of list size)");
            numberOfRandomValues[0] = list.size() / 2;
        }

        int chunkSize = list.size() / numberOfRandomValues[0];
        ArrayList<T> listOfRandomValues = new ArrayList<>();

        AtomicInteger counter = new AtomicInteger();
        Collection<List<T>> subSets = list.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
                .values();

        for (List<T> subSet : subSets) {
            listOfRandomValues.add(subSet.get(RandomData.randomInt(subSet.size() - 1)));
        }
        return listOfRandomValues;
    }

    public static <T> T randomListValue(List<T> list) {
        return list.get(randomInt(list.size() - 1));
    }
}
