package cn.edu.ecnu.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teacher implements Serializable {

    private String tid;

    private String email;

    private Boolean sex;

    private String department;

    private String jobtitle;

    private String description;

    private List<Project> projectsToJudge;

    private List<Project> projectsToInstruct;

    private List<Message> messages;

    private static final long serialVersionUID = 1L;

}