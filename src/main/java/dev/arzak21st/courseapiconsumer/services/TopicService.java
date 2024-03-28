package dev.arzak21st.courseapiconsumer.services;

import dev.arzak21st.courseapiconsumer.models.Topic;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TopicService {

    /*
    Spring Boot no longer automatically defines a RestTemplate but instead defines a RestTemplateBuilder, allowing you more control over the RestTemplate that gets created
    The RestTemplateBuilder can be injected as an argument in a @Bean method to create a RestTemplate
    See "dev.arzak21st.courseapiconsumer.config.SpringConfiguration"
    */

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.url}") // Injects the API base URL (http://localhost:1412/course-api) from the 'application.properties' file
    private String apiUrl;

    public List<Topic> getAllTopics() {

        List<Topic> topics = null;

        try {

            // Prepare the request information
            String url = apiUrl + "/topics";
            HttpMethod method = HttpMethod.GET;
            HttpEntity httpEntity = null;
            ParameterizedTypeReference<List<Topic>> parameterizedTypeReference = new ParameterizedTypeReference<List<Topic>>() {};

            // Make the request to retrieve the list of topics
            ResponseEntity<List<Topic>> responseEntity = restTemplate.exchange(url, method, httpEntity, parameterizedTypeReference);

            topics = responseEntity.getBody();
        }
        catch(Exception exception) {
            System.out.println("***** Exception" + "\n" + exception.toString() + "\n" + "***** Exception");
        }

        return topics;
    }

    public Topic getTopic(int topicId) {

        Topic topic = null;

        try {

            // Prepare the request information
            String url = apiUrl + "/topics/" + topicId;

            // Make the request to retrieve the topic
            ResponseEntity<Topic> responseEntity = restTemplate.getForEntity(url, Topic.class);

            topic = responseEntity.getBody();
        }
        catch(Exception exception) {
            System.out.println("***** Exception" + "\n" + exception.toString() + "\n" + "***** Exception");
        }

        return topic;
    }

    public boolean saveTopic(Topic topic) {

        try {

            // Prepare the request information
            String url = apiUrl + "/topics";

            // Make the request to post the topic
            restTemplate.postForEntity(url, topic, Topic.class);

            return true;
        }
        catch(Exception exception) {
            System.out.println("***** Exception" + "\n" + exception.toString() + "\n" + "***** Exception");
        }

        return false;
    }

    public boolean deleteTopic(int topicId) {

        try {

            // Prepare the request information
            String url = apiUrl + "/topics/" + topicId;

            // Make the request to delete  the topic
            restTemplate.delete(url);

            return true;
        }
        catch(Exception exception) {
            System.out.println("***** Exception" + "\n" + exception.toString() + "\n" + "***** Exception");
        }

        return false;
    }
}
