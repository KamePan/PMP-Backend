package cn.edu.ecnu.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {

    private String sid;

    private String email;

    private Boolean sex;

    private String department;

    private List<Team> teams;

    private List<Message> messages;

    private static final long serialVersionUID = 1L;

}