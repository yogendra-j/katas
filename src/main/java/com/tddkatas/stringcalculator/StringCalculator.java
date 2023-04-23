package com.tddkatas.stringcalculator;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StringCalculator {
    /**
     *
     */
    private static final String DEFAULT_DELIMETER = "[,\n]";
    private static final int UPPER_LIMIT = 1000;
    private static String delimeter;

    public StringCalculator() throws InstantiationException {
        throw new InstantiationException("Cannot be instantiated");
    }

    public static int add(String numbers) throws IllegalArgumentException {
        if (numbers.isEmpty())
            return 0;
        numbers = removeDelimeter(numbers);
        validateNumbersString(numbers);
        return calculateSumFromString(filterNumbers(numbers));
    }

    private static String removeDelimeter(String numbers) {
        if (numbers.startsWith("\\")) {
            numbers = handleCustomDelimeters(numbers);
        } else
            delimeter = DEFAULT_DELIMETER;
        return numbers;
    }

    private static String handleCustomDelimeters(String numbers) {
        if (numbers.startsWith("\\[")) {
            delimeter = "";
            int index = 1;
            while (numbers.charAt(index) == '[') {
                int delimeterEnd = numbers.indexOf(']', index);
                delimeter += delimeter.isEmpty() ? "" : "|";
                delimeter += numbers.substring(index + 1, delimeterEnd);
                index = delimeterEnd + 1;
            }
            delimeter = delimeter.replace("*", "\\*");
            numbers = numbers.substring(index + 1);
        } else {
            delimeter = numbers.substring(1, 2);
            numbers = numbers.substring(3);
        }
        return numbers;
    }

    private static int calculateSumFromString(IntStream numbers) {
        return numbers.sum();
    }

    private static void validateNumbersString(String numbers)
            throws IllegalArgumentException {
        final StringBuilder sb = new StringBuilder();
        convertStringToIntStream(numbers)
                .filter(number -> number < 0)
                .forEach(num -> {
                    sb.append(num);
                    sb.append(",");
                });
        if (sb.length() > 1) {
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private static IntStream filterNumbers(String numbers) {
        return convertStringToIntStream(numbers)
                .filter(number -> number < UPPER_LIMIT);
    }

    private static IntStream convertStringToIntStream(String numbers) {
        return Arrays.stream(numbers.split(delimeter))
                .mapToInt(Integer::parseInt);
    }

}
