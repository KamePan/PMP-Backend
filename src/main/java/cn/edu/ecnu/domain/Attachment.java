package cn.edu.ecnu.domain;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Getter
@Setter
@ToString
public class Attachment implements Serializable {

    private String aid;

    private String filename;

    private String path;

    private String pid;

    private static final long serialVersionUID = 1L;

}