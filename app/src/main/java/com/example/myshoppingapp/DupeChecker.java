package com.example.myshoppingapp;

import android.content.Context;


import java.util.ArrayList;

public class DupeChecker {

    public int ListNameDupeCheck(ArrayList<ShoppingItem> ShoppingList, ShoppingItem newItem) {
        int ArrayLength = ShoppingList.size();
        int nameAcceptable;

        switch(ArrayLength) {
        case 0:
            nameAcceptable = 1;
            break;

        default:
                    int IterationNumber = 0;
                    int nameDupeCount = 0;

                    while ((IterationNumber < ArrayLength)) {
                        nameDupeCount =
                                nameDupeCount + this.NameDupeChecker(newItem
                                        , ShoppingList.get(ArrayLength - IterationNumber-1));

                        IterationNumber++;
                    }

                    if (nameDupeCount == 0) {
                        nameAcceptable = 1;
                        }
                    else {
                        nameAcceptable = 0;
                        }

                break;
        }
    return nameAcceptable;
    }



        public int NameDupeChecker(ShoppingItem currentItem, ShoppingItem checkItem)
        {int dupeCount = 0;
            if(currentItem.nameEquals(checkItem)) {
                dupeCount = dupeCount+1;
            }
            return dupeCount;
        }
    }



