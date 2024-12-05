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
@Test
public void testBorrowReturn() throws Exception {
    // Set up
    Book book = new Book(101, "Test Book");
    Member member = new Member(1, "Test Member");
    Transaction transaction = Transaction.getTransaction();

    // Ensure book is initially available
    assertTrue(book.isAvailable());

    // Borrow the book
    boolean borrowed = transaction.borrowBook(book, member);
    assertTrue(borrowed);
    assertFalse(book.isAvailable());

    // Try to borrow again (should fail)
    boolean borrowAgain = transaction.borrowBook(book, member);
    assertFalse(borrowAgain);

    // Return the book
    boolean returned = transaction.returnBook(book, member);
    assertTrue(returned);
    assertTrue(book.isAvailable());

    // Try to return again (should fail)
    boolean returnAgain = transaction.returnBook(book, member);
    assertFalse(returnAgain);
}
@Test
public void testSingletonTransaction() throws Exception {
    // Ensure Singleton instance
    Transaction instance1 = Transaction.getTransaction();
    Transaction instance2 = Transaction.getTransaction();
    assertEquals(instance1, instance2);

    // Validate private constructor using reflection
    Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
}

