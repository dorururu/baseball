public class Game {

    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        if (guessNumber.equals(question)) {
            return new GuessResult(true, 3, 0);
        } else {
            return getResult(guessNumber);
        }
    }

    private GuessResult getResult(String guessNumber) {
        int strikes = 0;
        int ball = 0;
        for (int i = 0; i < 3; i++) {
            if (guessNumber.charAt(i) == question.charAt(i)) {
                strikes++;
            } else if (question.indexOf(guessNumber.charAt(i)) != -1) {
                ball++;
            }
        }

        boolean isAnswer = strikes == 3;
        return new GuessResult(isAnswer, strikes, ball);
    }

    private void assertIllegalArgument(String guessNumber) {
        if (guessNumber == null) {
            throw new IllegalArgumentException();
        }
        if (guessNumber.length() != 3) {
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
