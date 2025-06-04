import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void createGame() {
        assertNotNull(game);
    }

    private void assertIllegalArgument(String guessNumber) {
        try {
            game.guess(guessNumber);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    void throwIllegalArgumentExceptionInvalidInput() {
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    private void generateQuestion() {
        game.question = "123";
    }

    @Test
    void returnSolvedResultIfMatchedNumber() {
        generateQuestion();
        assertMatchedNumber(game.guess("123"), true, 3, 0);
    }

    @Test
    void returnSolvedResultIfUnMatchedNumber() {
        generateQuestion();
        assertMatchedNumber(game.guess("456"), false, 0, 0);
    }

    private static void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(solved);
        assertThat(result.getStrikes()).isEqualTo(strikes);
        assertThat(result.getBalls()).isEqualTo(balls);
    }

    @Test
    void test2StrikesAnd0Ball() {
        generateQuestion();
        assertMatchedNumber(game.guess("124"), false, 2, 0);
    }

    @Test
    void test1StrikesAnd2Ball() {
        generateQuestion();
        assertMatchedNumber(game.guess("132"), false, 1, 2);
    }
}