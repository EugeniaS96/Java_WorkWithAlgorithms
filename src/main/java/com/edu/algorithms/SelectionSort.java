package com.edu.algorithms;

import com.edu.Utility;
import javafx.scene.shape.Rectangle;

public class SelectionSort implements SortAlgorithm {

    private int i = 0, j = 1, minIndex = 0;
    private int operationCount = 0;

    @Override
    public boolean sortStep(Rectangle[] bars, boolean stepByStep) {
        if (i >= bars.length - 1) {
            return true;
        }

        if (j < bars.length) {
            Utility.highlightBars(bars, minIndex, j);
            if (bars[j].getHeight() < bars[minIndex].getHeight()) {
                minIndex = j;
            }
            j++;
            operationCount++;
            if (stepByStep) return false;
        } else {
            if (i != minIndex) {
                swap(bars, i, minIndex);
            }
            i++;
            minIndex = i;
            j = i + 1;
        }
        return false;
    }

    private void swap(Rectangle[] bars, int a, int b) {
        double temp = bars[a].getHeight();
        bars[a].setHeight(bars[b].getHeight());
        bars[b].setHeight(temp);
    }

    @Override
    public void reset() {
        i = 0;
        j = 1;
        minIndex = 0;
        operationCount = 0;
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
        return "Selection Sort";
    }

    @Override
    public String toString() {
        return getName();
    }
}
