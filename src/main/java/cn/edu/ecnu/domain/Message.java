package cn.edu.ecnu.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "")
public class Message implements Serializable {

    private String mid;

    private String content;

    private String uid;

    private static final long serialVersionUID = 1L;

}