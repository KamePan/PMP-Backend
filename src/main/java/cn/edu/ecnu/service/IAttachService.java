package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface IAttachService {

    public String insertAttachment(String pid, MultipartFile file, String path);

    void insertAttachment(Attachment attachment);
}
