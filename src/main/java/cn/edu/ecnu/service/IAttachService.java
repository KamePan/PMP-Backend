package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface IAttachService {

    void insertAttachment(Attachment attachment);

    void deleteAttachmentByAid(String aid);

    Attachment findAttachmentByAid(String aid);
}
