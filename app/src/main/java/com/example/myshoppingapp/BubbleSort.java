package com.example.myshoppingapp;
import java.util.ArrayList;

public class BubbleSort {

    public ArrayList RunSort(int listLength, ArrayList<ShoppingItem> SortArrayList) {
        int counter;
        boolean swapped = true;
//
        while (swapped) {
            swapped = false;
            for (counter = 0; counter < listLength - 1; counter++) {
                if (SortArrayList.get(counter).getPriority() > SortArrayList.get(counter + 1).getPriority()
                        || (SortArrayList.get(counter).getPriority() == SortArrayList.get(counter + 1).getPriority()
                        && SortArrayList.get(counter).getTotalValue() > SortArrayList.get(counter + 1).getTotalValue())
                ) {
                    ShoppingItem Placeholder = new ShoppingItem();
                    Placeholder.nameCopy(SortArrayList.get(counter));
                    Placeholder.priorityCopy(SortArrayList.get(counter));
                    Placeholder.priceCopy(SortArrayList.get(counter));
                    Placeholder.quantityCopy(SortArrayList.get(counter));
                    Placeholder.totalValueCopy(SortArrayList.get(counter));

                    SortArrayList.set(counter, SortArrayList.get(counter + 1));
                    SortArrayList.set(counter + 1, Placeholder);

                    swapped = true;
                }
            }
        }
        return SortArrayList;
    }

}