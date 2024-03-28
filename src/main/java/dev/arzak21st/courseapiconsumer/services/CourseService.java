package dev.arzak21st.courseapiconsumer.services;

import dev.arzak21st.courseapiconsumer.models.Course;
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
public class CourseService {

    /*
    Spring Boot no longer automatically defines a RestTemplate but instead defines a RestTemplateBuilder, allowing you more control over the RestTemplate that gets created
    The RestTemplateBuilder can be injected as an argument in a @Bean method to create a RestTemplate
    See "dev.arzak21st.courseapiconsumer.config.SpringConfiguration"
    */

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.url}") // Injects the API base URL (http://localhost:1412/course-api) from the 'application.properties' file
    private String apiUrl;

    public List<Course> getAllCourses(int topicId) {

        List<Course> courses = null;

        try {

            // Prepare the request information
            String url = apiUrl + "/topics/" + topicId + "/courses";
            HttpMethod method = HttpMethod.GET;
            HttpEntity httpEntity = null;
            ParameterizedTypeReference<List<Course>> parameterizedTypeReference = new ParameterizedTypeReference<List<Course>>() {};

            // Make the request to retrieve the list of courses
            ResponseEntity<List<Course>> responseEntity = restTemplate.exchange(url, method, httpEntity, parameterizedTypeReference);

            courses = responseEntity.getBody();
        }
        catch(Exception exception) {
            System.out.println("***** Exception" + "\n" + exception.toString() + "\n" + "***** Exception");
        }

        return courses;
    }

    public Course getCourse(int courseId) {

        Course course = null;

        try {

            // Prepare the request information
            String url = apiUrl + "/courses/" + courseId;

            // Make the request to retrieve the course
            ResponseEntity<Course> responseEntity = restTemplate.getForEntity(url, Course.class);

            course = responseEntity.getBody();
        }
        catch(Exception exception) {
            System.out.println("***** Exception" + "\n" + exception.toString() + "\n" + "***** Exception");
        }

        return course;
    }

    public boolean saveCourse(int topicId, Course course) {

        try {

            // Prepare the request information
            String url = apiUrl + "/topics/" + topicId + "/courses";

            // Make the request to post the course
            restTemplate.postForEntity(url, course, Course.class);

            return true;
        }
        catch(Exception exception) {
            System.out.println("***** Exception" + "\n" + exception.toString() + "\n" + "***** Exception");
        }

        return false;
    }

    public boolean deleteCourse(int courseId) {

        try {

            // Prepare the request information
            String url = apiUrl + "/courses/" + courseId;

            // Make the request to delete  the course
            restTemplate.delete(url);

            return true;
        }
        catch(Exception exception) {
            System.out.println("***** Exception" + "\n" + exception.toString() + "\n" + "***** Exception");
        }

        return false;
    }
}
