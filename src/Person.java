import java.util.Calendar;
import java.util.Objects;

/**
 * Person models a person record with an ID, name, title and year of birth.
 * The ID never changes once the object is created, so it has no setter.
 */
public class Person
{
    private String firstName;
    private String lastName;
    private final String ID;   // should never change
    private String title;      // a prefix: Mr. Mrs. Ms. Prof. Dr. Hon. etc.
    private int YOB;           // year of birth, expected range 1940 - 2010

    /**
     * Full constructor that takes all of the fields.
     *
     * @param ID        the unique ID for the person (never changes)
     * @param firstName the first name
     * @param lastName  the last name
     * @param title     the title prefix such as Mr. or Dr.
     * @param YOB       the year of birth
     */
    public Person(String ID, String firstName, String lastName, String title, int YOB)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.YOB = YOB;
    }

    /**
     * Overloaded constructor for a person without a title.
     * The title is set to an empty String.
     *
     * @param ID        the unique ID for the person (never changes)
     * @param firstName the first name
     * @param lastName  the last name
     * @param YOB       the year of birth
     */
    public Person(String ID, String firstName, String lastName, int YOB)
    {
        this(ID, firstName, lastName, "", YOB);
    }

    /**
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName the new first name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName the new last name
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return the ID (there is no setter because the ID never changes)
     */
    public String getID()
    {
        return ID;
    }

    /**
     * @return the title prefix
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title the new title prefix
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return the year of birth
     */
    public int getYOB()
    {
        return YOB;
    }

    /**
     * @param YOB the new year of birth
     */
    public void setYOB(int YOB)
    {
        this.YOB = YOB;
    }

    /**
     * Builds the full name.
     *
     * @return firstName, a space, then lastName
     */
    public String fullName()
    {
        return firstName + " " + lastName;
    }

    /**
     * Builds the formal name.
     *
     * @return title, a space, then the full name
     */
    public String formalName()
    {
        return title + " " + fullName();
    }

    /**
     * Calculates the age of the person for the current year using Calendar.
     *
     * @return the age in years as a String
     */
    public String getAge()
    {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return getAge(currentYear);
    }

    /**
     * Calculates the age the person will be in a specified year.
     *
     * @param year the year to calculate the age for
     * @return the age in years as a String
     */
    public String getAge(int year)
    {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        int age = cal.get(Calendar.YEAR) - YOB;
        return String.valueOf(age);
    }

    /**
     * Creates a comma separated value record for writing to a text file.
     *
     * @return the CSV record: ID, firstName, lastName, title, YOB
     */
    public String toCSV()
    {
        return ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
    }

    /**
     * Creates a JSON representation of the person.
     *
     * @return the person as a JSON object String
     */
    public String toJSON()
    {
        final char DQ = '"';
        return "{" + DQ + "ID" + DQ + ":" + DQ + ID + DQ + ","
                + DQ + "firstName" + DQ + ":" + DQ + firstName + DQ + ","
                + DQ + "lastName" + DQ + ":" + DQ + lastName + DQ + ","
                + DQ + "title" + DQ + ":" + DQ + title + DQ + ","
                + DQ + "YOB" + DQ + ":" + YOB + "}";
    }

    /**
     * Creates an XML representation of the person.
     *
     * @return the person as an XML element String
     */
    public String toXML()
    {
        return "<Person>"
                + "<ID>" + ID + "</ID>"
                + "<firstName>" + firstName + "</firstName>"
                + "<lastName>" + lastName + "</lastName>"
                + "<title>" + title + "</title>"
                + "<YOB>" + YOB + "</YOB>"
                + "</Person>";
    }

    /**
     * @return a readable String with all of the fields
     */
    @Override
    public String toString()
    {
        return "Person{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", YOB=" + YOB +
                '}';
    }

    /**
     * Two Person objects are equal when all of their fields match.
     *
     * @param o the object to compare with
     * @return true if the objects hold the same data
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return YOB == person.YOB
                && Objects.equals(ID, person.ID)
                && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(title, person.title);
    }

    /**
     * @return a hash code consistent with equals()
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(ID, firstName, lastName, title, YOB);
    }
}
