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
public class User implements Serializable {

    private String uid;

    private String username;

    private String password;

    private String role;

    private static final long serialVersionUID = 1L;

}