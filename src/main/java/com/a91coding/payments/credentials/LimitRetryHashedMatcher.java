package com.a91coding.payments.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class LimitRetryHashedMatcher extends HashedCredentialsMatcher {
    private static final Logger logger = LoggerFactory.getLogger(LimitRetryHashedMatcher.class);

    private Cache<String, AtomicInteger> cache;
    private final int maxRetryTimes;
    public LimitRetryHashedMatcher(CacheManager cacheManager, int maxRetryTimes, String cacheKey) {
        logger.info("LimitRetryHashedMatcher() cacheManager:" + cacheManager + " maxRetryTimes:" + maxRetryTimes + " cacheKey:" + cacheKey);
        cache = cacheManager.getCache(cacheKey);
        this.maxRetryTimes = maxRetryTimes;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        logger.info("LimitRetryHashedMatcher.doCredentialsMatch");
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = cache.get(username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            cache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > maxRetryTimes) {
            throw new ExcessiveAttemptsException();
        }
        Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        Object accountCredentials = getCredentials(info);
        logger.info("LimitRetryHashedMatcher.doCredentialsMatch tokenHashedCredentials:" + tokenHashedCredentials + " accountCredentials:" + accountCredentials);
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            cache.remove(username);
        }
        logger.info("LimitRetryHashedMatcher.doCredentialsMatch matches:" + matches);
        return matches;
    }

    @Override
    protected Object hashProvidedCredentials(AuthenticationToken token, AuthenticationInfo info) {
        Object salt = null;
        if (info instanceof SaltedAuthenticationInfo) {
            salt = ((SaltedAuthenticationInfo) info).getCredentialsSalt();
        } else {
            //retain 1.0 backwards compatibility:
            if (isHashSalted()) {
                salt = getSalt(token);
            }
        }
        logger.info("LimitRetryHashedMatcher.hashProvidedCredentials getCredentials:" + token.getCredentials() + " salt:" + salt + " getHashIterations:" + getHashIterations());
        return hashProvidedCredentials(token.getCredentials(), salt, getHashIterations());
    }

    public static void main(String[] args) {
        String algorithmName = "md5";
        String password = "111111";
        String[] userNameList = new String[]{"admin", "dev", "doc", "test"};
        for (String s : userNameList) {
            String salt1 = s;
            String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
            int hashIterations = 3;
            SimpleHash hash = new SimpleHash(algorithmName, password,
                    salt1 + salt2, hashIterations);
            String encodedPassword = hash.toHex();
            System.out.println(s);
            System.out.println(encodedPassword);
            System.out.println(salt2);
        }

    }
}
