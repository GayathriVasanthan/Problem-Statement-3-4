import java.util.*;

class Trade {
    String id;
    int volume;
    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }
    public String toString() {
        return id + ":" + volume;
    }
}

public class Problem {
    public static void mergeSort(Trade[] trades) {
        if (trades.length <= 1) return;
        int mid = trades.length / 2;
        Trade[] left = Arrays.copyOfRange(trades, 0, mid);
        Trade[] right = Arrays.copyOfRange(trades, mid, trades.length);
        mergeSort(left);
        mergeSort(right);
        merge(trades, left, right);
    }

    public static void merge(Trade[] trades, Trade[] left, Trade[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].volume <= right[j].volume) {
                trades[k++] = left[i++];
            } else {
                trades[k++] = right[j++];
            }
        }
        while (i < left.length) trades[k++] = left[i++];
        while (j < right.length) trades[k++] = right[j++];
    }

    public static void quickSortDesc(Trade[] trades, int low, int high) {
        if (low < high) {
            int pi = partition(trades, low, high);
            quickSortDesc(trades, low, pi - 1);
            quickSortDesc(trades, pi + 1, high);
        }
    }

    public static int partition(Trade[] trades, int low, int high) {
        Trade pivot = trades[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (trades[j].volume > pivot.volume) {
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    public static Trade[] mergeTwoLists(Trade[] a, Trade[] b) {
        Trade[] merged = new Trade[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) merged[k++] = a[i++];
            else merged[k++] = b[j++];
        }
        while (i < a.length) merged[k++] = a[i++];
        while (j < b.length) merged[k++] = b[j++];
        return merged;
    }

    public static void main(String[] args) {
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        mergeSort(trades);
        System.out.println("MergeSort (asc): " + Arrays.toString(trades));

        Trade[] tradesDesc = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };
        quickSortDesc(tradesDesc, 0, tradesDesc.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(tradesDesc));

        Trade[] morning = {new Trade("t1", 200), new Trade("t2", 400)};
        Trade[] afternoon = {new Trade("t3", 100), new Trade("t4", 200)};
        Trade[] merged = mergeTwoLists(morning, afternoon);
        int total = 0;
        for (Trade t : merged) total += t.volume;
        System.out.println("Merged morning+afternoon total: " + total);
    }
}