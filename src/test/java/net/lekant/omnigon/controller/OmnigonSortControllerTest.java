package net.lekant.omnigon.controller;

import net.lekant.omnigon.App;
import net.lekant.omnigon.service.impl.OmnigonSortServiceImplTest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * Created by qlehenaff on 12/11/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OmnigonSortControllerTest {

    Logger logger = Logger.getLogger(OmnigonSortControllerTest.class);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testExample() {
        String payload =
                "{\"strings\": [\n" +
                        "    \"Sound boy proceed to blast into the galaxy\",\n" +
                        "    \"Go back rocket man into the sky you'll see\",\n" +
                        "    \"Hear it all the time, come back rewind\",\n" +
                        "    \"Aliens are watching up in the sky\",\n" +
                        "    \"Sound boy process to blast into the galaxy\",\n" +
                        "    \"No one gonna harm you\",\n" +
                        "    \"They all want you to play I watch the birds of prey\"\n" +
                        "]}";

        String expectedResult = "{\n" +
                "  \"result\" : [ {\n" +
                "    \"string\" : \"Aliens are watching up in the sky\",\n" +
                "    \"longestWord\" : 8\n" +
                "  }, {\n" +
                "    \"string\" : \"Sound boy process to blast into the galaxy\",\n" +
                "    \"longestWord\" : 7\n" +
                "  }, {\n" +
                "    \"string\" : \"Sound boy proceed to blast into the galaxy\",\n" +
                "    \"longestWord\" : 7\n" +
                "  }, {\n" +
                "    \"string\" : \"Go back rocket man into the sky you'll see\",\n" +
                "    \"longestWord\" : 6\n" +
                "  }, {\n" +
                "    \"string\" : \"Hear it all the time, come back rewind\",\n" +
                "    \"longestWord\" : 6\n" +
                "  } ]\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/string/sortWordsByLengthDesc",
                entity, String.class, new HashMap<String, String>());
        logger.debug(responseEntity.getBody());

        Assert.assertEquals(expectedResult,responseEntity.getBody());
    }


}
