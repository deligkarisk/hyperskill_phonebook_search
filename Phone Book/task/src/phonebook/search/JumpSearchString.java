package phonebook.search;

import phonebook.domain.PhoneBookEntry;

import java.util.List;

public class JumpSearchString implements Search {

    public int search(List<PhoneBookEntry> sortedPhonebook, String entryToFind) {
        int numberOfComparisons = 0;


        // Search in descending array
        int blockSize = (int) Math.floor(Math.sqrt(sortedPhonebook.size()));

        System.out.println("Checking if the number is larger than the largest number of the array.");

        if (sortedPhonebook.get(0).getName().compareTo(entryToFind) < 0)  {
            return -1;
        }

        System.out.println("Checking if the number is in the first element of the array.");
        if (sortedPhonebook.get(0).getName().compareTo(entryToFind) == 0) {
            return 0;
        }

        int currentBlock = 0;
        while (blockSize * (currentBlock + 1) < sortedPhonebook.size()) {
            currentBlock++;
            int rightIndex = currentBlock*blockSize + 1;
            int leftIndex = rightIndex - blockSize;
            System.out.println("Moving to the next block with right index: " + rightIndex + " and left index: " + leftIndex);

            // if the smallest number, i.e. the right edge number of the current block is larger than the number we
            // want to find, then we need to move to the next block.
            if (sortedPhonebook.get(rightIndex).getName().compareTo(entryToFind) > 0) {
                numberOfComparisons++;
                continue;
            }


            // if the right index is smaller than the number we are looking for, then the number may be in this block.
            if (sortedPhonebook.get(rightIndex).getName().compareTo(entryToFind) <= 0) {
                System.out.println("The number we are looking for may be in this block.");
                for (int k = rightIndex; k > rightIndex - blockSize; k--) {
                    System.out.println("Comparing with element of index:" + k);
                    if (sortedPhonebook.get(k).getName().compareTo(entryToFind) == 0) {
                        return k;
                    }
                    numberOfComparisons++;

                    if ( k - 1 == rightIndex - blockSize) {
                        System.out.println("This was the last iteration for this block.");
                    }
                }
            }
        }

        // Try the remaining elements, if there are any left.
        for (int lastBlockIndex = sortedPhonebook.size() - 1; lastBlockIndex > currentBlock*blockSize + 1; lastBlockIndex--) {
            if (sortedPhonebook.get(lastBlockIndex).getName().compareTo(entryToFind) == 0) {
                return lastBlockIndex;
            }
            numberOfComparisons++;
        }

        System.out.println("entry not found");
        return -1;
    }
}
