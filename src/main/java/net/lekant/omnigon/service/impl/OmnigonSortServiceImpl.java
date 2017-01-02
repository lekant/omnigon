package net.lekant.omnigon.service.impl;

import net.lekant.omnigon.comparable.DefaultOmnigonSortComparator;
import net.lekant.omnigon.dto.LongestWordString;
import net.lekant.omnigon.service.OmnigonSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by qlehenaff on 1/2/17.
 *
 */
@Component
public class OmnigonSortServiceImpl implements OmnigonSortService {


    public static final String WORDS = "\\W";

    // The maximum capacity of results to be returned
    @Value(value = "${net.lekant.omnigon.sortMaxResults:5}")
    private int maxResults;

    @Autowired
    private Comparator<LongestWordString> resultsSorter;

    @Override
    public Set<LongestWordString> getSortedLongestWord(List<String> strings) {
        // Uses a tree set to order elements using the LongestWordString#compareTo as LongestWordString is comparable
        TreeSet<LongestWordString> result = new TreeSet<>(resultsSorter);

        sortListOfStrings(strings, result);
        return result;
    }

    /**
     * Creates a LongestWordString with the longest word for each entry in strings,
     * then populates result if there is enough space
     * @param strings a list of strings
     * @param result the sorted longest strings with score of the longest string
     */
    protected void sortListOfStrings(List<String> strings, TreeSet<LongestWordString> result) {
        strings.stream().forEach(n ->
                createLongestWordEntry(result, n)
        );
    }

    /**
     * Creates a LongestWordString with the longest word in n ,then populates result if there is enough space
     * @param result
     * @param n
     */
    protected void createLongestWordEntry(TreeSet<LongestWordString> result, String n) {
        String[] words = splitAsWords(n);
        LongestWordString longestWordString;
        if (words.length == 0) {
            longestWordString = new LongestWordString(n, "");
        } else {
            sortWordsByLengthDesc(words);
            longestWordString = new LongestWordString(n, words[0]);
        }
        if (result.size() >= maxResults) {
            LongestWordString last = result.last();
            if (resultsSorter.compare(last,longestWordString) == 1) {
                result.remove(last);
                result.add(longestWordString);
            }
        }
        else{
            result.add(longestWordString);
        }
    }

    /**
     * Remove spaces tabs and carriage returns
     * @param sentence
     * @return
     */
    protected String[] splitAsWords(String sentence) {
        Stream<String> wordStream = Pattern.compile(WORDS).splitAsStream(sentence);
        return wordStream.toArray(String[]::new);
    }

    /**
     * Sorts the array of words by word length desc
     * @param words
     */
    protected void sortWordsByLengthDesc(String[] words) {
        Arrays.sort(words, 0, words.length,
                (o1, o2) -> o1.length() > o2.length() ? -1 :
                        o1.length() < o2.length() ? 1 : 0);
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public void setResultsSorter(DefaultOmnigonSortComparator resultsSorter) {
        this.resultsSorter = resultsSorter;
    }
}
