package com.eric.authdemo.util;

import cn.hutool.core.map.MapUtil;
import com.eric.authdemo.constant.JwtConstants;
import com.eric.authdemo.exception.TokenException;
import com.eric.authdemo.model.SecurityUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * token = Base64(Header).Base64(Payload).Base64(Signature)
 * Signature = HS256(Base64(Header),Base64(Payload),秘钥)
 *
 * @author Eric
 * @date 2019-5-26 23:21:24
 */
@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret:eric}")
    private String secret;

    @Value("${jwt.expiration-time:1}")
    private Long expirationTime;

    public String createByAuthentication(Authentication authentication) {
        Map<String, Object> map = MapUtil.newHashMap();
        SecurityUserDetails principal = (SecurityUserDetails) authentication.getPrincipal();
        map.put(JwtConstants.PAYLOAD_NAME, principal.getName());
        return create(map);
    }

    public String create(Map<String, Object> claims) {
        // 加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        LocalDateTime time = LocalDateTime.now().plusHours(expirationTime);
        byte[] keyBytes = secret.getBytes();
        Key signingKey = new SecretKeySpec(keyBytes, signatureAlgorithm.getJcaName());
        // 附带ID信息
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("auth_demo")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }

    /**
     * 获取Payload
     * 不做过期处理!!
     *
     * @param token 令牌
     * @return Claims
     */
//    public static Map<String, Object> getPayload(String token) {
//        String payload = StrSpliter.split(token, '.', 0, true, true).get(1);
//        return JSONObject.parseObject(Base64.decodeStr(payload), Map.class);
//    }

    /**
     * 解析token
     *
     * @param token 令牌
     * @return Date
     */
    public Claims parseToken(String token) {
        // 获取Claims
        try {
            return Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("===== Token过期 =====");
            throw new TokenException("登陆凭证过期");
        } catch (Exception e) {
            log.error("===== Token解析异常 =====");
            throw new TokenException("登陆凭证异常");
        }
    }

    /**
     * 判断token是否合法
     *
     * @param token 令牌
     * @return boolean
     */
    public Boolean verifyToken(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }
}
