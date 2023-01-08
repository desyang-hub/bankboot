package com.bankboot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.bankboot.dao.AccountDao;
import com.bankboot.dao.SalesmanDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
@Component
public class JWTUtils {

    AccountDao accountDao;
    SalesmanDao salesmanDao;

    public JWTUtils(AccountDao accountDao, SalesmanDao salesmanDao) {
        this.accountDao = accountDao;
        this.salesmanDao = salesmanDao;
    }

    /**
     * 加密密钥
     */
    @Value("jkasehfjqewhgkjfeshfrwiehfi")
    String SECRET_KEY;

    /**
     * 获取token(身份令牌)
     * @return token
     */
    public String getToken(String id) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", id);

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * 验证token合法性 并返回结果
     */
    public boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e){
            log.info("token验证失败");
            return false;
        }
    }

    /**
     * 验证储户身份
     * @param token
     * @return
     */
    public boolean verifyAccount(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            String account = getId(token);
            if(accountDao.selectByAccount(account) == null) {
                log.info("token验证失败");
                return false;
            }
            return true;
        } catch (Exception e){
            log.info("token验证失败");
            return false;
        }
    }


    public boolean verifySalesman(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            String jobNo = getId(token);
            if(salesmanDao.selectByJobNo(jobNo) == null) {
                log.info("token验证失败");
                return false;
            }
            return true;
        } catch (Exception e){
            log.info("token验证失败");
            return false;
        }
    }

    /**
     * 解析token
     * @param token
     * @return 身份id
     */
    public String getId(String token){
        try{
            return JWT.decode(token).getClaim("id").asString();
        }catch(Exception ex){
            ex.printStackTrace();
            return "";
        }
    }
}
