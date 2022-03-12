package phonebook.sort;

import phonebook.domain.PhoneBookEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class QuickSortInteger {

    public Optional<List<Integer>> sort(List<Integer> unsortedList) throws IOException {

        if (unsortedList.size() <= 1) {
            return Optional.of(unsortedList);
        }

        int leftBoundary = 0;
        int rightBoundary = unsortedList.size() - 1;
        int pivot = partition(unsortedList, leftBoundary, rightBoundary); // partition the whole list at first

        List<Integer> leftSubList = unsortedList.subList(leftBoundary, pivot);
        List<Integer> rightSubList = unsortedList.subList(pivot + 1, unsortedList.size());


        sort(leftSubList);
        sort(rightSubList);

        return Optional.of(unsortedList);
    }


    private int partition(List<Integer> listToPartition, int leftBoundary, int rightBoundary) {

        System.out.println("Before partition:");
        System.out.println(Arrays.toString(listToPartition.toArray()));


        // temporary pivot position(a.k.a index of last element found to be smaller than the pivot value), starts
        // with -1.
        int postPartitionPivotPosition = -1;
        int pivotPosition = listToPartition.size() - 1;
        int searchRightBoundary = rightBoundary - 1 ; // since the pivot is the last element


        for (int i = leftBoundary; i < rightBoundary; i++) {
            if (listToPartition.get(i) <= listToPartition.get(pivotPosition)) {
                postPartitionPivotPosition++;
                Integer tempEntry = listToPartition.get(postPartitionPivotPosition);
                listToPartition.set(postPartitionPivotPosition, listToPartition.get(i));
                listToPartition.set(i, tempEntry);
            }
        }

        postPartitionPivotPosition++;
        Integer tempEntry = listToPartition.get(postPartitionPivotPosition);
        listToPartition.set(postPartitionPivotPosition, listToPartition.get(pivotPosition));
        listToPartition.set(pivotPosition, tempEntry);

        System.out.println("After partition:");
        System.out.println(Arrays.toString(listToPartition.toArray()));


        return postPartitionPivotPosition;


    }
}


