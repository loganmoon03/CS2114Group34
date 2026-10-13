import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import student.TestCase;
/**
 * A testing class for the LinkedChain class.
 * 
 * @author David Sage
 * @version October 2026
 *
 */
public class LinkedChainTest extends student.TestCase {

    private LinkedChain<String> chain;

    public void setUp() {
        chain = new LinkedChain<String>();
    }

    /**
     * Tests add(), including front placement and null rejection.
     * 
     * Also tests get() and size() as they are used in the test.
     */
    public void testAdd() {
        assertTrue(chain.add("a"));
        assertEquals(1, chain.size());
        assertEquals("a", chain.get(0));
 
        assertTrue(chain.add("b"));
        assertTrue(chain.add("c"));
        assertEquals(3, chain.size());

        assertEquals("c", chain.get(0));
        assertEquals("b", chain.get(1));
        assertEquals("a", chain.get(2));

        Exception thrown = null;
        try {
            chain.add(null);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertEquals(3, chain.size());
        
    }

    /**
     * Tests addToEnd(), including empty chain, order, and null rejection.
     * 
     * Also tests toString() as it is used in the test.
     */
    public void testAddToEnd() {

        assertTrue(chain.addToEnd("a"));
        assertEquals(1, chain.size());
        assertEquals("a", chain.get(0));
 

        assertTrue(chain.addToEnd("b"));
        assertTrue(chain.addToEnd("c"));
        assertEquals(3, chain.size());
        assertEquals("a", chain.get(0));
        assertEquals("b", chain.get(1));
        assertEquals("c", chain.get(2));
 
        chain.add("front");
        chain.addToEnd("back");
        assertEquals("[front, a, b, c, back]", chain.toString());
 
        Exception thrown = null;
        try {
            chain.addToEnd(null);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertEquals(5, chain.size());
    }

    /**
     * Tests both remove() and remove(T).
     * 
     * Also tests clear(), count(), and isEmpty() as as it is used in the test.
     */
    public void testRemove() {

        assertNull(chain.remove());
        assertEquals(0, chain.size());
 

        assertFalse(chain.remove("a"));
 
        chain.addToEnd("a");
        chain.addToEnd("b");
        chain.addToEnd("c");
        chain.addToEnd("d");
 

        assertEquals("a", chain.remove());
        assertEquals(3, chain.size());
        assertEquals("[b, c, d]", chain.toString());
 
   
        assertFalse(chain.remove(null));
        assertEquals(3, chain.size());
 

        assertTrue(chain.remove("b"));
        assertEquals("[c, d]", chain.toString());


        assertTrue(chain.remove("d"));
        assertEquals("[c]", chain.toString());
 
  
        assertFalse(chain.remove("zzz"));
        assertEquals(1, chain.size());
 
  
        assertTrue(chain.remove("c"));
        assertTrue(chain.isEmpty());
        assertEquals("[]", chain.toString());
 

        chain.addToEnd("x");
        chain.addToEnd("y");
        chain.addToEnd("z");
        assertTrue(chain.remove("y"));
        assertEquals("[x, z]", chain.toString());
        assertEquals(2, chain.size());
 

        chain.clear();
        chain.addToEnd("dup");
        chain.addToEnd("other");
        chain.addToEnd("dup");
        assertTrue(chain.remove("dup"));
        assertEquals("[other, dup]", chain.toString());
        assertEquals(1, chain.count("dup"));
    }

    /**
     * More tests for get() including valid and invalid indices.
     */
    public void testGet() {
        chain.addToEnd("a");
        chain.addToEnd("b");
        chain.addToEnd("c");
 
        assertEquals("a", chain.get(0));
        assertEquals("b", chain.get(1));
        assertEquals("c", chain.get(2));
 
        // get does not remove
        assertEquals(3, chain.size());
 
        Exception thrown = null;
        try {
            chain.get(-1);
        }
        catch (IndexOutOfBoundsException e) {
            thrown = e;
        }
        assertNotNull(thrown);

 
        thrown = null;
        try {
            chain.get(3);
        }
        catch (IndexOutOfBoundsException e) {
            thrown = e;
        }
        assertNotNull(thrown);

 
        thrown = null;
        try {
            chain.get(100);
        }
        catch (IndexOutOfBoundsException e) {
            thrown = e;
        }
        assertNotNull(thrown);
 
 
        // empty chain
        chain.clear();
        thrown = null;
        try {
            chain.get(0);
        }
        catch (IndexOutOfBoundsException e) {
            thrown = e;
        }
        assertNotNull(thrown);

    }


    /**
     * Tests removeAt() for first, middle, last, and invalid indices.
     */
    public void testRemoveAt() {
        chain.addToEnd("a");
        chain.addToEnd("b");
        chain.addToEnd("c");
        chain.addToEnd("d");
 
        assertEquals("c", chain.removeAt(2));
        assertEquals("[a, b, d]", chain.toString());
        assertEquals(3, chain.size());
 

        assertEquals("a", chain.removeAt(0));
        assertEquals("[b, d]", chain.toString());
 

        assertEquals("d", chain.removeAt(1));
        assertEquals("[b]", chain.toString());
 

        assertEquals("b", chain.removeAt(0));
        assertTrue(chain.isEmpty());
 

        Exception thrown = null;
        try {
            chain.removeAt(0);
        }
        catch (IndexOutOfBoundsException e) {
            thrown = e;
        }
        assertNotNull(thrown);
 
        chain.addToEnd("a");
        chain.addToEnd("b");
 
        thrown = null;
        try {
            chain.removeAt(-1);
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
 

        thrown = null;
        try {
            chain.removeAt(2);
        }
        catch (IndexOutOfBoundsException e) {
            thrown = e;
        }
        assertNotNull(thrown);
 

        thrown = null;
        try {
            chain.removeAt(10);
        }
        catch (IndexOutOfBoundsException e) {
            thrown = e;
        }
        assertNotNull(thrown);
 

        assertEquals(2, chain.size());
        assertEquals("[a, b]", chain.toString());
    }

    /**
     * Tests contains().
     */
    public void testContains() {
        assertFalse(chain.contains("a"));
        assertFalse(chain.contains(null));
 
        chain.addToEnd("a");
        chain.addToEnd("b");
        chain.addToEnd("c");
 
        assertTrue(chain.contains("a"));
        assertTrue(chain.contains("b"));
        assertTrue(chain.contains("c"));
        assertFalse(chain.contains("d"));
        assertFalse(chain.contains(null));
 
        chain.remove("b");
        assertFalse(chain.contains("b"));
    }

    /**
     * Tests toArray() for exact, larger, empty, and invalid arrays.
     */
    public void testToArray() {

        String[] result = chain.toArray(new String[0]);
        assertEquals(0, result.length);
 
        chain.addToEnd("a");
        chain.addToEnd("b");
        chain.addToEnd("c");
 
        String[] exact = new String[3];
        result = chain.toArray(exact);
        assertSame(exact, result);
        assertEquals("a", result[0]);
        assertEquals("b", result[1]);
        assertEquals("c", result[2]);
 

        String[] larger = new String[5];
        result = chain.toArray(larger);
        assertEquals(5, result.length);
        assertEquals("a", result[0]);
        assertEquals("b", result[1]);
        assertEquals("c", result[2]);
        assertNull(result[3]);
        assertNull(result[4]);
 
        chain.clear();
        chain.add("x");
        chain.add("y");
        result = chain.toArray(new String[2]);
        assertEquals("y", result[0]);
        assertEquals("x", result[1]);
 
        Exception thrown = null;
        try {
            chain.toArray(null);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(thrown);
 
        thrown = null;
        try {
            chain.toArray(new String[1]);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(thrown);
    }

}