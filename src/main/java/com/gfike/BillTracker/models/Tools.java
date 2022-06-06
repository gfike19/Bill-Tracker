package com.gfike.BillTracker.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Tools {

    public static List<Bill> sortBillsByDateAsc (List<Bill> bills, int month, int year) {
        int length = bills.size();
        boolean isSorted = false;

        while (isSorted == false) {
            int numberofSwaps = 0;
            for(int i = 0; i < length -1; i++) {
                LocalDate date1 = LocalDate.of(year, month, bills.get(i).getDueDate());
                LocalDate date2 = LocalDate.of(year, month, bills.get(i + 1).getDueDate());

                if(date1.isAfter(date2)) {
                    Bill temp = bills.get(i + 1);
                    bills.remove(temp);
                    bills.add(i + 1, bills.get(i));
                    bills.remove(bills.get(i));
                    bills.add(i, temp);
                    numberofSwaps ++;
                }
            }
            if (numberofSwaps == 0) {
                isSorted = true;
            } else {
                isSorted = false;
            }
        }
        return bills;
    }

    public static List<Bill> setToList(Set<Bill> bills) {
        List<Bill> billList = new ArrayList<>();

        for(Bill b : bills) {
            billList.add(b);
        }
        return billList;
    }

    public static Set<Bill> listToSet(List<Bill> bills) {
        Set<Bill> billSet = new HashSet<>();

        for(Bill b : bills) {
            billSet.add(b);
        }

        return billSet;
    }

}
