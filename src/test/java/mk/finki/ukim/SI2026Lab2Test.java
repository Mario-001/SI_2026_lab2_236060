package mk.finki.ukim;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SI2026Lab2Test {

    @Test
    void searchBookEveryStatementTest()
    {
        Library library = new Library();
        Library library2 = new Library();

        library.addBook(new Book("Clean Code","Robert C. Martin","Programming"));

        /**
         * Case 1: empty title -> expected exception
         */
        assertThrows(IllegalArgumentException.class, () -> library.searchBookByTitle(""));

        /**
         * Case 2: searching for 1 book -> expected book to be found
         */
        List<Book> bookList = library.searchBookByTitle("Clean Code");
        assertEquals("Clean Code", bookList.get(0).getTitle());

        /**
         * Case 3: Empty library with no books -> expected null
         */
        List<Book> bookList2 = library2.searchBookByTitle("Clean Code");
        assertNull(bookList2);

        /**
         * Case 4: Searching for a book that doesn't exist -> expected null
         */
        List<Book> bookList3 = library.searchBookByTitle("Random book");
        assertNull(bookList3);
    }

    @Test
    void borrowBookEveryBranchTest()
    {
        Library library = new Library();
        library.addBook(new Book("Clean Code","Robert C. Martin","Programming"));

        /**
         * Case 1: empty title and author -> expected exception
         */
        assertThrows(IllegalArgumentException.class,() -> library.borrowBook("",""));

        /**
         * Case 2: borrowing book -> expected the function to execute properly
         * thus not throwing any exceptions
         */
        assertDoesNotThrow(() ->
                library.borrowBook("Clean Code","Robert C. Martin"));

        /**
         * Case 3: borrowing a book that doesn't exist -> expected exception
         */
        assertThrows(RuntimeException.class, () -> library.borrowBook("xyz","xyz"));

        /**
         * Case 4: borrowing a book that is already borrowed -> expected exception
         */
        assertThrows(RuntimeException.class,() -> library.borrowBook("Clean Code","Robert C. Martin"));
    }

    @Test
    void borrowBookMultipleConditionTest()
    {
        Library library = new Library();
        library.addBook(new Book("Clean Code","Robert C. Martin","Programming"));

        /**
         * Case 1: borrowing a book where both title and author are empty
         */
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("",""));

        /**
         * Case 2: borrowing a book where title is present and author empty
         */
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("Clean Code",""));

        /**
         * Case 3: borrowing a book where title is empty and author present
         */
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("","Robert C. Martin"));

        /**
         * Case 4: borrowing a book where both title and author are present
         */
        assertDoesNotThrow(() ->
                library.borrowBook("Clean Code","Robert C. Martin"));
    }

    @Test
    void searchBookMultipleConditionTest()
    {
        Library library = new Library();
        library.addBook(new Book("Clean Code","Robert C. Martin","Programming"));

        /**
         * Case 1: Searching for a book that doesn't exist and is not borrowed
         * -> expected null
         */
        assertNull(library.searchBookByTitle("xyz"));

        /**
         * Case 2: Searching for a book that exists and isn't borrowed
         * -> expected the program to run normally
         */
        assertNotNull(library.searchBookByTitle("Clean Code"));

        library.borrowBook("Clean Code","Robert C. Martin");

        /**
         * Case 3: Searching for a book that exists but is borrowed -> expected null
         */
        assertNull(library.searchBookByTitle("Clean Code"));

        /**
         * Case 4: Searching for a book that doesn't exist and is borrowed
         * -> expected null
         */
        assertNull(library.searchBookByTitle("xyz"));

    }
}
