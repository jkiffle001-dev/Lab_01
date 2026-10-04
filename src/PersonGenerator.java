import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

/**
 * PersonGenerator collects Person data from the user with SafeInput, creates a
 * Person object for each record and stores it in an ArrayList&lt;Person&gt;.
 * When the user is finished, every Person is written to a CSV text file using
 * Person.toCSV().
 */
public class PersonGenerator
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();
        boolean done = false;

        System.out.println("Person Generator - enter the data for each person.");

        do
        {
            String id        = SafeInput.getRegExString(in, "Enter the ID (6 digits, e.g. 000001)", "\\d{6}");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter the first name");
            String lastName  = SafeInput.getNonZeroLenString(in, "Enter the last name");
            String title     = SafeInput.getNonZeroLenString(in, "Enter the title (Mr., Mrs., Ms., Dr., etc.)");
            int yob          = SafeInput.getRangedInt(in, "Enter the year of birth", 1940, 2010);

            // create the object as soon as we have the field data and add it to the list
            Person person = new Person(id, firstName, lastName, title, yob);
            people.add(person);
            System.out.println("Added: " + person.formalName() + " (age " + person.getAge() + ")");

            done = !SafeInput.getYNConfirm(in, "Do you want to add another person?");
        } while (!done);

        String fileName = SafeInput.getNonZeroLenString(in, "Enter the name of the file to save (e.g. PersonData.txt)");
        if (!fileName.toLowerCase().endsWith(".txt"))
        {
            fileName = fileName + ".txt";
        }

        Path file = Paths.get(System.getProperty("user.dir"), fileName);

        try
        {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE, TRUNCATE_EXISTING));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (Person p : people)
            {
                String rec = p.toCSV();
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("\n" + people.size() + " person record(s) written to " + file);
        }
        catch (IOException e)
        {
            System.out.println("Error writing the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
