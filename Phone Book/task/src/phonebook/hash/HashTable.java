package phonebook.hash;

import phonebook.domain.PhoneBookEntry;

import java.util.ArrayList;
import java.util.List;


public class HashTable {

    List<List<PhoneBookEntry>> buckets;


    public int findHashValue(PhoneBookEntry entry) {
        String name = entry.getName();
        return findHashValue(name);
    }

    public int findHashValue(String string) {
        int p = 5;
        int moduloOperator = this.buckets.size();
        int size = string.length();
        char[] tempCharArray = new char[size];
        tempCharArray = string.toCharArray();

        int currentH = 0;
        for (int i = 0; i < tempCharArray.length; i++) {
            currentH = (currentH*p + (int) tempCharArray[i])%moduloOperator;
        }

        return currentH;
    }



    public HashTable(int size) {

        buckets = new ArrayList<>(size);

        // initialize each bucket
        for (int i = 0; i < size; i++) {
            List<PhoneBookEntry> tempList = new ArrayList<PhoneBookEntry>();
            buckets.add(tempList);
        }

    }


    public void add(PhoneBookEntry entryToAdd) {

        int index = this.findHashValue(entryToAdd);
        List<PhoneBookEntry> bucket = buckets.get(index);
        bucket.add(entryToAdd);
    }

    public int size() {
        return buckets.size();
    }

    public int find(String entry) {
        int hashValue = this.findHashValue(entry);
        List<PhoneBookEntry> bucket = buckets.get(hashValue);

        if (bucket.size() == 0) {
            return -1;
        }

        for (PhoneBookEntry potentialEntry: bucket) {
            if (potentialEntry.getName().equals(entry)) {
                return hashValue;
            }
        }
        return -1;
    }
}
