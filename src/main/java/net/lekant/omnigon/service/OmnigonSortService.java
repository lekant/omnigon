package net.lekant.omnigon.service;

import net.lekant.omnigon.dto.LongestWordString;

import java.util.List;
import java.util.Set;

/**
 * Created by qlehenaff on 1/2/17.
 */
public interface OmnigonSortService {

    /**
     * Generates a sorted Set that contains the provided strings sorted according to their longest word
     * @param strings a list of strings, single words or sentences
     * @return a sorted Set that contains the provided strings sorted according to their longest word
     */
    Set<LongestWordString> getSortedLongestWord(List<String> strings);
}
