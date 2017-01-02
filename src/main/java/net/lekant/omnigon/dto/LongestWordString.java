package net.lekant.omnigon.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.lekant.omnigon.comparable.DefaultOmnigonSortComparator;

/**
 * Created by qlehenaff on 12/11/16.
 */
public class LongestWordString implements Comparable {

    private String string;

    private int longestWord;

    @JsonIgnore
    // Internal information for comparison
    private String selected;

    public LongestWordString(String string, String selected) {
        this.string = string;
        this.selected = selected;
        this.longestWord = selected.length();
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(int longestWord) {
        this.longestWord = longestWord;
    }

    public String getSelected() {
        return selected;
    }

    @Override
    public String toString() {
        return "LongestWordString{" +
                "string='" + string + '\'' +
                ", longestWord=" + longestWord +
                ", selected='" + selected + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LongestWordString that = (LongestWordString) o;

        if (longestWord != that.longestWord) return false;
        return (string != null ? string.equals(that.string) : that.string == null) && (selected != null ? selected.equals(that.selected) : that.selected == null);
    }

    @Override
    public int hashCode() {
        int result = string != null ? string.hashCode() : 0;
        result = 31 * result + longestWord;
        result = 31 * result + (selected != null ? selected.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        return new DefaultOmnigonSortComparator().compare(this, (LongestWordString) o);
    }
}
