import javax.swing.JFileChooser;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * PersonReader lets the user pick a Person CSV file with JFileChooser. Each
 * line is split into fields, used to create a Person object, and stored in an
 * ArrayList&lt;Person&gt;. The table is then generated from the ArrayList.
 */
public class PersonReader
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        final int FIELDS_LENGTH = 5;

        do
        {
            ArrayList<Person> people = new ArrayList<>();
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                Path file = chooser.getSelectedFile().toPath();

                try
                {
                    InputStream input = new BufferedInputStream(Files.newInputStream(file));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    String rec;
                    while ((rec = reader.readLine()) != null)
                    {
                        if (rec.trim().isEmpty())
                        {
                            continue;
                        }
                        String[] fields = rec.split(",");
                        if (fields.length == FIELDS_LENGTH)
                        {
                            // create a Person from the fields and add it to the list
                            Person p = new Person(fields[0].trim(), fields[1].trim(), fields[2].trim(),
                                    fields[3].trim(), Integer.parseInt(fields[4].trim()));
                            people.add(p);
                        }
                        else
                        {
                            System.out.println("Found a record that may be corrupt: " + rec);
                        }
                    }
                    reader.close();
                }
                catch (IOException e)
                {
                    System.out.println("Error reading the file: " + e.getMessage());
                    e.printStackTrace();
                }
                catch (NumberFormatException e)
                {
                    System.out.println("The year of birth was not a number: " + e.getMessage());
                }

                // display the table from the ArrayList of Person objects
                System.out.println();
                System.out.printf("%-10s%-15s%-15s%-8s%6s%n", "ID#", "Firstname", "Lastname", "Title", "YOB");
                System.out.println("======================================================");
                for (Person p : people)
                {
                    System.out.printf("%-10s%-15s%-15s%-8s%6d%n",
                            p.getID(), p.getFirstName(), p.getLastName(), p.getTitle(), p.getYOB());
                }
                System.out.println("\n" + people.size() + " Person object(s) read.");
            }
            else
            {
                System.out.println("No file was chosen.");
            }
        } while (SafeInput.getYNConfirm(in, "Do you want to read another file?"));

        System.exit(0);
    }
}
