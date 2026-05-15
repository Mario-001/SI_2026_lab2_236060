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
         * Case 3: Searching for a book that doesn't exist -> expected null
         */
        List<Book> bookList3 = library.searchBookByTitle("Random book");
        assertNull(bookList3);
    }

    @Test
    void borrowBook()
    {

    }
}