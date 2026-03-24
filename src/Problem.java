import java.util.*;

public class Problem {
    public static int linearFirst(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear first " + target + ": index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear first " + target + ": not found (" + comparisons + " comparisons)");
        return -1;
    }

    public static int linearLast(String[] logs, String target) {
        int comparisons = 0;
        for (int i = logs.length - 1; i >= 0; i--) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear last " + target + ": index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear last " + target + ": not found (" + comparisons + " comparisons)");
        return -1;
    }

    public static int[] binarySearch(String[] logs, String target) {
        int comparisons = 0;
        int low = 0, high = logs.length - 1;
        int index = -1;
        Arrays.sort(logs);
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (logs[mid].equals(target)) {
                index = mid;
                break;
            } else if (logs[mid].compareTo(target) < 0) low = mid + 1;
            else high = mid - 1;
        }
        int count = 0;
        for (String s : logs) if (s.equals(target)) count++;
        System.out.println("Binary " + target + ": index " + index + " (" + comparisons + " comparisons), count=" + count);
        return new int[]{index, count};
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};

        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        binarySearch(logs, "accB");
    }
}