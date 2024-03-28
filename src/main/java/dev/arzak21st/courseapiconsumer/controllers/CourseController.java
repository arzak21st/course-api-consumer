package dev.arzak21st.courseapiconsumer.controllers;

import dev.arzak21st.courseapiconsumer.models.Course;
import dev.arzak21st.courseapiconsumer.services.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getTopic/{topicId}/getAllCourses")
    public String getAllCourses(@PathVariable int topicId, Model model) {

        List<Course> courses = courseService.getAllCourses(topicId);
        model.addAttribute("topicId", topicId);
        model.addAttribute("courses", courses);
        return "courses";
    }

//    @GetMapping("/getTopic/{topicId}/getCourse/{courseId}")
//    public String getCourse(@PathVariable int courseId, Model model) {
//
//        Course course = courseService.getCourse(courseId);
//        model.addAttribute("course", course);
//        return "courses";
//    }

    @PostMapping("/getTopic/{topicId}/addCourse")
    public String addCourse(@PathVariable int topicId, @ModelAttribute Course course) {

        courseService.saveCourse(topicId, course);
        return "redirect:/getTopic/" + topicId + "/getAllCourses";
    }

    @PostMapping("/getTopic/{topicId}/updateCourse")
    public String updateCourse(@PathVariable int topicId, @ModelAttribute Course course) {

        courseService.saveCourse(topicId, course);
        return "redirect:/getTopic/" + topicId + "/getAllCourses";
    }

    @GetMapping("/getTopic/{topicId}/deleteCourse/{courseId}")
    public String deleteCourse(@PathVariable int topicId, @PathVariable int courseId) {

        courseService.deleteCourse(courseId);
        return "redirect:/getTopic/" + topicId + "/getAllCourses";
    }
}
