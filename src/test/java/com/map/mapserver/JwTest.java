package com.map.mapserver;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwTest {


    @Test
    public void testGen() {

        Map<String,Object> claims=new HashMap<>();
        claims.put("id","1");
        claims.put("username","张三");
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256("itheima"));
        System.out.println(token);
    }

    @Test
    public void testParse(){
//    DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("itheima")).build().verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoi5byg5LiJIn0sImV4cCI6MTcwNTIzNjc4MH0.o9-vuG1Wo8hJq4dz13phsahLf5tlxizTRr0ksWS0494");
//    System.out.println(decodedJWT.getClaims().get("user"));
    }
}
