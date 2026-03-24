import java.util.*;

class Client {
    String id;
    int riskScore;
    double accountBalance;
    public Client(String id, int riskScore, double accountBalance) {
        this.id = id;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }
    public String toString() {
        return id + ":" + riskScore;
    }
}

public class Problem {
    public static void bubbleSortRiskScoreAsc(Client[] clients) {
        int n = clients.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void insertionSortRiskScoreDescAccountBalanceAsc(Client[] clients) {
        for (int i = 1; i < clients.length; i++) {
            Client key = clients[i];
            int j = i - 1;
            while (j >= 0 && (clients[j].riskScore < key.riskScore ||
                    (clients[j].riskScore == key.riskScore && clients[j].accountBalance > key.accountBalance))) {
                clients[j + 1] = clients[j];
                j--;
            }
            clients[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 10000),
                new Client("clientB", 50, 7000)
        };

        bubbleSortRiskScoreAsc(clients);
        System.out.println("BubbleSort (asc): " + Arrays.toString(clients));

        insertionSortRiskScoreDescAccountBalanceAsc(clients);
        System.out.println("InsertionSort (desc risk + asc balance): " + Arrays.toString(clients));

        System.out.print("Top 3 risks: ");
        for (int i = 0; i < Math.min(3, clients.length); i++) {
            System.out.print(clients[i].id + "(" + clients[i].riskScore + ")");
            if (i < Math.min(3, clients.length) - 1) System.out.print(", ");
        }
        System.out.println();
    }
}