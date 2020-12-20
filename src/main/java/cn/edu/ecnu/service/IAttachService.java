package cn.edu.ecnu.service;

import org.springframework.web.multipart.MultipartFile;

public interface IAttachService {

    public String insertAttachment(String pid, MultipartFile file, String path);
}
