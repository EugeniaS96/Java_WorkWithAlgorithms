package com.edu.algorithms;

import com.edu.Utility;
import javafx.scene.shape.Rectangle;

public class MergeSort implements SortAlgorithm {
    private int operationCount = 0;
    private int[] tempArray;
    private int currentSize;
    private int leftStart;
    private boolean initialized = false;

    @Override
    public boolean sortStep(Rectangle[] bars, boolean stepByStep) {
        if (!initialized) {
            tempArray = new int[bars.length];
            currentSize = 1;
            leftStart = 0;
            initialized = true;
        }

        if (currentSize <= bars.length - 1) {
            if (leftStart < bars.length - 1) {
                int mid = Math.min(leftStart + currentSize - 1, bars.length - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, bars.length - 1);

                merge(bars, leftStart, mid, rightEnd);

                leftStart += 2 * currentSize;

                if (stepByStep) {
                    return false;
                }
            } else {
                currentSize *= 2;
                leftStart = 0;
            }
            return false;
        }
        return true;
    }

    private void merge(Rectangle[] bars, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        for (int l = left; l <= right; l++) {
            tempArray[l] = (int) bars[l].getHeight();
        }

        while (i <= mid && j <= right) {
            operationCount++;
            if (tempArray[i] <= tempArray[j]) {
                bars[k].setHeight(tempArray[i]);
                Utility.highlightBars(bars, k, i);
                i++;
            } else {
                bars[k].setHeight(tempArray[j]);
                Utility.highlightBars(bars, k, j);
                j++;
            }
            k++;
        }

        while (i <= mid) {
            bars[k].setHeight(tempArray[i]);
            Utility.highlightBars(bars, k, i);
            k++;
            i++;
        }
    }

    @Override
    public void reset() {
        initialized = false;
        resetOperationCount();
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