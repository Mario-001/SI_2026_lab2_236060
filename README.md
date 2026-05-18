

# Марио Димески (236060)

##  Анализа на функциите

### 1. searchBookByTitle
Оваа функција овозможува пребарување на достапни книги според нивниот наслов.

<p align="center">
  <img src="searchBookByTitle.jpg" width="600" alt="searchBookByTitle Diagram">
  <br>
  <i>Слика 1: Граф на контролен тек за searchBookByTitle</i>
</p>

**Цикломатска комплексност:**
Цикломатската комплексност за првиот граф го решив така што го зедов и ги изброив предикатните јазли  ($P$) и воедно ја искористив формулата **CC = P + 1**:
* **P = 5** (if за празен наслов, for циклус, два услови во if со &&, и последен if за празна листа).
* **CC = P + 1 = 6**

---

### 2. borrowBook
Функција за изнајмување на книга со проверка на автор и статус на достапност.

<p align="center">
  <img src="borrowBook.jpg" width="600" alt="borrowBook Diagram">
  <br>
  <i>Слика 2: Граф на контролен тек за borrowBook</i>
</p>

**Цикломатска комплексност:**
Цикломатската комплексност ја пресметав на истиот начин каде што ги броев предикатните јазли и ја искористив истата формула **CC = P + 1**:
* **P = 6** (|| кај наслов/автор, for циклус, && кај наслов/автор во циклусот, и if за статус на изнајмување).
* **CC = P + 1 = 7**

---
### Тест случаи според критериумот Every Statement
### Функцијата - searchBookByTitle
### Тест 1 :
 
 ```java
 /**  
 * Case 1: empty title -> expected exception
 */ 
 assertThrows(IllegalArgumentException.class, () -> library.searchBookByTitle(""));
 ```
 
со овај тест пример ги покриваме линиите од **57** - **58** односно
```java
57 if (title.isEmpty() || author.isEmpty()) {
58  throw new IllegalArgumentException("Invalid search query");
}
```
---
### Тест 2:
```java
/**  
 * Case 2: searching for 1 book -> expected book to be found 
 */
 assertEquals("Clean Code", bookList.get(0).getTitle());
```

со овај тест ги поркиваме линиите **57 па 60 - 63 па 66 па 69** 
```java
57 if (title.isEmpty())
```

```java
60 List<Book> results = new ArrayList<Book>();  
61 for (Book book : books) {  
62    if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) {  
63        results.add(book);
        }
}
```

```java
66 if (results.isEmpty()) {  
67    return null;  
}
```
---
### Тест 3
```java
/**  
 * Case 3: Empty library with no books -> expected null 
 */
 assertNull(bookList2);
```

со овај тест ги покриваме линиите **57 па 60 - 61 па 66 - 67**
```java
57 if (title.isEmpty() || author.isEmpty()){  
    throw new IllegalArgumentException("Invalid search query");  
}
```

```java
60 List<Book> results = new ArrayList<Book>();  
61 for (Book book : books) {  
    if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) {  
        results.add(book);  
    }  
}
```

```java
66 if (results.isEmpty()) {  
67    return null;  
}
```
---
### Тест 4
```java
/**  
 * Case 4: Searching for a book that doesn't exist -> expected null
 */
 assertNull(bookList3);
```

со овај тест ги покриваме линиите **57 па 60 - 62 па 66 - 67**
```java
57 if (title.isEmpty() || author.isEmpty()){  
    throw new IllegalArgumentException("Invalid search query");  
}
```

```java
60 List<Book> results = new ArrayList<Book>();  
61 for (Book book : books) {  
62    if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) {  
        results.add(book);  
    }  
}
```

```java
66 if (results.isEmpty()) {  
67    return null;  
}
```
---
## Тест случаи според критериумот Every Branch

### Функцијата - searchBookByTitle

Оваа функција 	овозможува позајмување на книга доколку не е веќе позајмена

### Тест 1
```java
/**  
 * Case 1: empty title and author -> expected exception
 */
assertThrows(IllegalArgumentException.class,() -> library.borrowBook("",""));
```
Со овај тест добиваме **true** на првата гранка:
```java
if (title.isEmpty() || author.isEmpty()){  
    throw new IllegalArgumentException("Invalid search query");  
}
```
--- 
### Тест 2
```java
/**  
 * Case 2: borrowing book -> expected the function to execute properly
 * thus not throwing any exceptions
 */
  assertDoesNotThrow(() ->  
        library.borrowBook("Clean Code","Robert C. Martin"));
```
Со овај тест добиваме **false** на првата гранка
```java
if (title.isEmpty() || author.isEmpty()){  
    throw new IllegalArgumentException("Invalid search query");  
}
```
Добиваме **true** на втората гранка:
```java
if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {  
//  if (!book.isBorrowed()) {  
//      book.setBorrowed(true);  
//      System.out.println("Borrowed successfully");  
//   } else {  
//     throw new RuntimeException("Book is already borrowed.");  
//   }  
     return;  
    }  
```
Добиваме **true** на третата гранка:
```java
if (!book.isBorrowed()) {  
    book.setBorrowed(true);  
    System.out.println("Borrowed successfully");  
} else {  
    throw new RuntimeException("Book is already borrowed.");  
}
```
---
### Тест 3
```java
/**  
 * Case 3: borrowing a book that doesn't exist -> expected exception 
 */
assertThrows(RuntimeException.class, () -> library.borrowBook("xyz","xyz"));
```
Со овај тест добиваме **false** на првата гранка:
```java
if (title.isEmpty() || author.isEmpty()){  
    throw new IllegalArgumentException("Invalid search query");  
}
```
Добиваме **false** на втората гранка, и со тоа не стасуваме воопшто до третата гранка.

