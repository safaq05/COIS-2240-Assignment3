import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class Transaction {
    private static Transaction instance;

    private Transaction() {
        // Private constructor to prevent instantiation
    }

    public static Transaction getTransaction() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book);
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            saveTransaction(transactionDetails);
            System.out.println(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            saveTransaction(transactionDetails);
            System.out.println(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Save transaction details to a file
    public void saveTransaction(String transactionDetails) {
        try (FileWriter writer = new FileWriter("transactions.txt", true)) {
            writer.write(transactionDetails + "\n");
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Display transaction history
    public void displayTransactionHistory() {
        File file = new File("transactions.txt");
        if (!file.exists()) {
            System.out.println("No transactions found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n--- Transaction History ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading transaction history: " + e.getMessage());
        }
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}

