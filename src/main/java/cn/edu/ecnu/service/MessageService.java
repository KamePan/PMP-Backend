package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.MessageMapper;
import cn.edu.ecnu.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public void insertMessage(Message message) {
        messageMapper.insert(message);
    }

}
