package dev.arzak21st.courseapiconsumer.controllers;

import dev.arzak21st.courseapiconsumer.models.Topic;
import dev.arzak21st.courseapiconsumer.services.TopicService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/getAllTopics")
    public String getAllTopics(Model model) {

        List<Topic> topics = topicService.getAllTopics();
        model.addAttribute("topics", topics);
        return "topics";
    }

//    @GetMapping("/getTopic/{topicId}")
//    public String getTopic(@PathVariable int topicId, Model model) {
//
//        Topic topic = topicService.getTopic(topicId);
//        model.addAttribute("topic", topic);
//        return "topics";
//    }

    @PostMapping("/addTopic")
    public String addTopic(@ModelAttribute Topic topic) {

        topicService.saveTopic(topic);
        return "redirect:/getAllTopics";
    }

    @PostMapping("/updateTopic")
    public String updateTopic(@ModelAttribute Topic topic) {

        topicService.saveTopic(topic);
        return "redirect:/getAllTopics";
    }

    @GetMapping("/deleteTopic/{topicId}")
    public String deleteTopic(@PathVariable int topicId) {

        topicService.deleteTopic(topicId);
        return "redirect:/getAllTopics";
    }
}
