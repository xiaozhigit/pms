package com.xxx.pms.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.security.MD5Encoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtils {

    public static final int VALID_TIME=1*60*60*1000;//单位毫秒  目前设置是一个小时

    // 设置签名加密方法
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    // 加密的密钥
    public static final String SECRET="secret";

    /**
     *生成token
     * @param subject  用户名
     * @param claims 附加信息
     * @return
     */
    public static String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())// 设置发布日期
                .setExpiration(new Date(System.currentTimeMillis()+JwtUtils.VALID_TIME))// 设置有效期
                .compressWith(CompressionCodecs.DEFLATE)// 设置压缩方式
                .signWith(SIGNATURE_ALGORITHM, JwtUtils.SECRET)// 设置签名
                .compact();
    }


    /**
     * 验证token
     * 如果解析失败会报MalformedJwtException 错误
     * 如果解析时间超时会报ExpiredJwtException 错误
     * @param token
     * @return
     */
    public static Claims checkToken(String token) {
        return Jwts.parser()
                .setSigningKey(JwtUtils.SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static int getUserIdByRequest(HttpServletRequest request) {
        String authHeaders = request.getHeader("Authorization");
        String token = authHeaders.replace("Bearer ","");
        Claims claims = checkToken(token);
        int userId = (int)claims.get("userId");
        return userId;
    }

    public static int getCompanyIdByRequest(HttpServletRequest request) {
        String authHeaders = request.getHeader("Authorization");
        String token = authHeaders.replace("Bearer ","");
        Claims claims = checkToken(token);
        int companyId = (int)claims.get("companyId");
        return companyId;
    }

    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7,token.length());
        return token;
    }

    /**
     * 得到claims中的信息
     * @param claims
     * @param key
     * @return
     */
    public static Object getClaimsValue(Claims claims,Object key){
        return claims.get(key);
    }

    public static void main(String[] args) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("claims","claims");
//        String ss = JwtUtils.generateToken("sss",claims);
//        System.out.println(ss);
//        Claims c = checkToken("eyJhbGciOiJIUzI1NiIsInppcCI6IkRFRiJ9.eNosy8EKwyAQhOF3mbOCZqvu5m3WxoMhgcAaKJS-ez3kNv8H84XdFSvMDA7vQ_tpM5_h0D4X1phDpBwCJYeu44EkUibso8-DLtyYavMbKftXy-JFqfqpUQotsTDj9wcAAP__.gfEs0hwUKBBzz-MhV_xCVEI4uyCwGPuSMS5w1C13biU");
//        //       ExpiredJwtException
//        System.out.println(c.getSubject());
//        System.out.println(c.getExpiration());

        String encode = "ly"+"123456";
        String encodePassword = MD5Encoder.encode(encode.getBytes());
        System.out.println(encodePassword);


    }



}
