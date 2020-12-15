package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Advice;
import cn.edu.ecnu.service.AdviceService;
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
    private AdviceService adviceService;

    @ApiOperation("提出建议接口")
    @ResponseBody
    @PostMapping
    public JSONObject createAdvice(@RequestBody Advice advice) {
        JSONObject object = new JSONObject();
        String aid = "A" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        advice.setAid(aid);
        advice.setAdvicetime(new Date());
        adviceService.insertAdvice(advice);
        object.put("advice", advice);
        return object;
    }
}
