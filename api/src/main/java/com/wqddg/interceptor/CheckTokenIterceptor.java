package com.wqddg.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wqddg
 * @ClassName CheckTokenIterceptor
 * @DateTime: 2021/11/15 23:34
 * @remarks : #
 */
@Component
public class CheckTokenIterceptor implements HandlerInterceptor{

    @Autowired
    private StringRedisTemplate template;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("opttons".equalsIgnoreCase(method)){
            return true;
        }
        String token = request.getHeader("token");
        if (token==null){
            ResultVo resultVo = new ResultVo(ResStatus.LOGIN_FAIL_NOT, "请先登录", null);
            doResponse(response,resultVo);
            //提升请先登录
            return false;
        }else {
            String tem = template.boundValueOps(token).get();
            if (tem==null){
                ResultVo resultVo = new ResultVo(ResStatus.LOGIN_FAIL_NOT, "请先登录", null);
                doResponse(response,resultVo);
                return false;
            }else {
                //在拦截器中进行重置失效事件
                template.boundValueOps(token).expire(30, TimeUnit.MINUTES);
                return true;
            }

            /**try {
                JwtParser parser = Jwts.parser();
                parser.setSigningKey("wqddg");
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                return true;
            }catch (ExpiredJwtException e){
                ResultVo resultVo = new ResultVo(ResStatus.LOGIN_FAIL_TIMEOUT, "登录过期、请重新登录", null);
                doResponse(response,resultVo);
                return false;
            }catch (UnsupportedJwtException e){
                ResultVo resultVo = new ResultVo(ResStatus.NO, "Token不合法 请自重", null);
                doResponse(response,resultVo);
                return false;
            }
            catch (Exception e){
                ResultVo resultVo = new ResultVo(ResStatus.LOGIN_FAIL_NOT, "请先登录", null);
                doResponse(response,resultVo);
                return false;
            }**/
        }
    }

    private void doResponse(HttpServletResponse response,ResultVo resultVo) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        //转化为json
        String json=new ObjectMapper().writeValueAsString(resultVo);
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
