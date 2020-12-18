package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.AdviceMapper;
import cn.edu.ecnu.domain.Advice;
import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class AdviceService {

    @Autowired
    private AdviceMapper adviceMapper;

    public void insertAdvice(Advice advice) {
        adviceMapper.insert(advice);
    }
}
