package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Attachment;

public interface IAttachService {

    void insertAttachment(Attachment attachment);

    void deleteAttachmentByAid(String aid);

    Attachment findAttachmentByAid(String aid);
}
