import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        TextProc.createRandomText(0, 10, 1, 10);

    }

    @Test
    public void shouldCompareAmount() {

        TextProc nInst = new TextProc("Hale Hell");
        assertEquals(2, nInst.wordsAmount());


    }

    @Test
    public void shouldTestTextEquality() {

        TextProc nInst = new TextProc("Hale Hell");
        assertEquals("Hale Hell", nInst.getText());


    }

}

