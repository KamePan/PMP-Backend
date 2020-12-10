package cn.edu.ecnu.filter;

import cn.edu.ecnu.domain.JwtUser;
import cn.edu.ecnu.domain.User;
import cn.edu.ecnu.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // 拦截登录请求，即 uri 为 /auth/login 的请求
        super.setFilterProcessesUrl("/auth/login");
    }

    /*将验证用户名密码交给 spring-security 连接数据库验证
    猜测这就是实现 UserDetails 的原因*/
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            // 从输入流中获取到登录信息
            // ObjectMapper 类是 Jacksion 库的主要类
            // 能够使 Java 对象转化为 Json 对象，或 Json 对象转化为 java 对象
            // 此处为 json 对象转化为 User 类

            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    /**
                     * @param principal 用户名
                     * @param credentials 密码
                     * @param authorities 权限
                     * */
                    new UsernamePasswordAuthenticationToken(user.getUsername(),
                            user.getPassword(), new ArrayList<>())
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 成功验证后调用的方法
     * 如果验证成功，则根据 username 和 password 生成 token并返回
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        // 博客上写 getPrinciple() 返回实现了 UserDetails 接口的对象，我再看看
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
        String role = "";
        // 这里实际上可以存储多个角色
        for (GrantedAuthority authority : jwtUser.getAuthorities()) {
            role = authority.getAuthority();
        }
        String token = JwtUtil.createJwt(jwtUser.getUsername(), role);
        // 返回成功 token
        response.setHeader("token", JwtUtil.tokenPrefix + token);
    }

    /*验证失败后调用方法*/
    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed)
            throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
