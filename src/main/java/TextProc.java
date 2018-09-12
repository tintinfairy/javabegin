import org.apache.commons.lang.RandomStringUtils;
import java.util.Random;

public class TextProc {

    private String text;

    public TextProc(String name) {
        this.text = name;
    }

    public TextProc() {
        this.text = generateRandomText();
    }

    public static String generateRandomText() {
        return createRandomText(1, 10, 1, 10);
    }

    public static String createRandomText(int minWordsAmount,
                                          int maxWordsAmount,
                                          int minWordLength,
                                          int maxWordLength) {
        if (minWordsAmount == 0 ||
                minWordsAmount > maxWordsAmount ||
                minWordLength == 0 ||
                minWordLength > maxWordLength) {
            throw new IllegalArgumentException("incorrect amount or length range");
        }

        Random rnd = new Random(System.currentTimeMillis());
        int wordsAmount = minWordsAmount + rnd.nextInt(maxWordsAmount - minWordsAmount);
        final boolean useLetters = true;
        final boolean useNumbers = false;

        String[] words = new String[wordsAmount];
        for (int i = 0; i < wordsAmount; i++) {
            int wordLength = minWordLength + rnd.nextInt(maxWordLength - minWordLength);
            words[i] = RandomStringUtils.random(wordLength, useLetters, useNumbers);
        }

        return String.join(" ", words);
    }

    public String getText() {
        return text;
    }

    /**
     * @return количество слов в тексте
     */
    public int wordsAmount() {
        return words().length;
    }

    /**
     * @return массив слов в тексте
     */
    public String[] words() {
        return text.split("\\s");
    }

}
