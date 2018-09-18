import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
    @Test(expected = IllegalArgumentException.class)
    public void createRandomText_RightLimitsOfMinAndMax_ThrowException() {
        TextProc.createRandomText(0, 10, 1, 10);

    }

    @Test
    public void testWordsAmount() {

        TextProc nInst = new TextProc("Hale Hell");
        assertEquals(2, nInst.wordsAmount());


    }

    @Test
    public void testGetText() {

        TextProc nInst = new TextProc("Hale Hell");
        assertEquals("Hale Hell", nInst.getText());


    }

    @Test
    public void wordsUniqueness_NumberOfUniqueWords_True() {

        TextProc nInst = new TextProc("Hale Hell Hell Hell You Hale Me");
        assertEquals(2, nInst.wordsUniqueness(nInst.words()));


    }

}

