import java.util.*;

public class Problem {
    public static void linearSearch(int[] risks, int target) {
        int comparisons = 0;
        boolean found = false;
        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                found = true;
                System.out.println("Linear: threshold=" + target + " → found at index " + i + " (" + comparisons + " comps)");
                break;
            }
        }
        if (!found) System.out.println("Linear: threshold=" + target + " → not found (" + comparisons + " comps)");
    }

    public static void binaryFloorCeiling(int[] risks, int target) {
        int low = 0, high = risks.length - 1, comparisons = 0;
        int floor = -1, ceiling = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (risks[mid] == target) {
                floor = ceiling = risks[mid];
                break;
            } else if (risks[mid] < target) {
                floor = risks[mid];
                low = mid + 1;
            } else {
                ceiling = risks[mid];
                high = mid - 1;
            }
        }
        System.out.println("Binary floor(" + target + "): " + floor + ", ceiling: " + ceiling + " (" + comparisons + " comps)");
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};
        int target = 30;

        linearSearch(risks, target);
        binaryFloorCeiling(risks, target);
    }
}