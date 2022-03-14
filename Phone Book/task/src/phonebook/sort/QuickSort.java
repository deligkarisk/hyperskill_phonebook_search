package phonebook.sort;

import phonebook.domain.PhoneBookEntry;

import java.io.IOException;
import java.util.*;

public class QuickSort {

    public void sortPhoneBook(List<PhoneBookEntry> toSortPhoneBook) {


        if (toSortPhoneBook.size() <= 1) {
            return;
        }

        int leftBoundary = 0;
        int rightBoundary = toSortPhoneBook.size() - 1;
        int pivot = partition(toSortPhoneBook, leftBoundary, rightBoundary); // partition the whole list at first

        List<PhoneBookEntry> leftSubList = toSortPhoneBook.subList(leftBoundary, pivot);
        List<PhoneBookEntry> rightSubList = toSortPhoneBook.subList(pivot + 1, toSortPhoneBook.size());

        sortPhoneBook(leftSubList);
        sortPhoneBook(rightSubList);
    }

    private int partition(List<PhoneBookEntry> listToPartition, int leftBoundary, int rightBoundary) {

      //  System.out.println("Before partition:");
       // System.out.println(Arrays.toString(listToPartition.toArray()));


        // temporary pivot position(a.k.a index of last element found to be smaller than the pivot value), starts
        // with -1.
        int postPartitionPivotPosition = -1;
        int pivotPosition = listToPartition.size() - 1;
        int searchRightBoundary = rightBoundary - 1 ; // since the pivot is the last element


        for (int i = leftBoundary; i < rightBoundary; i++) {
            if (listToPartition.get(i).compareTo(listToPartition.get(pivotPosition)) <= 0) {
                postPartitionPivotPosition++;
                PhoneBookEntry tempEntry = listToPartition.get(postPartitionPivotPosition);
                listToPartition.set(postPartitionPivotPosition, listToPartition.get(i));
                listToPartition.set(i, tempEntry);
            }
        }

        postPartitionPivotPosition++;
        PhoneBookEntry tempEntry = listToPartition.get(postPartitionPivotPosition);
        listToPartition.set(postPartitionPivotPosition, listToPartition.get(pivotPosition));
        listToPartition.set(pivotPosition, tempEntry);

    //    System.out.println("After partition:");
    //    System.out.println(Arrays.toString(listToPartition.toArray()));


        return postPartitionPivotPosition;

    }

}
