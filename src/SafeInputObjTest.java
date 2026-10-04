import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * JUnit 4 tests for SafeInputObj.
 * The methods are interactive, so each test builds a SafeInputObj around a
 * Scanner that reads from a String of simulated keyboard input. Bad input is
 * included first to show that each method rejects it and keeps asking.
 */
public class SafeInputObjTest
{
    private SafeInputObj withInput(String input)
    {
        return new SafeInputObj(new Scanner(input));
    }

    @Test
    public void getNonZeroLenString()
    {
        SafeInputObj sio = withInput("\n\nHello\n");
        assertEquals("Hello", sio.getNonZeroLenString("Enter text"));
    }

    @Test
    public void getRangedInt()
    {
        SafeInputObj sio = withInput("abc\n50\n7\n");
        assertEquals(7, sio.getRangedInt("Enter a number", 1, 10));
    }

    @Test
    public void getInt()
    {
        SafeInputObj sio = withInput("xyz\n-42\n");
        assertEquals(-42, sio.getInt("Enter an int"));
    }

    @Test
    public void getRangedDouble()
    {
        SafeInputObj sio = withInput("nope\n500.5\n12.5\n");
        assertEquals(12.5, sio.getRangedDouble("Enter a double", 0, 100), 0.001);
    }

    @Test
    public void getDouble()
    {
        SafeInputObj sio = withInput("bad\n3.14159\n");
        assertEquals(3.14159, sio.getDouble("Enter a double"), 0.00001);
    }

    @Test
    public void getYNConfirmYes()
    {
        SafeInputObj sio = withInput("maybe\ny\n");
        assertTrue(sio.getYNConfirm("Continue?"));
    }

    @Test
    public void getYNConfirmNo()
    {
        SafeInputObj sio = withInput("N\n");
        assertFalse(sio.getYNConfirm("Continue?"));
    }

    @Test
    public void getRegExString()
    {
        SafeInputObj sio = withInput("12\nABCDEF\n000123\n");
        assertEquals("000123", sio.getRegExString("Enter ID", "\\d{6}"));
    }
}
