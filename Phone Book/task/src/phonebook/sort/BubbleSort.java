package phonebook.sort;

import phonebook.domain.PhoneBookEntry;

import java.util.ArrayList;
import java.util.List;

// This bubble sort algorithm is currently applicable only to PhonebookEntry objects.
// It may be good to investigate how to make it applicable to more classes, either by using Generics or by using
// Interfaces.
public class BubbleSort {

    public List<PhoneBookEntry> sortPhoneBook(List<PhoneBookEntry> unsortedPhoneBook, boolean ascending) {

        if (!ascending) {
            throw new RuntimeException("Only ascending sorting is currently supported");
        }

        List<PhoneBookEntry> toSortPhoneBook = new ArrayList<PhoneBookEntry>(unsortedPhoneBook);

        PhoneBookEntry tempEntry;
        boolean changesOccuredInLastLoop;
        int countIters = 0;
        int numberOfChangesInIter;

        do {
            changesOccuredInLastLoop = false;
            numberOfChangesInIter = 0;
            for (int i = 0; i < toSortPhoneBook.size() - 1; i++) {
                if (toSortPhoneBook.get(i).compareTo(toSortPhoneBook.get(i+1)) > 0) {
                    tempEntry = toSortPhoneBook.get(i);
                    toSortPhoneBook.set(i, toSortPhoneBook.get(i + 1));
                    toSortPhoneBook.set(i + 1, tempEntry);
                    changesOccuredInLastLoop = true;
                    numberOfChangesInIter++;
                }
            }
            countIters++;
            System.out.println("Finished " + countIters + " iteration, with " + numberOfChangesInIter + " changes");
        } while (changesOccuredInLastLoop);

        return toSortPhoneBook;

    }
}
