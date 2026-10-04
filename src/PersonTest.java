import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * JUnit 4 tests for the Person class.
 * Tests the constructors, the setters and the additional methods.
 */
public class PersonTest
{
    Person p1;
    Person p2;

    @Before
    public void setUp()
    {
        p1 = new Person("000001", "Bilbo", "Baggins", "Esq.", 1960);
        p2 = new Person("000002", "Frodo", "Baggins", 1990);
    }

    @Test
    public void constructorAllFields()
    {
        assertEquals("000001", p1.getID());
        assertEquals("Bilbo", p1.getFirstName());
        assertEquals("Baggins", p1.getLastName());
        assertEquals("Esq.", p1.getTitle());
        assertEquals(1960, p1.getYOB());
    }

    @Test
    public void constructorNoTitle()
    {
        assertEquals("000002", p2.getID());
        assertEquals("Frodo", p2.getFirstName());
        assertEquals("", p2.getTitle());
        assertEquals(1990, p2.getYOB());
    }

    @Test
    public void setFirstName()
    {
        p1.setFirstName("Bill");
        assertEquals("Bill", p1.getFirstName());
    }

    @Test
    public void setLastName()
    {
        p1.setLastName("Underhill");
        assertEquals("Underhill", p1.getLastName());
    }

    @Test
    public void setTitle()
    {
        p1.setTitle("Mr.");
        assertEquals("Mr.", p1.getTitle());
    }

    @Test
    public void setYOB()
    {
        p1.setYOB(1975);
        assertEquals(1975, p1.getYOB());
    }

    @Test
    public void fullName()
    {
        assertEquals("Bilbo Baggins", p1.fullName());
    }

    @Test
    public void formalName()
    {
        assertEquals("Esq. Bilbo Baggins", p1.formalName());
    }

    @Test
    public void getAge()
    {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals(String.valueOf(currentYear - 1960), p1.getAge());
    }

    @Test
    public void getAgeForYear()
    {
        assertEquals("40", p1.getAge(2000));
        assertEquals("10", p2.getAge(2000));
    }

    @Test
    public void toCSV()
    {
        assertEquals("000001, Bilbo, Baggins, Esq., 1960", p1.toCSV());
    }

    @Test
    public void toJSON()
    {
        assertEquals("{\"ID\":\"000001\",\"firstName\":\"Bilbo\",\"lastName\":\"Baggins\",\"title\":\"Esq.\",\"YOB\":1960}",
                p1.toJSON());
    }

    @Test
    public void toXML()
    {
        assertEquals("<Person><ID>000001</ID><firstName>Bilbo</firstName><lastName>Baggins</lastName>"
                + "<title>Esq.</title><YOB>1960</YOB></Person>", p1.toXML());
    }

    @Test
    public void testToString()
    {
        assertEquals("Person{ID='000001', firstName='Bilbo', lastName='Baggins', title='Esq.', YOB=1960}",
                p1.toString());
    }

    @Test
    public void testEquals()
    {
        Person copy = new Person("000001", "Bilbo", "Baggins", "Esq.", 1960);
        assertEquals(p1, copy);
        assertNotEquals(p1, p2);
    }
}
