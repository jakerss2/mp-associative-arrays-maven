package edu.grinnell.csc207;

import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.NullKeyException;
import edu.grinnell.csc207.util.KeyNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Some additional tests from SamR.
 *
 * @author Samuel A. Rebelsky
 */
public class TestsFromSam {
  /**
   * A simple test.
   */
  @Test
  public void testSetAndGet() throws Exception {
    AssociativeArray<String, String> aa = 
        new AssociativeArray<String, String>();
    aa.set("a", "aardvark");
    assertEquals("aardvark", aa.get("a"), "M: We can get what we just set");
  } // testSetAndGet()

    /**
   * A test of cloning.
   * 
   * @throws NullKeyException
   */
  @Test
  public void rebelskySamuelTest01() throws NullKeyException {
    // Build an array
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    arr.set("A", "Apple");
    try {
      assertEquals("Apple", arr.get("A"));
    } catch (Exception e) {
      fail("Original array does not contain expected value");
    }
    // Make a copy
    AssociativeArray<String, String> arr2 = arr.clone();
    // Make sure it contains the appropriate value
    try {
      assertEquals("Apple", arr2.get("A"));
    } catch (Exception e) {
      fail("Clone does not contain original value");
    } // try/catch
    // Change the original array
    arr.set("A", "aardvark");
    // Make sure we haven't changed the clone.
    try {
      assertEquals("Apple", arr2.get("A"));
    } catch (Exception e) {
      fail("Change to original changes clone.");
    }
    // Change the clone
    arr2.set("A", "Ant");
    // And look for values
    try {
      assertEquals("Ant", arr2.get("A"));
    } catch (Exception e) {
      fail("Cannot change clone");
    }
    try {
      assertEquals("aardvark", arr.get("A"));
    } catch (Exception e) {
      fail("Change to clone changes original");
    }
  } // rebelskySamuelTest01()

  /**
   * Can we successfully add a bunch of values? (Checks array expansion.)
   * 
   * @throws NullKeyException
   */
  @Test
  public void rebelskySamuelTest02() throws NullKeyException {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    // Add a bunch of values
    for (int i = 10; i < 50; i++) {
      arr.set(i, i * i);
    } // for
    try {
      for (int i = 49; i >= 10; i--) {
        assertEquals(i * i, arr.get(i));
      } // for
    } catch (Exception e) {
      fail("Exception in call to get");
    } // try/catch
  } // rebelskySamuelest02()

  /**
   * Do we get exceptions when grabbing a deleted value from the array?
   * 
   * @throws NullKeyException
   */
  @Test
  public void rebelskySamuelTest03() throws NullKeyException {
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    // Add an element to the array
    arr.set("A", "Apple");
    // Make sure that it's there.
    try {
      assertEquals("Apple", arr.get("A"));
    } catch (KeyNotFoundException e) {
      fail("Could not set A to Apple");
    }
    // Remove it.
    arr.remove("A");
    // Make sure it's no longer there.
    try {
      // The following line should throw an exception
      arr.get("A");
      fail("Did not throw an exception");
    } catch (KeyNotFoundException e) {
      // Do nothing
    }
  } // rebelskySamuelTest03

  /**
   * Does `toString` work correctly on multi-element arrays? Intended to demonstrate how complicated
   * writing such a test might be.
   *
   * @throws NullKeyException In unexpected situations.
   */
  @Test
  public void rebelskySamuelTest04() throws NullKeyException {
    AssociativeArray<String, Integer> si = new AssociativeArray<String, Integer>();
    si.set("A", 1);
    si.set("B", 2);
    si.set("C", 3);
    String result = si.toString();
    System.err.println(result);
    assertTrue(result.equals("{A:1, B:2, C:3}") || result.equals("{A:1, C:3, B:2}")
        || result.equals("{B:2, A:1, C:3}") || result.equals("{B:2, C:3, A:1}")
        || result.equals("{C:3, A:1, B:2}") || result.equals("{C:3, B:2, A:1}"));
  } // rebelskySamuelTest04()

  /**
   * Do we get exceptions when grabbing a value from the empty array?
   */
  @Test
  public void rebelskySamuelEdge01() {
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    try {
      // The following line should throw an exception
      arr.get("A");
      fail("Did not throw an exception");
    } catch (KeyNotFoundException e) {
      // Do nothing
    } // try/catch
  } // rebelskySamuelEdge01
} // class TestsFromSam
