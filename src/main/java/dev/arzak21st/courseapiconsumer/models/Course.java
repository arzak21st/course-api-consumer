package dev.arzak21st.courseapiconsumer.models;

public class Course {

    private int courseId;
    private String name;
    Topic topic;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId + ", name=" + name + '}';
    }
}
