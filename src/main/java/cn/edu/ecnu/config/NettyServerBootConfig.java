package cn.edu.ecnu.config;

import cn.edu.ecnu.netty.NettyServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class NettyServerBootConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            System.out.println("Netty Port: 8082");
            new NettyServer(8082).start();
        }
    }
}
