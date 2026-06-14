package top.curiez.mindlog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
@Slf4j
public class JwtUtils {
    public static String secret =
            "dVnsmy+SIX6pNptQdeclDSJ26EMSPEIhvZYKBTTug4k=";

    public static long EXPIRATION = 24*60*60*1000;

    private static SecretKey secretKey =
            Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

    public static String getJwtToken (Map<String,Object> claim) {
        String token = Jwts.builder().setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(secretKey)
                .compact();
        return token;
    }

    public static Claims parseToken (String token) {
        JwtParser build = Jwts.parserBuilder().setSigningKey(secretKey).build();
        Claims claims = null;
        try{
            claims = build.parseClaimsJws(token).getBody();
        }catch (Exception e) {
            log.error("解析token失败，token:{}",token);
            return null;
        }
        return claims;
    }

    public static Integer getIdByToken(String token) {
        Claims claims = parseToken(token);
        if(claims!=null) {
            Integer userId = (Integer) claims.get("id");
            if(userId>0) {
                return userId;
            }
        }
        return null;
    }
}
