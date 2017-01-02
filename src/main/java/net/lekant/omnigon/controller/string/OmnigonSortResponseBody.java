package net.lekant.omnigon.controller.string;

import net.lekant.omnigon.dto.LongestWordString;

import java.util.List;
import java.util.Set;

/**
 * Created by qlehenaff on 1/1/17.
 */
public class OmnigonSortResponseBody {
    private Set<LongestWordString> result;

    public Set<LongestWordString> getResult() {
        return result;
    }

    public void setResult(Set<LongestWordString> result) {
        this.result = result;
    }
}
