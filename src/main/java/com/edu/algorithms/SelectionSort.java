package com.edu.algorithms;

import com.edu.Utility;
import javafx.scene.shape.Rectangle;

public class SelectionSort implements SortAlgorithm {

    private int currentIndex = 0;
    private int minIndex = 0;
    private int searchIndex = 0;
    private int operationCount = 0;
    private boolean isFindingMin = true;

    @Override
    public boolean sortStep(Rectangle[] bars, boolean stepByStep) {
        if (currentIndex >= bars.length - 1) {
            return true;
        }

        if (isFindingMin) {
            if (searchIndex < bars.length) {
                if (bars[searchIndex].getHeight() < bars[minIndex].getHeight()) {
                    Utility.highlightBars(bars, minIndex, searchIndex);
                    minIndex = searchIndex;
                }
                searchIndex++;
                operationCount++;

                if (stepByStep) {
                    return false;
                }
            } else {
                isFindingMin = false;
            }
        } else {
            Utility.highlightBars(bars, currentIndex, minIndex);
            swap(bars, currentIndex, minIndex);

            currentIndex++;
            minIndex = currentIndex;
            searchIndex = currentIndex + 1;
            isFindingMin = true;
            operationCount++;

            if (stepByStep) {
                return false;
            }
        }

        return false;
    }

    private void swap(Rectangle[] bars, int i, int j) {
        double tempHeight = bars[i].getHeight();
        bars[i].setHeight(bars[j].getHeight());
        bars[j].setHeight(tempHeight);
    }

    @Override
    public void reset() {
        currentIndex = 0;
        minIndex = 0;
        searchIndex = 0;
        isFindingMin = true;
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
        return "Selection Sort";
    }

    @Override
    public String toString() {
        return getName();
    }
}