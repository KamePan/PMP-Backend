package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Attachment;
import cn.edu.ecnu.service.IAttachService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
                             @RequestParam("file")MultipartFile file) throws IOException {

        Attachment attachment = insertAttachment(pid, file);
        attachService.insertAttachment(attachment);
        return (JSONObject) JSON.toJSON(attachment);
    }

    @ApiOperation("文件删除")
    @ResponseBody
    @DeleteMapping("/{aid}")
    public JSONObject deleteAttachment(@PathVariable String aid) throws FileNotFoundException {

        Attachment attachment = attachService.findAttachmentByAid(aid);
        deleteAttachmentFromDir(attachment);
        attachService.deleteAttachmentByAid(aid);
        return (JSONObject) JSON.toJSON(attachment);
    }

    private void deleteAttachmentFromDir(Attachment attachment) throws FileNotFoundException {

        String path = ResourceUtils.getURL("classpath:").getPath();
        path = path.substring(1).replace("/", "\\");
        //target下路径： D:\Pan\IDEAWorkspace\PMP-backend\target\classes\static\
        String pathForTarget = path + "static\\";
        //项目下资源路径： D:\Pan\IDEAWorkspace\PMP-backend\src\main\resources\static\
        path = path.replace("\\target\\classes", "\\src\\main\\resources\\static");

        deleteFileFromDir(path + attachment.getPath());
        deleteFileFromDir(pathForTarget + attachment.getPath());
    }

    private void deleteFileFromDir(String path) {
        File file = new File(path);
        if (file.exists() == true) {
            System.out.println("文件存在，可执行删除操作");
            Boolean flag = false;
            flag = file.delete();
            if (flag) {
                System.out.println("成功删除文件" + file.getName());
            } else {
                System.out.println("文件删除失败");
            }
        }
    }

    private Attachment insertAttachment(String pid, MultipartFile file) throws IOException {
        if (file == null || pid == null) {
            return null;
        }
        String path = ResourceUtils.getURL("classpath:").getPath();
        path = path.substring(1).replace("/", "\\");
        //target下路径： D:\Pan\IDEAWorkspace\PMP-backend\target\classes\static\
        String pathForTarget = path + "static\\";
        //项目下资源路径： D:\Pan\IDEAWorkspace\PMP-backend\src\main\resources\static\
        path = path.replace("\\target\\classes", "\\src\\main\\resources\\static");

        /*由于要进行两次文件的transferTo操作，因此对文件进行复制*/
        InputStream inputStream1 = file.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream1.read(buffer)) > -1) {
            baos.write(buffer, 0, length);
        }
        baos.flush();
        InputStream inputStream2 = new ByteArrayInputStream(baos.toByteArray());
        // 测试时使用的 mock，开发时尽量不使用，但还未找到替代方案
        MultipartFile file2 = new MockMultipartFile(file.getName(), inputStream2);

        String originalFilename = file.getOriginalFilename();
        String filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String aid = "A" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String resPath = filename + "_" + aid + suffix;

        String pathForPro = path + resPath;
        String pathFroTar = pathForTarget + resPath;

        System.out.println(pathForPro);
        System.out.println(pathFroTar);


        try {
            File fileForPro = new File(pathForPro);
            File fileForTar = new File(pathFroTar);
            file.transferTo(fileForPro);
            file.transferTo(fileForTar);
            
        } catch (Exception e) {
            System.out.println("文件上传错误");
            e.printStackTrace();
            return null;
        }
        Attachment attachment = new Attachment().setAid(aid).setPath(resPath).setFilename(originalFilename).setPid(pid);
        System.out.println("文件上传到了静态资源路径 "+ pathForPro + " 下");
        System.out.println("文件上传到了target目录 "+ pathForPro + " 下");

        return attachment;
    }
}
