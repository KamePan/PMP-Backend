package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Attachment;
import cn.edu.ecnu.service.IAttachService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

@Api(tags = "文件处理控制器")
@RestController
@RequestMapping("/attach")
public class AttachController {

    @Autowired
    private IAttachService attachService;

    @ApiOperation("文件上传")
    @ResponseBody
    @PostMapping("/{pid}")
    public JSONObject upload(@PathVariable("pid")String pid,
                             @RequestParam("file")MultipartFile file) throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        path = path.substring(1).replace("/", "\\");
        path = path.replace("\\target\\classes", "\\src\\main\\resources\\static");
        System.out.println(path);
        Attachment attachment = insertAttachment(pid, file, path);
        System.out.println("文件上传到了路径 "+ attachment.getPath() + " 下");
        return (JSONObject) JSON.toJSON(attachment);
    }

    @ApiOperation("文件删除")
    @ResponseBody
    @DeleteMapping("/{aid}")
    public JSONObject deleteAttachment(@PathVariable String aid) throws FileNotFoundException {

        Attachment attachment = attachService.findAttachmentByAid(aid);
        deleteAttachmentFromDir(attachment.getPath());
        attachService.deleteAttachmentByAid(aid);
        return (JSONObject) JSON.toJSON(attachment);
    }


    private void deleteAttachmentFromDir(String path) {
        File fileInTarget = new File(path);
        if (fileInTarget.exists() == true) {
            System.out.println("文件存在，可执行删除操作");
            Boolean flag = false;
            flag = fileInTarget.delete();
            if (flag) {
                System.out.println("成功删除文件" + fileInTarget.getName());
            } else {
                System.out.println("文件删除失败");
            }
        }
    }


    private Attachment insertAttachment(String pid, MultipartFile file, String path) {

        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String aid = "A" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String filepath = path + aid + suffix;
        try {
            File fileToSave = new File(filepath);
            file.transferTo(fileToSave);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Attachment attachment = new Attachment(aid, filepath, pid);
        attachService.insertAttachment(attachment);
        return attachment;
    }
}
