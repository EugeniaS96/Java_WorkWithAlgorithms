package com.edu.algorithms;

import com.edu.Utility;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements SortAlgorithm {

    private class Task {
        int left, mid, right;
        int i, j, k;
        int stage;
        double[] aux;
        Task(int l, int m, int r, double[] sharedAux) {
            left = l; mid = m; right = r;
            i = l; j = m + 1; k = l;
            stage = 0;
            aux = sharedAux;
        }
    }

    private List<Task> tasks;
    private int currentTask;
    private double[] aux;
    private int operationCount = 0;
    private boolean initialized = false;

    @Override
    public boolean sortStep(Rectangle[] bars, boolean stepByStep) {
        int n = bars.length;
        if (!initialized) {
            aux = new double[n];
            tasks = new ArrayList<>();
            for (int width = 1; width < n; width *= 2) {
                for (int left = 0; left < n - 1; left += 2 * width) {
                    int mid = Math.min(left + width - 1, n - 1);
                    int right = Math.min(left + 2 * width - 1, n - 1);
                    tasks.add(new Task(left, mid, right, aux));
                }
            }
            currentTask = 0;
            initialized = true;
        }
        if (currentTask >= tasks.size()) {
            return true;
        }

        Task t = tasks.get(currentTask);

        if (t.stage == 0) {
            for (int x = t.left; x <= t.right; x++) {
                t.aux[x] = bars[x].getHeight();
            }
            t.stage = 1;
        }

        if (t.stage == 1) {
            if (t.i <= t.mid && t.j <= t.right) {
                Utility.highlightBars(bars, t.i, t.j);
                operationCount++;
                if (t.aux[t.i] <= t.aux[t.j]) {
                    bars[t.k++] .setHeight(t.aux[t.i++]);
                } else {
                    bars[t.k++] .setHeight(t.aux[t.j++]);
                }
                if (stepByStep) return false;
                return false;
            }
            t.stage = 2;
        }

        if (t.stage == 2) {
            if (t.i <= t.mid) {
                Utility.highlightBars(bars, t.i, t.i);
                operationCount++;
                bars[t.k++].setHeight(t.aux[t.i++]);
                if (stepByStep) return false;
                return false;
            }
            t.stage = 3;
        }

        if (t.stage == 3) {
            if (t.j <= t.right) {
                Utility.highlightBars(bars, t.j, t.j);
                operationCount++;
                bars[t.k++].setHeight(t.aux[t.j++]);
                if (stepByStep) return false;
                return false;
            }

            currentTask++;
        }

        return false;
    }

    @Override
    public void reset() {
        operationCount = 0;
        initialized = false;
        tasks = null;
    }

    @Override
    public int getOperationCount() {
        return operationCount;
    }

    @Override
    public void resetOperationCount() {
        operationCount = 0;
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public String toString() {
        return getName();
    }
}
