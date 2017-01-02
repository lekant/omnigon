package net.lekant.omnigon.service.impl;

import net.lekant.omnigon.comparable.DefaultOmnigonSortComparator;
import net.lekant.omnigon.dto.LongestWordString;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by qlehenaff on 1/2/17.
 */
public class OmnigonSortServiceImplTest {

    private Logger logger = Logger.getLogger(OmnigonSortServiceImplTest.class);

    private OmnigonSortServiceImpl omnigonSortService = new OmnigonSortServiceImpl();

    @Before
    public void setup() {
        omnigonSortService = new OmnigonSortServiceImpl();
        omnigonSortService.setMaxResults(5);
        omnigonSortService.setResultsSorter(new DefaultOmnigonSortComparator());
    }

    @Test
    public void testSplit() {
        String[] expected = omnigonSortService.splitAsWords(" ");
        Assert.assertArrayEquals(expected, new String[]{});
        Assert.assertArrayEquals(omnigonSortService.splitAsWords("Sound boy proceed to blast into the galaxy"),
                new String[]{"Sound", "boy", "proceed", "to", "blast", "into", "the", "galaxy"});
    }

    @Test
    public void testSort() {
        String[] words = {"Sound", "boy", "proceed", "to", "blast", "into", "the", "galaxy"};
        omnigonSortService.sortWordsByLengthDesc(words);
        Assert.assertArrayEquals(
                new String[]{"proceed", "galaxy","Sound", "blast", "into", "boy", "the", "to"}
                ,words);

    }

    @Test
    public void testSortList() {
        String[] sentences = {
                "Sound boy proceed to blast into the galaxy",
                "Go back rocket man into the sky you'll see",
                "Hear it all the time, come back rewind",
                "Aliens are watching up in the sky",
                "Sound boy process to blast into the galaxy",
                "No one gonna harm you",
                "They all want you to play I watch the birds of prey"
        };
        List<String> strings = Arrays.asList(sentences);

        TreeSet<LongestWordString> result = new TreeSet<>();
        omnigonSortService.sortListOfStrings(strings,result);

        logger.debug(result);

        LongestWordString[] asArrayResult = result.toArray(new LongestWordString[]{});

        Assert.assertEquals("Aliens are watching up in the sky", asArrayResult[0].getString());
        Assert.assertEquals("Sound boy process to blast into the galaxy", asArrayResult[1].getString());
        Assert.assertEquals("Sound boy proceed to blast into the galaxy", asArrayResult[2].getString());
        Assert.assertEquals("Go back rocket man into the sky you'll see", asArrayResult[3].getString());
        Assert.assertEquals("Hear it all the time, come back rewind", asArrayResult[4].getString());

        Assert.assertEquals(8, asArrayResult[0].getLongestWord());
        Assert.assertEquals(7, asArrayResult[1].getLongestWord());
        Assert.assertEquals(7, asArrayResult[2].getLongestWord());
        Assert.assertEquals(6, asArrayResult[3].getLongestWord());
        Assert.assertEquals(6, asArrayResult[4].getLongestWord());
    }
}
