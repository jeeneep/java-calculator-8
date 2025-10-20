package calculator.domain;

import calculator.parser.InputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator(new InputParser());
    }

    @DisplayName("null 또는 빈 문자열을 입력하면 0을 반환해야 한다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void returnZeroWhenInputIsNull(String input) {
        assertThat(calculator.add(input)).isZero();
    }

    @DisplayName("기본 및 커스텀 구분자를 사용하여 합계를 정확히 계산한다.")
    @ParameterizedTest
    @MethodSource("provideCalculatorTestCases")
    void calculateSumWithVariousDelimiters(String input, Integer expectedSum) {
        assertThat(calculator.add(input)).isEqualTo(expectedSum);
    }

    @DisplayName("숫자가 아닌 값이나 음수가 포함된 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "1,a",          // 숫자가 아닌 값
            "1:-2",         // 음수
            "//;\n1;3;a",   // 커스텀 구분자 사용 시 숫자가 아닌 값
            "1,2,-3:4",     // 혼합 사용 시 음수
            "//;\n1;2"      // 잘못된 커스텀 구분자 패턴 (개행문자 \n 누락)
    })
    void throwExceptionWhenInvalidInput(String input) {
        assertThatThrownBy(() -> calculator.add(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideCalculatorTestCases() {
        return Stream.of(
                // 쉼표/콜론 기본 구분자
                Arguments.of("1,2,3", 6),
                Arguments.of("1:2:3", 6),
                Arguments.of("1,2:3", 6),
                // 커스텀 구분자
                Arguments.of("//;\\n1;2;3", 6),
                // 숫자 하나
                Arguments.of("5", 5)
        );
    }
}