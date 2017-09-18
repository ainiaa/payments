package com.a91coding.payments.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import java.util.Random;
import java.util.UUID;

public final class RandomStringUtils {
    public static String getRandomString() { //length表示生成字符串的长度
        UUID uuid= UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }

    public static void main(String[] args) {
        String str = getRandomString();
        int len = str.length();
        System.out.printf("uuid:%s len:%d", str, len);
    }
}