package phonebook;

import phonebook.domain.PhoneBookEntry;
import phonebook.domain.TimeDuration;
import phonebook.search.JumpSearchString;
import phonebook.search.LinearSearchString;
import phonebook.search.Search;
import phonebook.sort.BubbleSort;
import phonebook.sort.QuickSort;
import phonebook.sort.QuickSortInteger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    static BubbleSort bubbleSort = new BubbleSort();
    static LinearSearchString linearSearchString = new LinearSearchString();
    static JumpSearchString jumpSearchString = new JumpSearchString();
    static QuickSort quickSort = new QuickSort();


    public static void main(String[] args) throws IOException {
        File directoryFile = new File("/home/kosmas-deligkaris/directory.txt");
        File findFile = new File("/home/kosmas-deligkaris/find.txt");

        // Load directory into memory
        List<PhoneBookEntry> directoryList = new ArrayList<PhoneBookEntry>();
        Scanner directoryScanner = new Scanner(directoryFile);
        while (directoryScanner.hasNext()) {
            directoryList.add(new PhoneBookEntry(directoryScanner.nextLine()));
        }
        System.out.println("Loaded directory");

        // Load entries from find file
        Scanner findScanner = new Scanner(findFile);
        List<String> findList = new ArrayList<String>();
        while (findScanner.hasNext()) {
            findList.add(findScanner.nextLine());
        }
        System.out.println("Loaded find list");


        System.out.println("Unsorted array:");
        System.out.println(Arrays.toString(directoryList.subList(0,10).toArray()));

        quickSort.sortPhoneBook(directoryList.subList(0,10));
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(directoryList.subList(0,10).toArray()));











/*
        long startTimeLinear = System.currentTimeMillis();
        int countFindLinearSearch = 0;
        System.out.println("Start searching (linear search)...");
        for (String entryToFind : findList) {
            if (linearSearchString.search(directoryList, entryToFind) > -1) {
                countFindLinearSearch++;
            }
        }
        long stopTimeLinear = System.currentTimeMillis();
        TimeDuration timeTakenLinear = new TimeDuration(startTimeLinear, stopTimeLinear);

        System.out.println("Found " + countFindLinearSearch + " / " + findList.size() +
                " entries. Time taken: " +
                timeTakenLinear.getMinutes() + " min. " +
                timeTakenLinear.getSeconds() + " sec. " +
                timeTakenLinear.getMilliseconds() + " ms.");


        System.out.println("Start searching (bubble sort + jump search)...");
        long startTimeSorting = System.currentTimeMillis();
        long maxDuration = (stopTimeLinear - startTimeLinear) * 10;
        Optional<List<PhoneBookEntry>> optionalSortedPhoneBook = bubbleSort.sortPhoneBook(directoryList, true,
                maxDuration,
                false);
        TimeDuration timeTakenSort = new TimeDuration(startTimeSorting, System.currentTimeMillis());

        // prepare for the search
        int countFind = 0;
        long startTimeSearch;

        startTimeSearch = System.currentTimeMillis();
        if (optionalSortedPhoneBook.isPresent()) {
            List<PhoneBookEntry> sortedPhoneBook = optionalSortedPhoneBook.get();

            for (String entryToFind : findList) {
                if (jumpSearchString.search(sortedPhoneBook, entryToFind, false) > -1) {
                    countFind++;
                }
            }
        } else {
            for (String entryToFind : findList) {
                if (linearSearchString.search(directoryList, entryToFind) > -1) {
                    countFind++;
                }
            }
        }

        TimeDuration timeTakenSearch = new TimeDuration(startTimeSearch, System.currentTimeMillis());
        TimeDuration timeTakenTotal = new TimeDuration(startTimeSorting, System.currentTimeMillis());
        System.out.println("Found " + countFind + " / " + findList.size() + " entries. Time taken: " +
                timeTakenTotal.getMinutes() + " min. " +
                timeTakenTotal.getSeconds() + " sec. " +
                timeTakenTotal.getMilliseconds() + " ms.");


        System.out.print("Sorting time: " +
                timeTakenSort.getMinutes() + " min. " +
                timeTakenSort.getSeconds() + " sec. " +
                timeTakenSort.getMilliseconds() + " ms.");

        if (optionalSortedPhoneBook.isEmpty()) {
            System.out.println(" - STOPPED, moved to linear search");
        }

        System.out.println("Searching time: " +
                timeTakenSearch.getMinutes() + " min. " +
                timeTakenSearch.getSeconds() + " sec. " +
                timeTakenSearch.getMilliseconds() + " ms.");


    }*/

    }
}

