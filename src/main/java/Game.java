public class Game {

    public static final int NUMBER_LENGTH = 3;
    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        if (guessNumber.equals(question)) {
            return new GuessResult(true, NUMBER_LENGTH, 0);
        } else {
            return getGuessResult(guessNumber);
        }
    }

    private GuessResult getGuessResult(String guessNumber) {
        int strikes = 0;
        int ball = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            if (guessNumber.charAt(i) == question.charAt(i)) {
                strikes++;
            } else if (question.indexOf(guessNumber.charAt(i)) != -1) {
                ball++;
            }
        }

        return new GuessResult(false, strikes, ball);
    }

    private void assertIllegalArgument(String guessNumber) {
        if (guessNumber == null) {
            throw new IllegalArgumentException();
        }
        if (guessNumber.length() != NUMBER_LENGTH) {
            throw new IllegalArgumentException();
        }

        for (char number : guessNumber.toCharArray()) {
            if (number < '0' || number > '9') {
                throw new IllegalArgumentException();
            }
        }

        if (isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}
