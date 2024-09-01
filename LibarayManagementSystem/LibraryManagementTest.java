import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LibraryManagementTest {
    private Library library;

    @Before
    public void setUp() {
        library = new Library(10); // Initialize library with capacity 10
    }

    //Testing of adding Book
    @Test
    public void testAddBook() {
        Book book1 = new Book("Title1", "Author1", "ISBN1", 2020);
        library.addBook(book1);
        assertEquals(1, library.getNumBooks());
    }

    // Testcase of Duplicate Book not add (it's already available ))
    @Test
    public void testAddDuplicateBook() {
        Book book1 = new Book("Title1", "Author1", "ISBN1", 2020);
        Book book2 = new Book("Title2", "Author2", "ISBN1", 2021); // Duplicate ISBN
        library.addBook(book1);
        library.addBook(book2);
        assertEquals("Should not add a duplicate ISBN.", 1, library.getNumBooks());
    }

    //Testcase of Borrow book
    @Test
    public void testBorrowBook() {
        Book book = new Book("Title", "Author", "ISBN", 2020);
        library.addBook(book);
        library.borrowBook("ISBN");
        assertFalse(book.isAvailable());
    }

    // TestCase of BorrowBook is available or Not
    @Test
    public void testBorrowUnavailableBook() {
        Book book = new Book("Title", "Author", "ISBN", 2020);
        library.addBook(book);
        library.borrowBook("ISBN"); // Borrow the book first

        // Try to borrow the book again and check if exception is thrown
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            library.borrowBook("ISBN"); // Try to borrow again
        });

        assertEquals("Book is already borrowed.", exception.getMessage());
    }

    // Testcase of  return book
    @Test
    public void testReturnBook() {
        Book book = new Book("Title", "Author", "ISBN", 2020);
        library.addBook(book);
        library.borrowBook("ISBN");
        library.returnBook("ISBN");
        assertTrue(book.isAvailable());
    }

    // Testcase of Unknown book is return
    @Test
    public void testReturnBookNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.returnBook("NonExistentISBN");
        });
        assertEquals("The book \"NonExistentISBN\" was not found in the library.", exception.getMessage());
    }
    
    // Both TestCase of Book iD is unique or or
    @Test
    public void testAddBookWithUniqueISBN() {
        Book book1 = new Book("Title1", "Author1", "ISBN1", 2020);
        Book book2 = new Book("Title2", "Author2", "ISBN2", 2021);

        library.addBook(book1);
        library.addBook(book2);

        assertEquals("Should add both books with unique ISBNs", 2, library.getNumBooks());
    }

    @Test
    public void testAddBookWithDuplicateISBN() {
        Book book1 = new Book("Title1", "Author1", "ISBN1", 2020);
        Book book2 = new Book("Title2", "Author2", "ISBN1", 2021); // Duplicate ISBN

        library.addBook(book1);
        library.addBook(book2);

        assertEquals("Should not add a book with a duplicate ISBN", 1, library.getNumBooks());
    }
}
