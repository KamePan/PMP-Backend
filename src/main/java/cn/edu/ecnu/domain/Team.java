package cn.edu.ecnu.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@ApiModel(description = "")
public class Team implements Serializable {

    private String teamid;

    private String teamname;

    private List<Student> students;

    private List<Project> projects;

    private static final long serialVersionUID = 1L;


}