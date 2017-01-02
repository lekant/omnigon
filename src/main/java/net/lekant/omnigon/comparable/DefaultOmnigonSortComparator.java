package net.lekant.omnigon.comparable;

import net.lekant.omnigon.dto.LongestWordString;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created by qlehenaff on 1/2/17.
 */
@Component
public class DefaultOmnigonSortComparator implements Comparator<LongestWordString> {

    @Override
    /**
     * Sorts the input strings by the longest word contained in the string
     * If there are several words of the same length, the "biggest by lexicographical comparison" should be selected among them.
     */
    public int compare(LongestWordString o1, LongestWordString o2) {
        return o1.getLongestWord() > o2.getLongestWord() ? -1 :
                o1.getLongestWord() < o2.getLongestWord() ? 1 :
                        o2.getSelected().compareTo(o1.getSelected());
    }
}
