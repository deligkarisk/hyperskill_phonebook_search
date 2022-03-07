package phonebook.search;

import phonebook.domain.PhoneBookEntry;

import java.util.List;

public class LinearSearchString {

    public int search(List<PhoneBookEntry> catalog, String stringToFind) {

        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getName().equals(stringToFind)) {
                return i;
            }
        }

        return -1;
    }
}
