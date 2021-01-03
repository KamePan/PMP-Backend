package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Judge;

public interface IJudgeService {

    void assignJudgeForProject(Judge judge);

    void updateJudgeForProject(Judge judge);
}
