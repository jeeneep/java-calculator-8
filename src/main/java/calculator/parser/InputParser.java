package calculator.parser;

import calculator.domain.PositiveInteger;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputParser {

    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";
    private static final String CUSTOM_DELIMITER_SEPARATOR = "\\n";
    private static final String CUSTOM_DELIMITER_START = "//";

    public List<PositiveInteger> parse(String text) {
        if (text == null || text.trim().isEmpty()) {
            return List.of();
        }

        if (text.startsWith(CUSTOM_DELIMITER_START)) {
            return parseWithCustomDelimiter(text);
        }

        return parseWithDefaultDelimiters(text);
    }

    private List<PositiveInteger> parseWithDefaultDelimiters(String input) {
        String[] strings = input.split(DEFAULT_DELIMITER_REGEX);
        validateAllNumeric(strings);
        return toPositiveNumbers(strings);
    }

    private List<PositiveInteger> parseWithCustomDelimiter(String input) {
        int index = input.indexOf(CUSTOM_DELIMITER_SEPARATOR);
        if (index < 0) {
            throw new IllegalArgumentException("Invalid custom delimiter pattern: " + input);
        }

        String rawDelimiter = input.substring(CUSTOM_DELIMITER_START.length(), index);
        String quoted = Pattern.quote(rawDelimiter);
        String body = input.substring(index + CUSTOM_DELIMITER_SEPARATOR.length());

        if (body.trim().isEmpty()) {
            return List.of();
        }

        String[] strings = body.split(quoted);
        validateAllNumeric(strings);
        return toPositiveNumbers(strings);
    }

    private List<PositiveInteger> toPositiveNumbers(String[] strings) {
        return Arrays.stream(strings)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .map(PositiveInteger::new)
                .toList();
    }

    private void validateAllNumeric(String[] strings) {
        Arrays.stream(strings)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .forEach(this::assertDigitsOnly);
    }

    private void assertDigitsOnly(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력 문자열에 숫자가 아닌 값이 포함되어 있습니다.");
        }
    }
}