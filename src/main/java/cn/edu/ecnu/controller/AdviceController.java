package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Advice;
import cn.edu.ecnu.service.IAdviceService;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Api(tags = "建议信息控制器")
@RestController
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private IAdviceService adviceService;

    @ApiOperation("提出建议接口")
    @ResponseBody
    @PostMapping
    public JSONObject createAdvice(@RequestBody Advice advice) {
        JSONObject object = new JSONObject();
        String aid = "A" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        advice.setAid(aid);
        advice.setAdvicetime(new Date());
        adviceService.insertAdvice(advice);

        // 本端是否为消费端
        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
        // 获取最后一次调用的提供方IP地址
        String serverIp = RpcContext.getContext().getRemoteHost();
        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        String application = RpcContext.getContext().getUrl().getParameter("application");

        object.put("isConsumerSide", isConsumerSide);
        object.put("serverIp", serverIp);
        object.put("application", application);

        object.put("advice", advice);
        return object;
    }
}
