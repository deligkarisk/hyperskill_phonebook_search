package phonebook.search;

public class JumpSearchNumeric {

    public int search(int[] sortedArray, int numberToFind, boolean arrayIsAscending) {
        int numberOfComparisons = 0;

        if (arrayIsAscending) {
            throw new RuntimeException("Only search in descending arrays has been implemented here.");
        }

        // Search in descending array
        int blockSize = (int) Math.floor(Math.sqrt(sortedArray.length));

        System.out.println("Looking for number: " + numberToFind);

        System.out.println("Checking if the number is larger than the largest number of the array.");
        if (sortedArray[0] < numberToFind) {
            return -1;
        }

        System.out.println("Checking if the number is in the first element of the array.");
        if (sortedArray[0] == numberToFind) {
            return 0;
        }

        int currentBlock = 0;
        while (blockSize * (currentBlock + 1) < sortedArray.length) {
            currentBlock++;
            int rightIndex = currentBlock*blockSize + 1;
            int leftIndex = rightIndex - blockSize;
            System.out.println("Moving to the next block with right index: " + rightIndex + " and left index: " + leftIndex);

            // if the smallest number, i.e. the right edge number of the current block is larger than the number we
            // want to find, then we need to move to the next block.
            if (sortedArray[rightIndex] > numberToFind) {
                numberOfComparisons++;
                continue;
            }


            // if the right index is smaller than the number we are looking for, then the number may be in this block.
            if (sortedArray[rightIndex] <= numberToFind) {
                System.out.println("The number we are looking for may be in this block.");
                for (int k = rightIndex; k > rightIndex - blockSize; k--) {
                    System.out.println("Comparing with element of index:" + k);
                    if (sortedArray[k] == numberToFind) {
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
        for (int lastBlockIndex = sortedArray.length - 1; lastBlockIndex > currentBlock*blockSize + 1; lastBlockIndex--) {
            if (sortedArray[lastBlockIndex] == numberToFind) {
                return lastBlockIndex;
            }
            numberOfComparisons++;
        }

        System.out.println("Number not found");
        return -1;
    }
}
