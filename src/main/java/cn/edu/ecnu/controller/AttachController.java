package cn.edu.ecnu.controller;

import cn.edu.ecnu.service.AttachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

@Api(tags = "文件处理控制器")
@RestController
@RequestMapping("/attach")
public class AttachController {

    @Autowired
    private AttachService attachService;

    @ApiOperation("文件上传")
    @ResponseBody
    @PostMapping("/{pid}")
    public String upload(@PathVariable("pid")String pid,
                         @RequestParam("files")MultipartFile[] files,
                         HttpServletRequest request) throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        path = path.substring(1).replace("/", "\\");
        path = path.replace("\\target\\classes", "\\src\\main\\resources\\static");
        System.out.println(path);
        for (MultipartFile file : files) {
            String filepath = attachService.insertAttachment(pid, file, path);
            System.out.println("文件上传到了路径 "+ filepath + " 下");
        }
        return "上传成功";
    }

}
