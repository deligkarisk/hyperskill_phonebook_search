package phonebook.search;

import phonebook.domain.PhoneBookEntry;

import java.util.List;

public class BinarySearchString implements Search{

    public int search(List<PhoneBookEntry> sortedPhonebook, String entryToFind) {


        int left = 0;
        int right = sortedPhonebook.size() - 1;

        while ( left <= right) {
            int middle = (left + right)/2;
          //  System.out.println("middle value: " + middle);

            if (sortedPhonebook.get(middle).getName().equals(entryToFind)) {
         //       System.out.println("Entry found at index: " + middle);
                return middle;
            } else if (sortedPhonebook.get(middle).getName().compareTo(entryToFind) > 0) {
                right = middle - 1;
        //        System.out.println("right value: " + right);
            } else {
                left = middle + 1;
       //         System.out.println("left value: " + left);
            }
        }
        // if not found, return -1
       // System.out.println("Entry not found.");
        return -1;
    }
}
