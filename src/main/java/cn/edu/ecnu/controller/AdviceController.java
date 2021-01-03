package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Advice;
import cn.edu.ecnu.service.IAdviceService;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
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
    @PostMapping("/{pid}")
    public JSONObject createAdvice(@PathVariable String pid, @RequestBody Advice advice) {

        String aid = "A" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        advice.setAid(aid).setPid(pid);
        Date date = new Date();
        System.out.println(date.getTime());
        advice.setAdvicetime(date);
        adviceService.insertAdvice(advice);
        return (JSONObject) JSON.toJSON(advice);
    }
}
