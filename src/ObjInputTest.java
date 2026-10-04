/**
 * ObjInputTest is a short program that exercises every method of SafeInputObj
 * so the output can be captured to show that each one works.
 */
public class ObjInputTest
{
    public static void main(String[] args)
    {
        SafeInputObj sio = new SafeInputObj();

        System.out.println("=== Testing SafeInputObj ===");

        String name = sio.getNonZeroLenString("getNonZeroLenString - enter your name");
        System.out.println("You entered: " + name);

        int anyInt = sio.getInt("getInt - enter any whole number");
        System.out.println("You entered: " + anyInt);

        int rangedInt = sio.getRangedInt("getRangedInt - enter a number from 1 to 10 ", 1, 10);
        System.out.println("You entered: " + rangedInt);

        double anyDouble = sio.getDouble("getDouble - enter any decimal number");
        System.out.println("You entered: " + anyDouble);

        double rangedDouble = sio.getRangedDouble("getRangedDouble - enter a price from 0 to 100 ", 0, 100);
        System.out.println("You entered: " + rangedDouble);

        String id = sio.getRegExString("getRegExString - enter a 6 digit ID", "\\d{6}");
        System.out.println("You entered: " + id);

        boolean yes = sio.getYNConfirm("getYNConfirm - do you like Java?");
        System.out.println("You answered: " + (yes ? "Yes" : "No"));

        System.out.println("\n=== All SafeInputObj methods tested ===");
    }
}
