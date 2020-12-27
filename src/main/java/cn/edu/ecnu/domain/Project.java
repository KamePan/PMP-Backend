package cn.edu.ecnu.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "")
public class Project implements Serializable {

    private String pid;

    private String projectname;

    private String description;

    private String type;

    private Integer stage;

    private Integer midmark;

    private Integer finalmark;

    private String advisorid;

    private Teacher advisor;

    private String teamid;

    private Team team;

    private List<Advice> adviceList;

    private List<Attachment> attachments;

    private static final long serialVersionUID = 1L;


}