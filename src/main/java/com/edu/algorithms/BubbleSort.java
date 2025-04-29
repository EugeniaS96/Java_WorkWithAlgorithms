package com.edu.algorithms;

import com.edu.Utility;
import javafx.scene.shape.Rectangle;

public class BubbleSort implements SortAlgorithm {

    private int operationCount = 0;
    private int pass = 0;
    private int index = 0;
    private boolean swapped = false;

    @Override
    public boolean sortStep(Rectangle[] bars, boolean stepByStep) {
        int n = bars.length;
        if (pass >= n - 1) {
            return true;
        }
        if (index < n - 1 - pass) {
            Utility.highlightBars(bars, index, index + 1);
            operationCount++;
            if (bars[index].getHeight() > bars[index + 1].getHeight()) {
                double tmp = bars[index].getHeight();
                bars[index].setHeight(bars[index + 1].getHeight());
                bars[index + 1].setHeight(tmp);
                swapped = true;
            }
            index++;
            return false;
        }

        if (!swapped) {
            return true;
        }
        pass++;
        index = 0;
        swapped = false;
        return false;
    }

    @Override
    public void reset() {
        operationCount = 0;
        pass = 0;
        index = 0;
        swapped = false;
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
        return "Bubble Sort";
    }

    @Override
    public String toString() {
        return getName();
    }
}
