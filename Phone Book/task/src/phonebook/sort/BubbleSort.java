package phonebook.sort;

import phonebook.domain.PhoneBookEntry;
import phonebook.domain.TimeDuration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// This bubble sort algorithm is currently applicable only to PhonebookEntry objects.
// It may be good to investigate how to make it applicable to more classes, either by using Generics or by using
// Interfaces.
public class BubbleSort {


    FileWriter sortDetailsWriter;

    public Optional<List<PhoneBookEntry>> sortPhoneBook(List<PhoneBookEntry> unsortedPhoneBook, boolean ascending,
                                                        long maxduration, boolean exportDetails) throws IOException {

        if (!ascending) {
            throw new RuntimeException("Only ascending sorting is currently supported");
        }

        long startTime = System.currentTimeMillis();

        List<PhoneBookEntry> toSortPhoneBook = new ArrayList<PhoneBookEntry>(unsortedPhoneBook);

        if (exportDetails) {
            sortDetailsWriter = new FileWriter("bubbleSortDetails.txt");
        }


        PhoneBookEntry tempEntry;
        boolean changesOccuredInLastLoop;
        int countIters = 0;
        int numberOfChangesInIter;

        do {
            changesOccuredInLastLoop = false;
            numberOfChangesInIter = 0;
            for (int i = 0; i < toSortPhoneBook.size() - 1; i++) {
                if (toSortPhoneBook.get(i).compareTo(toSortPhoneBook.get(i + 1)) > 0) {
                    tempEntry = toSortPhoneBook.get(i);
                    toSortPhoneBook.set(i, toSortPhoneBook.get(i + 1));
                    toSortPhoneBook.set(i + 1, tempEntry);
                    changesOccuredInLastLoop = true;
                    numberOfChangesInIter++;
                }
            }
            countIters++;
            if (exportDetails) {
                String message = "Finished " + countIters + " iteration, with " + numberOfChangesInIter + " changes";
                System.out.println(message);
                sortDetailsWriter.write(message + System.lineSeparator());
            }
            if (System.currentTimeMillis() - startTime > maxduration) {
                return Optional.empty();
            }
        } while (changesOccuredInLastLoop);

        if (exportDetails) {
            sortDetailsWriter.close();
        }

        return Optional.of(toSortPhoneBook);

    }
}
