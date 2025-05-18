package org.dm.trainingcourses.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainingControllerHttpRequestTest {

    private final Logger logger = LoggerFactory.getLogger(TrainingControllerHttpRequestTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String TRAINING = """
    {
        "name": "Java training",
        "description": "Learn the basics of Java programming.",
        "price": 149.99,
        "speaker": "Peter Python",
        "dates": [
            {
              "startDate": "2025-10-07T07:00:00.000+00:00",
              "endDate": "2025-10-07T15:00:00.000+00:00",
              "bookings": [
                  {
                      "name": "Bob Booking",
                      "email": "bb@email.com"
                  }
              ]
          }
        ]
    }
    """;

    private static final String BOOKING = """ 
    {
        "name": "Bob Booking",
        "email": "bb@email.com"
    }
    """;

    @Test
    void getTrainingsIsSuccessful() throws Exception {
        String url = getTrainingsUrl();
        ResponseEntity<String> response = (this.restTemplate.getForEntity(url, String.class));
        logger.debug("Response of {}: {}", url, response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void createTrainingIsSuccessful() throws Exception {
        JsonNode trainingNode = createTraining();
        String trainingId = trainingNode.get("id").asText();
        assertTrue(this.restTemplate.getForEntity(getTrainingUrl(trainingId), String.class).getStatusCode().is2xxSuccessful());
    }

    @Test
    void createBookingForTrainingIsSuccessful() throws Exception {
        JsonNode trainingNode = createTraining();
        assertTrue(trainingNode.hasNonNull("dates"));
        JsonNode datesNode = trainingNode.get("dates");
        assertTrue(datesNode.isArray(), "The 'dates' should be an array");
        assertFalse(datesNode.isEmpty(), "The 'dates' array should not be empty");
        Iterator<JsonNode> datesIterator = datesNode.elements();
        JsonNode dateNode = datesIterator.next();
        assertTrue(dateNode.hasNonNull("id"));
        String dateId = dateNode.get("id").asText();

        String trainingId = trainingNode.get("id").asText();
        String bookingsUrl = getBookingsUrl(trainingId, dateId);
        ResponseEntity<String> bookingResponse = this.restTemplate.postForEntity(bookingsUrl, getHttpEntity(BOOKING), String.class);
        logger.debug("Response of {}: {}", bookingsUrl, bookingResponse);
    }
    
    private JsonNode createTraining() throws JsonProcessingException {
        String trainingUrl = getTrainingsUrl();
        ResponseEntity<String> response = this.restTemplate.postForEntity(trainingUrl, getHttpEntity(TRAINING), String.class);
        logger.debug("Response of {}: {}", trainingUrl, response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        JsonNode trainingNode = objectMapper.readTree(response.getBody());
        assertTrue(trainingNode.hasNonNull("id"));
        return trainingNode;
    }


    private String getBookingsUrl(String trainingId, String dateId) {
        return getTrainingUrl(trainingId) + TrainingController.PATH_DATES + "/" + dateId + TrainingController.PATH_BOOKINGS;
    }

    private String getDateUrl(String trainingId, String dateId) {
        return getTrainingUrl(trainingId) + TrainingController.PATH_DATES + "/" + dateId;
    }

    private String getDatesUrl(String trainingId) {
        return getTrainingUrl(trainingId) + TrainingController.PATH_DATES;
    }

    private String getTrainingUrl(String trainingId) {
        return getTrainingsUrl() + "/" + trainingId;
    }

    private String getTrainingsUrl() {
        return getUrl(TrainingController.PATH_TRAININGS);
    }

    private String getUrl(String path) {
        return "http://localhost:" + port + path;
    }

    private HttpEntity<String> getHttpEntity(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}
