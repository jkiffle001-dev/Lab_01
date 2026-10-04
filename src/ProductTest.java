import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit 4 tests for the Product class.
 * Tests the constructors, the setters and the additional methods.
 */
public class ProductTest
{
    Product p1;
    Product p2;

    @Before
    public void setUp()
    {
        p1 = new Product("000001", "Pipeweed", "Long Bottom Leaf", 600.0);
        p2 = new Product("000002", "Lembas", 200.0);
    }

    @Test
    public void constructorAllFields()
    {
        assertEquals("000001", p1.getID());
        assertEquals("Pipeweed", p1.getName());
        assertEquals("Long Bottom Leaf", p1.getDescription());
        assertEquals(600.0, p1.getCost(), 0.001);
    }

    @Test
    public void constructorNoDescription()
    {
        assertEquals("000002", p2.getID());
        assertEquals("Lembas", p2.getName());
        assertEquals("", p2.getDescription());
        assertEquals(200.0, p2.getCost(), 0.001);
    }

    @Test
    public void setName()
    {
        p1.setName("Old Toby");
        assertEquals("Old Toby", p1.getName());
    }

    @Test
    public void setDescription()
    {
        p1.setDescription("Finest leaf in the Southfarthing");
        assertEquals("Finest leaf in the Southfarthing", p1.getDescription());
    }

    @Test
    public void setCost()
    {
        p1.setCost(650.5);
        assertEquals(650.5, p1.getCost(), 0.001);
    }

    @Test
    public void toCSV()
    {
        assertEquals("000001, Pipeweed, Long Bottom Leaf, 600.0", p1.toCSV());
    }

    @Test
    public void toJSON()
    {
        assertEquals("{\"ID\":\"000001\",\"name\":\"Pipeweed\",\"description\":\"Long Bottom Leaf\",\"cost\":600.0}",
                p1.toJSON());
    }

    @Test
    public void toXML()
    {
        assertEquals("<Product><ID>000001</ID><name>Pipeweed</name><description>Long Bottom Leaf</description>"
                + "<cost>600.0</cost></Product>", p1.toXML());
    }

    @Test
    public void testToString()
    {
        assertEquals("Product{ID='000001', name='Pipeweed', description='Long Bottom Leaf', cost=600.0}",
                p1.toString());
    }

    @Test
    public void testEquals()
    {
        Product copy = new Product("000001", "Pipeweed", "Long Bottom Leaf", 600.0);
        assertEquals(p1, copy);
        assertNotEquals(p1, p2);
    }
}
