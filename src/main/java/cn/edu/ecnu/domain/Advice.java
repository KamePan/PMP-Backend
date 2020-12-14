package cn.edu.ecnu.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "")
public class Advice implements Serializable {

    private String aid;

    private String content;

    private String pid;

    private Date advicetime;

    private static final long serialVersionUID = 1L;

}