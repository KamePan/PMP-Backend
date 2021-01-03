package cn.edu.ecnu.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@ApiModel(description = "")
public class Judge implements Serializable {

    private String tid;

    private String pid;

    private String comments;

    private Integer score;

    private Integer stage;

    private static final long serialVersionUID = 1L;

}