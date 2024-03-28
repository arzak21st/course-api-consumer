package dev.arzak21st.courseapiconsumer.models;

import java.util.List;

public class Topic {

    private int topicId;
    private String name;
    List<Course> courses;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Topic{" + "topicId=" + topicId + ", name=" + name + ", courses=" + courses + '}';
    }
}
