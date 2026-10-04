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
 * ProductReader lets the user pick a Product CSV file with JFileChooser. Each
 * line is split into fields, used to create a Product object, and stored in an
 * ArrayList&lt;Product&gt;. The table is then generated from the ArrayList.
 */
public class ProductReader
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        final int FIELDS_LENGTH = 4;

        do
        {
            ArrayList<Product> products = new ArrayList<>();
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
                            // create a Product from the fields and add it to the list
                            Product p = new Product(fields[0].trim(), fields[1].trim(), fields[2].trim(),
                                    Double.parseDouble(fields[3].trim()));
                            products.add(p);
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
                    System.out.println("The cost was not a number: " + e.getMessage());
                }

                // display the table from the ArrayList of Product objects
                System.out.println();
                System.out.printf("%-10s%-14s%-28s%10s%n", "ID#", "Name", "Description", "Cost");
                System.out.println("==============================================================");
                for (Product p : products)
                {
                    System.out.printf("%-10s%-14s%-28s%10.2f%n",
                            p.getID(), p.getName(), p.getDescription(), p.getCost());
                }
                System.out.println("\n" + products.size() + " Product object(s) read.");
            }
            else
            {
                System.out.println("No file was chosen.");
            }
        } while (SafeInput.getYNConfirm(in, "Do you want to read another file?"));

        System.exit(0);
    }
}
