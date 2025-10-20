package calculator.domain;

public class PositiveInteger {

    private final int value;

    public PositiveInteger(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
        this.value = number;
    }

    public int getValue() {
        return value;
    }

    public int sum(int accumulatedSum) {
        return accumulatedSum + value;
    }
}