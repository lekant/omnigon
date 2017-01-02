package net.lekant.omnigon.controller.string;

import net.lekant.omnigon.dto.LongestWordString;
import net.lekant.omnigon.service.OmnigonSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by qlehenaff on 12/11/16.
 */
@Controller
@RequestMapping(value = "/string")
public class OmnigonSortController {

    @Autowired
    private OmnigonSortService omnigonSortService;

    @RequestMapping(method = RequestMethod.POST, value = "/sortWordsByLengthDesc",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    /**
     * reads a collection of strings from a request payload,
     * sort them by provided comparison algorithm,
     * and feed the top 5 results into response processor.
     */
    public ResponseEntity<OmnigonSortResponseBody> sort(@RequestBody OmnigonSortRequestBody body) {

        Assert.notNull(body);
        Assert.notNull(body.getStrings());
        Assert.notEmpty(body.getStrings());

        List<String> stringList = body.getStrings();

        Set<LongestWordString> result = omnigonSortService.getSortedLongestWord(stringList);

        OmnigonSortResponseBody omnigonSortResponseBody = new OmnigonSortResponseBody();
        omnigonSortResponseBody.setResult(result);

        return new ResponseEntity<>(omnigonSortResponseBody, HttpStatus.ACCEPTED);
    }


}
