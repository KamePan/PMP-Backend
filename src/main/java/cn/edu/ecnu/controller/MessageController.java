package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Message;
import cn.edu.ecnu.service.MessageService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Api(tags = "消息控制器")
@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("向接收队列的每个id发送一条内容为content的消息")
    @ResponseBody
    @PostMapping
    public JSONObject sendMessages(@RequestBody List<Message> messages) {
        JSONObject object = new JSONObject();
        for (Message message : messages) {
            String mid = "M" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            message.setMid(mid);
            message.setMessagetime(new Date());
            messageService.insertMessage(message);
        }
        object.put("messages", messages);
        return object;
    }

}
