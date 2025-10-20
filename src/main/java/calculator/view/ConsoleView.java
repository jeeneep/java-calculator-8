package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleView {
    private static final String START_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    private static final String RESULT_PREFIX = "결과 : ";

    public String getInput() {
        System.out.println(START_MESSAGE);
        return Console.readLine();
    }

    public void printResult(int sum) {
        System.out.println(RESULT_PREFIX + sum);
    }

}