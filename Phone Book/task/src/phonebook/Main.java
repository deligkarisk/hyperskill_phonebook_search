package phonebook;

import phonebook.domain.PhoneBookEntry;
import phonebook.domain.TimeDuration;
import phonebook.hash.HashTable;
import phonebook.search.BinarySearchString;
import phonebook.search.JumpSearchString;
import phonebook.search.LinearSearchString;
import phonebook.search.Search;
import phonebook.sort.BubbleSort;
import phonebook.sort.QuickSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    static BubbleSort bubbleSort = new BubbleSort();
    static LinearSearchString linearSearch = new LinearSearchString();
    static JumpSearchString jumpSearch = new JumpSearchString();
    static QuickSort quickSort = new QuickSort();
    static BinarySearchString binarySearch = new BinarySearchString();

    static File directoryFile = new File("/home/kosmas-deligkaris/directory.txt");
    static File findFile = new File("/home/kosmas-deligkaris/find.txt");
    static List<PhoneBookEntry> directoryList = new ArrayList<PhoneBookEntry>();
    static List<String> findList = new ArrayList<String>();
    static HashTable hashTable;


    public static void main(String[] args) throws IOException {

        loadData(directoryFile, findFile);

        System.out.println("Start searching (linear search)...");
        long startTimeLinear = System.currentTimeMillis();
        int countFindLinearSearch = findAllEntries(linearSearch, directoryList, findList);
        long stopTimeLinear = System.currentTimeMillis();
        TimeDuration timeTakenLinear = new TimeDuration(startTimeLinear, stopTimeLinear);
        printPerformanceDetails(countFindLinearSearch, findList.size(), timeTakenLinear);


        System.out.println("Start searching (bubble sort + jump search)...");
        long startTimeSortingBubbleSort = System.currentTimeMillis();
        long maxDuration = (stopTimeLinear - startTimeLinear) * 10;
        boolean jumpSearchCompleted = false;
        Optional<List<PhoneBookEntry>> optionalSortedPhoneBook = bubbleSort.sortPhoneBook(directoryList, true,
                maxDuration,
                false);
        TimeDuration timeTakenSortStep2 = new TimeDuration(startTimeSortingBubbleSort, System.currentTimeMillis());
        if (optionalSortedPhoneBook.isPresent()) { jumpSearchCompleted = true;}

        // prepare for the search
        int countFindStep2 = -1;
        long startTimeSearch;

        startTimeSearch = System.currentTimeMillis();
        if (jumpSearchCompleted) {
            List<PhoneBookEntry> sortedPhoneBook = optionalSortedPhoneBook.get();
            countFindStep2 = findAllEntries(jumpSearch, sortedPhoneBook, findList);
        } else {
            countFindStep2 = findAllEntries(linearSearch, directoryList, findList);
        }
        TimeDuration timeTakenSearchStep2 = new TimeDuration(startTimeSearch, System.currentTimeMillis());
        TimeDuration timeTakenTotalStep2 = new TimeDuration(startTimeSortingBubbleSort, System.currentTimeMillis());
        printPerformanceDetails(countFindStep2, findList.size(), timeTakenTotalStep2);
        printSearchAndSortDetails(timeTakenSortStep2, timeTakenSearchStep2, jumpSearchCompleted);


        System.out.println("Start searching (quick sort + binary search)...");
        List<PhoneBookEntry> clonedPhonebook = new ArrayList<>(directoryList);
        long startTimeSortingQuickSort = System.currentTimeMillis();
        quickSort.sortPhoneBook(clonedPhonebook);
        TimeDuration timeTakenSortStep3 = new TimeDuration(startTimeSortingQuickSort, System.currentTimeMillis());
        long startTimeSearchBinarySearch = System.currentTimeMillis();
        int countFindStep3 = findAllEntries(binarySearch, clonedPhonebook, findList);
        TimeDuration timeTakenSearchStep3 = new TimeDuration(startTimeSearchBinarySearch, System.currentTimeMillis());
        TimeDuration timeTakenTotalStep3 = new TimeDuration(startTimeSortingQuickSort, System.currentTimeMillis());
        printPerformanceDetails(countFindStep3, findList.size(), timeTakenTotalStep3);
        printSearchAndSortDetails(timeTakenSortStep3, timeTakenSearchStep3, true);


        System.out.println("Start searching (hash table)...");
        hashTable = new HashTable((int) (1.2*directoryList.size()));


        long startTimeFitDataToHashTable = System.currentTimeMillis();
        fitDirectoryDataToHashTable(directoryList, hashTable);
        TimeDuration timeTakenFitDataToHashTable = new TimeDuration(startTimeFitDataToHashTable,
                System.currentTimeMillis());
        long startTimeFindDataInHashTable = System.currentTimeMillis();
        int countFindHashTable = findAllEntries(hashTable, findList);
        TimeDuration timeTakenSearchInHashTable = new TimeDuration(startTimeFindDataInHashTable,
                System.currentTimeMillis());
        TimeDuration timeTakenTotalHashTable = new TimeDuration(startTimeFitDataToHashTable,
                System.currentTimeMillis());
        printPerformanceDetails(countFindHashTable, findList.size(), timeTakenTotalHashTable);
        printHashAndSearchDetails(timeTakenFitDataToHashTable, timeTakenSearchInHashTable, true);
    }









    private static void fitDirectoryDataToHashTable(List<PhoneBookEntry> directoryList, HashTable hashTable) {
        for (PhoneBookEntry entry: directoryList) {
            hashTable.add(entry);
        }
    }




    private static void printPerformanceDetails(int countFinds, int listSize, TimeDuration timeTaken) {
        System.out.println("Found " + countFinds + " / " + listSize +
                " entries. Time taken: " +
                timeTaken.getMinutes() + " min. " +
                timeTaken.getSeconds() + " sec. " +
                timeTaken.getMilliseconds() + " ms.");
    }


    private static void printSearchAndSortDetails(TimeDuration timeTakenSort, TimeDuration timeTakenSearch,
                                                       boolean sortCompleted) {
            System.out.print("Sorting time: " +
                    timeTakenSort.getMinutes() + " min. " +
                    timeTakenSort.getSeconds() + " sec. " +
                    timeTakenSort.getMilliseconds() + " ms.");

            if (!sortCompleted) {
                System.out.println(" - STOPPED, moved to linear search");
            } else {
                System.out.println("");
            }
            
            System.out.println("Searching time: " +
                    timeTakenSearch.getMinutes() + " min. " +
                    timeTakenSearch.getSeconds() + " sec. " +
                    timeTakenSearch.getMilliseconds() + " ms.");
        }


    private static void printHashAndSearchDetails(TimeDuration timeTakenSort, TimeDuration timeTakenSearch,
                                                  boolean sortCompleted) {
        System.out.print("Creating time: " +
                timeTakenSort.getMinutes() + " min. " +
                timeTakenSort.getSeconds() + " sec. " +
                timeTakenSort.getMilliseconds() + " ms.");

        if (!sortCompleted) {
            System.out.println(" - STOPPED, moved to linear search");
        } else {
            System.out.println("");
        }

        System.out.println("Searching time: " +
                timeTakenSearch.getMinutes() + " min. " +
                timeTakenSearch.getSeconds() + " sec. " +
                timeTakenSearch.getMilliseconds() + " ms.");
    }



    private static void loadData(File directoryFile, File findFile) throws FileNotFoundException {
        // Load directory into memory
        Scanner directoryScanner = new Scanner(directoryFile);
        while (directoryScanner.hasNext()) {
            directoryList.add(new PhoneBookEntry(directoryScanner.nextLine()));
        }
        System.out.println("Loaded directory");

        // Load entries from find file
        Scanner findScanner = new Scanner(findFile);
        while (findScanner.hasNext()) {
            findList.add(findScanner.nextLine());
        }
        System.out.println("Loaded find list");
    }


    private static int findAllEntries(HashTable hashTable, List<String> findList) {
        int countFind = 0;

        for (String entryToFind: findList) {
            if (hashTable.find(entryToFind) > - 1) {
                countFind++;
            }
        }

        return countFind;

    }

    private static int findAllEntries(Search searchAlgorithm, List<PhoneBookEntry> fullPhonebook,
                                      List<String> findList) {

        int countFind = 0;


        for (String entryToFind : findList) {
            if (searchAlgorithm.search(fullPhonebook, entryToFind) > -1) {
                countFind++;
            }
        }
        return countFind;
    }

    }





