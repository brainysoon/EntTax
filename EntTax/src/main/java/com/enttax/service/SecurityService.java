package com.enttax.service;

import java.util.Map;

/**
 * Created by brainy on 17-4-27.
 */
public interface SecurityService {

    /**
     * 加密的算法
     */
    String ENCODE_ALGORITHM_NAME = "md5";

    /**
     * 哈希 迭代加密次数
     */
    int ENCODE_HASH_ITERATION = 3;

    /**
     * 加密过后的密码的key
     */
    String ENCODE_RESULT_KEY_PASSWORD = "password";

    /**
     * 用户生成哈希值 的摘要
     */
    String ENCODE_RESULT_KEY_SALT = "salt";

    /**
     * @param password
     * @param preSalt
     * @return
     */
    Map<String, String> encodePassword(String password, String preSalt);
}