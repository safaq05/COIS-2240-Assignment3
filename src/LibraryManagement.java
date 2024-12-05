import java.util.Scanner;

public class LibraryManagement {
    private Library library = new Library(); // Library instance to manage members and books

    public static void main(String[] args) {
        new LibraryManagement().run(); // Calls the run method to start the program
       

    }

    private void run() {
        Scanner scanner = new Scanner(System.in); // Scanner object to take user input
        boolean running = true; // Keeps the system running in a loop
        //added this
        private Transaction transaction = Transaction.getTransaction();//insatnce


        while (running) { // Main menu loop
            System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt(); // Read the user's choice
            scanner.nextLine(); // Consume newline character

            switch (choice) { // Handle different user choices
            case 1:
            	//adding anew member
                System.out.print("Enter member ID: ");
                //makse sure its all numbers
                int memberId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                //adding a new meber
                System.out.print("Enter member name: ");
                String memberName = scanner.nextLine();
                Member newMember = new Member(memberId, memberName);
                if (library.addMember(newMember)) {
                    System.out.println("Member added successfully.");
                }
                break;

                //adding a new book
            case 2:
            	
                System.out.print("Enter book ID: ");
                int bookId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter book title: ");
                String bookTitle = scanner.nextLine();
                Book newBook = new Book(bookId, bookTitle);
                if (library.addBook(newBook)) {
                    System.out.println("Book added successfully.");
                }
                break;

                case 3: // Borrow a book
                    System.out.println("\n--- Available Members ---");
                    for (Member member : library.getMembers()) { // Display all members
                        System.out.println(member.getId() + ". " + member.getName());
                    }
                    
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    
                    System.out.println("\n--- Available Books ---");
                    for (Book book : library.getBooks()) { // Display all available books
                        if (book.isAvailable())
                            System.out.println(book.getId() + ". " + book.getTitle());
                    }
                    
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    
                    scanner.nextLine(); // Consume newline

                    Member member = library.findMemberById(memberId); // Find member by ID
                    Book book = library.findBookById(bookId); // Find book by ID

                    if (member != null && book != null) { // If both member and book exist
                        Transaction.borrowBook(book, member); // Process borrowing transaction
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;
                case 4: // Return a borrowed book
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    
                    System.out.print("Enter book ID: ");
                    bookId = scanner.nextInt();
                    
                    scanner.nextLine(); // Consume newline

                    member = library.findMemberById(memberId); // Find member by ID
                    book = library.findBookById(bookId); // Find book by ID

                    if (member != null && book != null) { // If both member and book exist
                        Transaction.returnBook(book, member); // Process return transaction
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;
                case 5: // View borrowed books by a member
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Member specificMember = library.findMemberById(memberId); // Find member by ID
                    
                    if (specificMember != null) { // If member exists
                        System.out.println("Books borrowed by " + specificMember.getName() + ":");
                        for (Book bk : specificMember.getBorrowedBooks()) { // List borrowed books
                            System.out.println(" - " + bk.getTitle());
                        }
                    } else {
                        System.out.println("Invalid member ID.");
                    }
                    break;
                    //fix this
                case 6:
                    transaction.displayTransactionHistory();
                    break;

                case 7: // Exit the system
                    System.out.println("Exiting. Good Bye..");
                    running = false; // Stop the loop
                    break;
                default: //Invalid choice
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
