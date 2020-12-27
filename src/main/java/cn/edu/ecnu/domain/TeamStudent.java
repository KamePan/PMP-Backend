package cn.edu.ecnu.domain;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class TeamStudent implements Serializable {

    private String teamid;

    private String sid;

    private static final long serialVersionUID = 1L;

}