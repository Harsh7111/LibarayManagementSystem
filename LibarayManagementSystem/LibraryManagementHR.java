
import java.util.Scanner;

// Book Class.........
class Book {
    private String title;
    private String author;
    private String ISBN;
    private int year;
    private boolean isAvailable;

    public Book(String title, String author, String ISBN, int year) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.year = year;
        this.isAvailable = true;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            throw new IllegalStateException("Book is already borrowed.");
        }
    }

    public void returnBook() {
        isAvailable = true;
    }
    
    @Override // Convert all data in string Type
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + ISBN + "\nYear: " + year + "\nAvailable: "
                + (isAvailable ? "Yes" : "No");
    }
}
// Library class 
class Library {
    private Book[] books;
    private int numBooks;

    public Library(int size) {
        books = new Book[size];
        numBooks = 0;
    }
    // Adding book function
    public void addBook(Book book) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i].getISBN().equalsIgnoreCase(book.getISBN())) {
                System.out.println("A book with ISBN \"" + book.getISBN() + "\" already exists in the library.");
                return;
            }
        }
        if (numBooks < books.length) {
            books[numBooks] = book;
            numBooks++;
            System.out.println("Book added successfully.");
        } else {
            System.out.println("The library is full.");
        }
    }

    public int getNumBooks() {
        return numBooks;
    }
    // Show all the books function
    public void printBooks() {

        int availableCount = 0;

        if (numBooks != 0) {
            for (int i = 0; i < numBooks; i++) {
                System.out.println(books[i]);
                System.out.println();

                // Count how many books are available
                if (books[i].isAvailable()) {
                    availableCount++;
                }
            }
            System.out.println("Total books in the library: " + numBooks);
            System.out.println("Available books for borrowing: " + availableCount);
        } else {
            System.out.println("No books in the library.");
        }
    }
    // Borrowing Book Function
    public void borrowBook(String ISBN) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i].getISBN().equalsIgnoreCase(ISBN)) {
                if (books[i].isAvailable()) {
                    books[i].borrowBook();
                    System.out.println("You have successfully borrowed: " + ISBN);
                } else {
                    throw new IllegalStateException("Book is already borrowed."); // Ensure this line is present
                }
                return;
            }
        }
        System.out.println("The book \"" + ISBN + "\" was not found in the library.");
    }
    // Return Book function
    public void returnBook(String ISBN) {
        boolean found = false;
        for (int i = 0; i < numBooks; i++) {
            if (books[i].getISBN().equalsIgnoreCase(ISBN)) {
                found = true;
                if (!books[i].isAvailable()) {
                    books[i].returnBook();
                    System.out.println("You have successfully returned: " + ISBN);
                } else {
                    System.out.println("The book \"" + ISBN + "\" was not borrowed.");
                }
                return;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("The book \"" + ISBN + "\" was not found in the library.");
        }
    }
}
// Main LibraryManagement Class
public class LibraryManagementHR {
    private static Scanner scanner = new Scanner(System.in);
    private Library library = new Library(10);
    
    //Adding details of book  
    public void addBooksFun(int numberOfBooks) {
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println("Enter details for book " + (i + 1) + ":");

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Author: ");
            String author = scanner.nextLine();

            System.out.print("ISBN: ");
            String ISBN = scanner.nextLine();

            System.out.print("Passing year ");
            int year = scanner.nextInt();
            scanner.nextLine();

            library.addBook(new Book(title, author, ISBN, year));

        }
    }

    
    public void borrowBookFun() {
        System.out.print("Enter the ISBN Code of the book you want to borrow: ");
        String ISBN = scanner.nextLine();
        library.borrowBook(ISBN);
    }

    public void returnBookFun() {
        System.out.print("Enter the ISBN of the book you want to return: ");
        String ISBN = scanner.nextLine();
        library.returnBook(ISBN);
    }

    public static void main(String[] args) {
        LibraryManagementHR lm = new LibraryManagementHR(); //Create Obj 
        boolean continuing = true;

        //Code of user option 
        while (continuing) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add books");
            System.out.println("2. Print books");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int selection = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (selection) {
                case 1:
                    System.out.println("Enter the number of books you want to add:");
                    int numberOfBooks = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    lm.addBooksFun(numberOfBooks);
                    break;
                case 2:
                    System.out.println("Books in the library:");
                    lm.library.printBooks();
                    break;
                case 3:
                    lm.borrowBookFun();
                    break;
                case 4:
                    lm.returnBookFun();
                    break;
                case 5:
                    continuing = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
