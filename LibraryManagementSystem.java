import java.util.*;

class Book {
    int id;
    String title;
    boolean isIssued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public void displayBook() {
        System.out.println("Book ID: " + id + ", Title: " + title + ", Issued: " + isIssued);
    }
}

class User {
    int userId;
    String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public void displayUser() {
        System.out.println("User ID: " + userId + ", Name: " + name);
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    HashMap<Integer, Integer> issuedBooks = new HashMap<>(); // bookId -> userId

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void showAllBooks() {
        for (Book book : books) {
            book.displayBook();
        }
    }

    public void showAllUsers() {
        for (User user : users) {
            user.displayUser();
        }
    }

    public User getUserById(int userId) {
        for (User user : users) {
            if (user.userId == userId) {
                return user;
            }
        }
        return null;
    }

    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.id == bookId) {
                return book;
            }
        }
        return null;
    }

    public void issueBook(int bookId, int userId) {
        Book book = getBookById(bookId);
        User user = getUserById(userId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (!book.isIssued) {
            book.isIssued = true;
            issuedBooks.put(bookId, userId);
            System.out.println("Book '" + book.title + "' issued to " + user.name);
        } else {
            System.out.println("Book already issued.");
        }
    }

    public void returnBook(int bookId) {
        Book book = getBookById(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.isIssued) {
            book.isIssued = false;
            issuedBooks.remove(bookId);
            System.out.println("Book '" + book.title + "' returned successfully.");
        } else {
            System.out.println("Book was not issued.");
        }
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.title.toLowerCase().contains(keyword.toLowerCase()) ||
                Integer.toString(book.id).equals(keyword)) {
                book.displayBook();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with keyword: " + keyword);
        }
    }

    public void showIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("No books are currently issued.");
            return;
        }

        System.out.println("--- Issued Book List ---");
        for (Map.Entry<Integer, Integer> entry : issuedBooks.entrySet()) {
            int bookId = entry.getKey();
            int userId = entry.getValue();
            Book book = getBookById(bookId);
            User user = getUserById(userId);
            if (book != null && user != null) {
                System.out.println("Book: '" + book.title + "' issued to " + user.name + " (User ID: " + user.userId + ")");
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        // Initial data
        lib.addBook(new Book(1, "Java Programming"));
        lib.addBook(new Book(2, "Python Basics"));
        lib.addBook(new Book(3, "C++ Guide"));

        lib.addUser(new User(101, "Alice"));
        lib.addUser(new User(102, "Bob"));

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Show All Books");
            System.out.println("2. Show All Users");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Show Issued Books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    lib.showAllBooks();
                    break;
                case 2:
                    lib.showAllUsers();
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter Your User ID: ");
                    int userId = sc.nextInt();
                    lib.issueBook(bookId, userId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    lib.returnBook(returnId);
                    break;
                case 5:
                    System.out.print("Enter Book Title or ID to search: ");
                    String keyword = sc.nextLine();
                    lib.searchBook(keyword);
                    break;
                case 6:
                    lib.showIssuedBooks();
                    break;
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
