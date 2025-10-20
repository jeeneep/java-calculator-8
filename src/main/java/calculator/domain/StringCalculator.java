package calculator.domain;

import calculator.parser.InputParser;
import java.util.List;

public class StringCalculator {

    private final InputParser parser;

    public StringCalculator(InputParser parser) {
        this.parser = parser;
    }

    public int add(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        List<PositiveInteger> numbers = parser.parse(text);

        return numbers.stream()
                .mapToInt(PositiveInteger::getValue)
                .sum();
    }
}