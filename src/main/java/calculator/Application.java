package calculator;

import calculator.domain.StringCalculator;
import calculator.parser.InputParser;
import calculator.view.ConsoleView;

public class Application {

    public static void main(String[] args) {

        ConsoleView view = new ConsoleView();
        InputParser parser = new InputParser();
        StringCalculator calculator = new StringCalculator(parser);

        String input = view.getInput();

        int result = calculator.add(input);

        view.printResult(result);
    }
}