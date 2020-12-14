package cn.edu.ecnu.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamStudent implements Serializable {

    private String teamid;

    private String sid;

    private static final long serialVersionUID = 1L;

}