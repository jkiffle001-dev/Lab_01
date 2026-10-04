import java.util.Objects;

/**
 * Product models a product record with an ID, name, description and cost.
 * The ID never changes once the object is created, so it has no setter.
 */
public class Product
{
    private String name;
    private String description;
    private final String ID;   // should never change
    private double cost;

    /**
     * Full constructor that takes all of the fields.
     *
     * @param ID          the unique ID for the product (never changes)
     * @param name        the product name
     * @param description a short description of the product
     * @param cost        the cost of the product
     */
    public Product(String ID, String name, String description, double cost)
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    /**
     * Overloaded constructor for a product without a description.
     * The description is set to an empty String.
     *
     * @param ID   the unique ID for the product (never changes)
     * @param name the product name
     * @param cost the cost of the product
     */
    public Product(String ID, String name, double cost)
    {
        this(ID, name, "", cost);
    }

    /**
     * @return the product name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the new product name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the new description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the ID (there is no setter because the ID never changes)
     */
    public String getID()
    {
        return ID;
    }

    /**
     * @return the cost
     */
    public double getCost()
    {
        return cost;
    }

    /**
     * @param cost the new cost
     */
    public void setCost(double cost)
    {
        this.cost = cost;
    }

    /**
     * Creates a comma separated value record for writing to a text file.
     *
     * @return the CSV record: ID, name, description, cost
     */
    public String toCSV()
    {
        return ID + ", " + name + ", " + description + ", " + cost;
    }

    /**
     * Creates a JSON representation of the product.
     *
     * @return the product as a JSON object String
     */
    public String toJSON()
    {
        final char DQ = '"';
        return "{" + DQ + "ID" + DQ + ":" + DQ + ID + DQ + ","
                + DQ + "name" + DQ + ":" + DQ + name + DQ + ","
                + DQ + "description" + DQ + ":" + DQ + description + DQ + ","
                + DQ + "cost" + DQ + ":" + cost + "}";
    }

    /**
     * Creates an XML representation of the product.
     *
     * @return the product as an XML element String
     */
    public String toXML()
    {
        return "<Product>"
                + "<ID>" + ID + "</ID>"
                + "<name>" + name + "</name>"
                + "<description>" + description + "</description>"
                + "<cost>" + cost + "</cost>"
                + "</Product>";
    }

    /**
     * @return a readable String with all of the fields
     */
    @Override
    public String toString()
    {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    /**
     * Two Product objects are equal when all of their fields match.
     *
     * @param o the object to compare with
     * @return true if the objects hold the same data
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.cost, cost) == 0
                && Objects.equals(ID, product.ID)
                && Objects.equals(name, product.name)
                && Objects.equals(description, product.description);
    }

    /**
     * @return a hash code consistent with equals()
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(ID, name, description, cost);
    }
}
