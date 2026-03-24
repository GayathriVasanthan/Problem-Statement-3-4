import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;
    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class Problem {
    public static void bubbleSortFee(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    Collections.swap(transactions, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void insertionSortFeeTimestamp(List<Transaction> transactions) {
        for (int i = 1; i < transactions.size(); i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;
            while (j >= 0 && (transactions.get(j).fee > key.fee ||
                    (transactions.get(j).fee == key.fee && transactions.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                transactions.set(j + 1, transactions.get(j));
                j--;
            }
            transactions.set(j + 1, key);
        }
    }

    public static List<Transaction> flagHighFee(List<Transaction> transactions, double threshold) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.fee > threshold) outliers.add(t);
        }
        return outliers;
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        bubbleSortFee(transactions);
        System.out.println("BubbleSort (fees): " + transactions);

        insertionSortFeeTimestamp(transactions);
        System.out.println("InsertionSort (fee+ts): " + transactions);

        List<Transaction> outliers = flagHighFee(transactions, 50.0);
        System.out.println("High-fee outliers: " + outliers);
    }
}