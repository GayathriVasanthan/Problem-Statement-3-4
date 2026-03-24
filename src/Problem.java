import java.util.*;

class Asset {
    String id;
    double returnRate;
    double volatility;
    public Asset(String id, double returnRate, double volatility) {
        this.id = id;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }
    public String toString() {
        return id + ":" + returnRate + "%";
    }
}

public class Problem {
    public static void mergeSort(Asset[] assets) {
        if (assets.length <= 1) return;
        int mid = assets.length / 2;
        Asset[] left = Arrays.copyOfRange(assets, 0, mid);
        Asset[] right = Arrays.copyOfRange(assets, mid, assets.length);
        mergeSort(left);
        mergeSort(right);
        merge(assets, left, right);
    }

    public static void merge(Asset[] assets, Asset[] left, Asset[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].returnRate <= right[j].returnRate) assets[k++] = left[i++];
            else assets[k++] = right[j++];
        }
        while (i < left.length) assets[k++] = left[i++];
        while (j < right.length) assets[k++] = right[j++];
    }

    public static void quickSortDescReturnAscVol(Asset[] assets, int low, int high) {
        if (low < high) {
            int pi = partition(assets, low, high);
            quickSortDescReturnAscVol(assets, low, pi - 1);
            quickSortDescReturnAscVol(assets, pi + 1, high);
        }
    }

    public static int partition(Asset[] assets, int low, int high) {
        Asset pivot = assets[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (assets[j].returnRate > pivot.returnRate ||
                    (assets[j].returnRate == pivot.returnRate && assets[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = assets[i];
                assets[i] = assets[j];
                assets[j] = temp;
            }
        }
        Asset temp = assets[i + 1];
        assets[i + 1] = assets[high];
        assets[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12, 0.3),
                new Asset("TSLA", 8, 0.5),
                new Asset("GOOG", 15, 0.2)
        };

        mergeSort(assets);
        System.out.println("MergeSort (asc returnRate): " + Arrays.toString(assets));

        Asset[] assetsQuick = {
                new Asset("AAPL", 12, 0.3),
                new Asset("TSLA", 8, 0.5),
                new Asset("GOOG", 15, 0.2)
        };
        quickSortDescReturnAscVol(assetsQuick, 0, assetsQuick.length - 1);
        System.out.println("QuickSort (desc return + asc volatility): " + Arrays.toString(assetsQuick));
    }
}