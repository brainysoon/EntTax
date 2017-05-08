package com.enttax.service.impl;

import com.enttax.service.SecurityService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brainy on 17-4-27.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    /**
     * 随机声生成后半 摘要
     */
    private SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Override
    public Map<String, String> encodePassword(String password, String preSalt) {

        //后半 摘要
        String subSalt = randomNumberGenerator.nextBytes().toHex();
        String salt = preSalt + subSalt;

        //生成
        SimpleHash simpleHash = new SimpleHash(ENCODE_ALGORITHM_NAME,
                password, salt, ENCODE_HASH_ITERATION);
        String encodePassword = simpleHash.toHex();

        //返回 生成的   和 摘要
        Map<String, String> map = new HashMap<>();
        map.put(ENCODE_RESULT_KEY_PASSWORD, encodePassword);
        map.put(ENCODE_RESULT_KEY_SALT, subSalt);

        return map;
    }
}
