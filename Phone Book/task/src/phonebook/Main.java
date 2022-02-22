package phonebook;

import phonebook.domain.PhoneBookEntry;
import phonebook.search.Search;
import phonebook.sort.BubbleSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static BubbleSort bubbleSort = new BubbleSort();



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
        List<PhoneBookEntry> findList = new ArrayList<PhoneBookEntry>();
        while (findScanner.hasNext()) {
            findList.add(new PhoneBookEntry(findScanner.nextLine()));
        }
        System.out.println("Loaded find list");


        List<PhoneBookEntry> sortedPhoneBook = bubbleSort.sortPhoneBook(directoryList, true);

        FileWriter sortedDirectoryWriter = new FileWriter("output.txt");
        for(PhoneBookEntry entry: sortedPhoneBook) {
            sortedDirectoryWriter.write(entry.getEntry() + System.lineSeparator());
        }
        sortedDirectoryWriter.close();




























/*


        // Find entries
        System.out.println("Start searching...");
        int foundEntries = 0;
        long preTime = System.currentTimeMillis();

        for (String entry : findList) {
            // System.out.println("Searching for: " + entry);
            for (String directoryEntry : directoryList) {
                if (directoryEntry.contains(entry)) {
                  //  System.out.println("Entry found");
                    foundEntries++;
                    break;
                }
            }
        }
        long postTime = System.currentTimeMillis();
        long timePassed = (postTime - preTime);
        long timePassedSec = timePassed / 1000;
        long remainingMillisecs = timePassed % 1000;
        long passedMin = timePassedSec / 60;
        long remainingSecs = timePassedSec % 60;



        System.out.println("Found " + foundEntries + " / " + findList.size() + " entries. Time taken: " + passedMin + " min. " + remainingSecs + " sec. " + remainingMillisecs + " ms.");*/


    }


}

