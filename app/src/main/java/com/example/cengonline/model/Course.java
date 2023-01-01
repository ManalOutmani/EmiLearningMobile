package com.example.cengonline.model;

public class Course {
    private String courseId, courseName, coursePeriod, courseCode, studentName,
            courseTeacherName, courseTeacherId, courseStudentsId ;
    private String link;


    public Course() {
    }

    public Course(String courseId, String courseName, String coursePeriod,
                  String courseCode, String studentName, String courseTeacherName, String courseTeacherId, String courseStudentsId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.coursePeriod = coursePeriod;
        this.courseCode = courseCode;
        this.studentName = studentName;
        this.courseTeacherName = courseTeacherName;
        this.courseTeacherId = courseTeacherId;
        this.courseStudentsId = courseStudentsId;
    }

    public Course(String courseId, String courseName, String coursePeriod, String courseCode, String studentName, String courseTeacherName, String courseTeacherId, String courseStudentsId, String link) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.coursePeriod = coursePeriod;
        this.courseCode = courseCode;
        this.studentName = studentName;
        this.courseTeacherName = courseTeacherName;
        this.courseTeacherId = courseTeacherId;
        this.courseStudentsId = courseStudentsId;
        this.link = link;
    }

    public String getCourseStudentsId() {
        return courseStudentsId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseTeacherName() {
        return courseTeacherName;
    }

    public String getCourseTeacherId() {
        return courseTeacherId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
