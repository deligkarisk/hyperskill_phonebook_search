package phonebook.search;

import phonebook.domain.PhoneBookEntry;

import java.util.List;

public interface Search {

    int search(List<PhoneBookEntry> phoneBookEntries, String entryToFind);



}