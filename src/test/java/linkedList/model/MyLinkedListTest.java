package linkedList.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private List<String> strList = new MyLinkedList<>();

    @BeforeEach
    void setUp() {
        strList.add("Java");
        strList.add("JHTML");
        strList.add("CSS");
        strList.add("Javascript");
        strList.add("Angular");
        strList.add("Spring");
        strList.add("Hibernate");
    }

    @Test
    void size() {
        assertEquals(7, strList.size());
        assertNotEquals(6, strList.size());
    }

    @Test
    void indexOf() {
        assertEquals(strList.indexOf("Spring"), 5);
    }

    @Test
    void getElementByIndex() {
        assertNotNull(strList.getElementByIndex(3));
        assertNotNull(strList.getElementByIndex(0));
        assertNotNull(strList.getElementByIndex(6));
        assertThrows(IndexOutOfBoundsException.class, () -> strList.getElementByIndex(8));
    }

    @Test
    void setElementToIndex() {
        strList.setElementToIndex(0, "Andersen");
        strList.setElementToIndex(6, "JUnit");
        assertEquals("Andersen", strList.getElementByIndex(0));
        assertEquals("JUnit", strList.getElementByIndex(6));
        assertEquals(7, strList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> strList.setElementToIndex(7, "Fail"));
    }

    @Test
    void add() {
        strList.add("Andersen");
        assertEquals(8, strList.size());
        assertEquals("Andersen", strList.getElementByIndex(7));
        strList.add(1, "Andersen");
        assertEquals("Andersen", strList.getElementByIndex(1));
        assertEquals(9, strList.size());
    }

    @Test
    void remove() {
        strList.remove(0);
        assertEquals(6, strList.size());
        assertEquals("JHTML", strList.getElementByIndex(0));
        strList.remove("JHTML");
        assertEquals(5, strList.size());
        assertEquals("CSS", strList.getElementByIndex(0));
        assertThrows(IndexOutOfBoundsException.class, () -> strList.remove(8));
    }

    @Test
    void clear() {
        strList.clear();
        assertEquals(0, strList.size());
    }

    @Test
    void reverseOrder() {
        java.util.List test = new LinkedList<String>(Arrays.asList("Hibernate", "Spring", "Angular", "Javascript",
                "CSS", "JHTML", "Java"));
        strList.reverseOrder();
        assertIterableEquals(test, strList);
    }
}