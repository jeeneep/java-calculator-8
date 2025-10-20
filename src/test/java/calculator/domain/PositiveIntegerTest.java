package calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositiveIntegerTest {

    @DisplayName("유효한 숫자 값으로 PositiveInteger 객체를 생성하고 값을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void createPositiveInteger(int input) {
        PositiveInteger number = new PositiveInteger(input);
        assertThat(number.getValue()).isEqualTo(input);
    }

    @DisplayName("음수 입력 시 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void throwExceptionWhenNegative(int input) {
        assertThatThrownBy(() -> new PositiveInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 입력할 수 없습니다.");
    }

    // 🌟 참고: 현재 PositiveInteger는 int를 인자로 받으므로, 0을 검사하는 케이스는 추가하지 않습니다.
    // (InputParser에서 String을 int로 변환 후 PositiveInteger를 호출하므로, 이 로직은 InputParserTest에서 커버됨)

    @DisplayName("기존 합계에 자신의 값을 더한 결과를 반환한다.")
    @Test
    void sumWithAccumulatedValue() {
        PositiveInteger number = new PositiveInteger(5);
        int accumulatedSum = 10;

        int result = number.sum(accumulatedSum);

        assertThat(result).isEqualTo(15);
    }
}