---
### Тест 4

```java
/**  
 * Case 4: borrowing a book that is already borrowed -> expected exception
 */
assertThrows(RuntimeException.class,() -> library.borrowBook("Clean Code","Robert C. Martin"));
```
Со овај тест добиваме **false** на првата гранка:
```java
if (title.isEmpty() || author.isEmpty()){  
    throw new IllegalArgumentException("Invalid search query");  
}
```
Добиваме **true** на втората гранка:
```java
if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {  
//  if (!book.isBorrowed()) {  
//      book.setBorrowed(true);  
//      System.out.println("Borrowed successfully");  
//   } else {  
//     throw new RuntimeException("Book is already borrowed.");  
//   }  
     return;  
    }  
```
Добиваме **false** на третата гранка:
```java
if (!book.isBorrowed()) {  
    book.setBorrowed(true);  
    System.out.println("Borrowed successfully");  
} else {  
    throw new RuntimeException("Book is already borrowed.");  
}
```
#### Заклучок: потребно е минимум 4 тест случаи за да се покрие функцијата "borrowBook" а воедно ги исполлнува условите за "Every Branch" критериумот
---
## Тест случаи според критериумот Multiple Condition за двете функции

### Функцијата - `borrowBookEveryBranchTest`

### Тест 1

```java
/**  
 * Case 1: borrowing a book where both title and author are empty
 */
assertThrows(IllegalArgumentException.class, () -> library.borrowBook("",""));
``` 
Со овај тест внесуваме празен стринг и за насловот и за авторот што не доведува да имаме состојба - **false | false** кај гранката.

---
### Тест 2
```java
/**  
 * Case 2: borrowing a book where title is present and author empty
 */
 assertThrows(IllegalArgumentException.class, () -> library.borrowBook("Clean Code",""));
```
Со овај тест внесуваме валиден стринг за насловот а за авторот внесуваме празен стринг што не доведува да имаме состојба - **true | false** кај гранката.

---

```java
/**  
 * Case 3: borrowing a book where title is empty and author present
 */
 assertThrows(IllegalArgumentException.class, () -> library.borrowBook("","Robert C. Martin"));
```
Со овај тест внесуваме празен стринг за насловот а за авторот внесуваме валиден стринг што не доведува да имаме состојба - **false | true** кај гранката.

---
### Тест 4
```java
/**  
 * Case 4: borrowing a book where both title and author are present
 */
 assertDoesNotThrow(() ->  
        library.borrowBook("Clean Code","Robert C. Martin"));
```
Со овај тест внесуваме валиден стринг за насловот и за авторот што не доведува да имаме состојба - **true | true** кај гранката.
<br>

**Заклучок:** потребно е минимум 4 тестови за да се исполни критериумот Multiple Condition за функцијата `borrowBook`

---

### Функцијата - `searchBookMultipleConditionTest`

### Тест 1

```java
/**  
 * Case 1: Searching for a book that doesn't exist and is not borrowed * -> expected null
 */
assertNull(library.searchBookByTitle("xyz"));
```
Со овај тест внесуваме наслов за книга што не постои и не доведува да имаме состојба - **false | false**

---

### Тест 2

```java
/**  
 * Case 2: Searching for a book that exists and isn't borrowed * -> expected the program to run normally
 */
assertNotNull(library.searchBookByTitle("Clean Code"));
```
Со овај тест внесуваме валиден наслов за книга што не е позајмена и не доведува да имаме состојба - **true | true**

---
### Тест 3

```java
library.borrowBook("Clean Code","Robert C. Martin");  
  
/**  
 * Case 3: Searching for a book that exists but is borrowed -> expected null 
 */
assertNull(library.searchBookByTitle("Clean Code"));
```

Со овај тест внесуваме валиден наслов за книга што е позајмена и не доведува да имаме состојба - **true | false**

---

### Тест 4

```java
/**  
 * Case 4: Searching for a book that doesn't exist and is borrowed * -> expected null
 */
 assertNull(library.searchBookByTitle("xyz"));
```
Со овај тест внесуваме наслов за книга што не постои е позајмена и не доведува да имаме состојба - **false | false**

---
**Заклучок:** потребно е минимум 4 тестови за да се исполни критериумот Multiple Condition за функцијата `searchBookByTitle`
