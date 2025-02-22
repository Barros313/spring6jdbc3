package com.pluralsight.conference;

import com.pluralsight.conference.model.Speaker;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class Spring6jdbc3ApplicationTests {

    @Test
    public void testCreateSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        Speaker speaker = new Speaker();
        speaker.setName("Mylene Cavalcanti");

        speaker = restTemplate.postForObject("http://localhost:8080/speaker", speaker, Speaker.class);

        System.out.println(speaker.getName());

    }

    @Test
    void testGetSpeakers() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Speaker>> speakersResponse = restTemplate.exchange(
                "http://localhost:8080/speaker", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Speaker>>() {
                });

        assertNotNull(speakersResponse.getBody(), "Body is null");

        List<Speaker> speakers = speakersResponse.getBody();

        for (Speaker speaker : speakers) {
            System.out.println("Speaker name: " + speaker.getName());
        }
    }

    @Test
    void testGetSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        Speaker speaker = restTemplate.getForObject("http://localhost:8080/speaker/{id}", Speaker.class, 27);

        if (speaker != null) {
            System.out.println("Speaker name: " + speaker.getName());
        } else {
            System.out.println("Not found");
        }
    }

    @Test
    void testUpdateSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        Speaker speaker = restTemplate.getForObject("http://localhost:8080/speaker/{id}", Speaker.class, 27);

        speaker.setName(speaker.getName() + " Sr.");

        restTemplate.put("http://localhost:8080/speaker", speaker);

        System.out.println("Speaker name: " + speaker.getName() + "\n skill: " + speaker.getSkill());
    }

    @Test
    public void testBatchUpdate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject("http://localhost:8080/speaker/batch", Object.class);


    }

    @Test
    void testDeleteSpeaker() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete("http://localhost:8080/speaker/delete/{id}" , 31);
    }

    @Test
    void testException() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject("http://localhost:8080/speaker/test" , Speaker.class);

    }

}
