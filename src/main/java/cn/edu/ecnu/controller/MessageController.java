package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Message;
import cn.edu.ecnu.netty.ChannelPool;
import cn.edu.ecnu.service.IMessageService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Api(tags = "消息控制器")
@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @ApiOperation("向接收队列的每个id发送一条内容为content的消息")
    @ResponseBody
    @PostMapping
    public JSONArray sendMessages(@RequestBody List<Message> messages) {
        System.out.println(messages);
        for (Message message : messages) {
            String mid = "M" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            message.setMid(mid);
            message.setMessagetime(new Date());
            messageService.insertMessage(message);

            ChannelPool.pointSending(message.getUid(), message.getContent());
        }
        return JSONArray.parseArray(JSON.toJSONString(messages));
    }

}
