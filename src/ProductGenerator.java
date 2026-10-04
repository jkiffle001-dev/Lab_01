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
 * ProductGenerator collects Product data from the user with SafeInput, creates
 * a Product object for each record and stores it in an ArrayList&lt;Product&gt;.
 * When the user is finished, every Product is written to a CSV text file using
 * Product.toCSV().
 */
public class ProductGenerator
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        boolean done = false;

        System.out.println("Product Generator - enter the data for each product.");

        do
        {
            String id          = SafeInput.getRegExString(in, "Enter the ID (6 digits, e.g. 000001)", "\\d{6}");
            String name        = SafeInput.getNonZeroLenString(in, "Enter the product name");
            String description = SafeInput.getNonZeroLenString(in, "Enter a short description");
            double cost        = SafeInput.getRangedDouble(in, "Enter the cost", 0, 1000000);

            // create the object as soon as we have the field data and add it to the list
            Product product = new Product(id, name, description, cost);
            products.add(product);
            System.out.println("Added: " + product.getName());

            done = !SafeInput.getYNConfirm(in, "Do you want to add another product?");
        } while (!done);

        String fileName = SafeInput.getNonZeroLenString(in, "Enter the name of the file to save (e.g. ProductData.txt)");
        if (!fileName.toLowerCase().endsWith(".txt"))
        {
            fileName = fileName + ".txt";
        }

        Path file = Paths.get(System.getProperty("user.dir"), fileName);

        try
        {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE, TRUNCATE_EXISTING));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (Product p : products)
            {
                String rec = p.toCSV();
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("\n" + products.size() + " product record(s) written to " + file);
        }
        catch (IOException e)
        {
            System.out.println("Error writing the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
