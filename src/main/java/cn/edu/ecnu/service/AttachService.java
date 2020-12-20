package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.AttachmentMapper;
import cn.edu.ecnu.domain.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

//@Service
public class AttachService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    /*获取图片后缀并生成 aid，将 attachment 对象添加到数据库中*/
    public String insertAttachment(String pid, MultipartFile file, String path) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String aid = "A" + UUID.randomUUID().toString().substring(0, 8);
        String filepath = path + aid + suffix;
        try {
            File fileToSave = new File(filepath);
            file.transferTo(fileToSave);
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        Attachment attachment = new Attachment(aid, filepath, pid);
        attachmentMapper.insert(attachment);
        return path;
    }
}
