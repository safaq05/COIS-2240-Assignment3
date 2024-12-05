import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

class LibraryManagementTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
@Test
public void testBookId() throws Exception {
    // Valid cases
    Book validBook1 = new Book(100, "Valid Book");
    Book validBook2 = new Book(999, "Another Valid Book");
    assertNotNull(validBook1);
    assertNotNull(validBook2);

    // Invalid cases
    try {
        new Book(99, "Invalid Book");
        fail("Exception not thrown for ID 99");
    } catch (Exception e) {
        assertEquals("Invalid Book ID: 99", e.getMessage());
    }

    try {
        new Book(1000, "Invalid Book");
        fail("Exception not thrown for ID 1000");
    } catch (Exception e) {
        assertEquals("Invalid Book ID: 1000", e.getMessage());
    }
}

