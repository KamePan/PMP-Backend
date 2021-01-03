package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Judge;
import cn.edu.ecnu.service.IJudgeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "审查信息控制器")
@RequestMapping("/judge")
public class JudgeController {

    @Autowired
    private IJudgeService judgeService;


    @ApiOperation("分配项目审查老师")
    @PostMapping
    public JSONArray assignJudgeForProject(@RequestBody List<Judge> judges) {
        for (Judge judge : judges) {
            judgeService.assignJudgeForProject(judge);
        }
        return JSONArray.parseArray(JSON.toJSONString(judges));
    }

    @ApiOperation("终期审查老师评分、点评接口")
    @PutMapping("/finalJudge")
    public JSONObject updateJudgeForFinalProject(@RequestBody Judge judge) {
        judge.setStage(4);
        judgeService.assignJudgeForProject(judge);
        return (JSONObject) JSON.toJSON(judge);
    }

    @ApiOperation("中期审查老师评分、点评接口")
    @PutMapping("/middleJudge")
    public JSONObject updateJudgeForMiddleProject(@RequestBody Judge judge) {
        judge.setStage(3);
        judgeService.updateJudgeForProject(judge);
        return (JSONObject) JSON.toJSON(judge);
    }
}
