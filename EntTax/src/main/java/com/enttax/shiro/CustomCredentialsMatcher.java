package com.enttax.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by brainy on 17-4-28.
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {

    private static final String CREDENTIALS_MATCHER_PASSWORD_CACHE_KEY = "passwordRetryCache";
    private static final String CREDENTIALS_MATCHER_RETRY_OUT_OF_LIMIT = "重试密码超过4次";

    //密码重复次数的缓存
    private Cache<String, AtomicInteger> passwordRetryCache;

    public CustomCredentialsMatcher(CacheManager cacheManager) {

        //注入密码相关缓存
        passwordRetryCache = cacheManager.getCache(CREDENTIALS_MATCHER_PASSWORD_CACHE_KEY);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        //验证id
        String sId = (String) token.getPrincipal();

        //重试记录
        AtomicInteger retryCount = passwordRetryCache.get(sId);

        //如果先前没有记录, 设置缓存
        if (retryCount == null) {

            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(sId, retryCount);
        }

        //如果重试密码超过10次，弹出异常 不给于登录
        if (retryCount.incrementAndGet() > 4) {

            throw new ExcessiveAttemptsException(CREDENTIALS_MATCHER_RETRY_OUT_OF_LIMIT);
        }

        //尝试 验证密码
        boolean macherResult = super.doCredentialsMatch(token, info);

        //如果验证成功,则清除缓存
        if (macherResult) {

            passwordRetryCache.remove(sId);
        }

        return macherResult;
    }
}
