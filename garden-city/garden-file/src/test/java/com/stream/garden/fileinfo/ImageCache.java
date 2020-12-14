package com.stream.garden.fileinfo;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ImageCache {

    private final int maxCacheSize = 10;
    private final List<String> cacheList = new ArrayList<>(maxCacheSize);
    private final Map<String, byte[]> cacheMap = new HashMap<>(maxCacheSize);
    private final Lock lock = new ReentrantLock();

    private ImageCache() {
    }

    public static ImageCache getInstance() {
        return ImageCacheInstance.INSTANCE;
    }

    private void moveToFirst(String key) {
        int index = cacheList.indexOf(key);
        Collections.swap(cacheList, index, 0);
    }

    public void add(String key, byte[] value) {
        lock.lock();
        try {
            if (cacheMap.containsKey(key)) {
                moveToFirst(key);
                return;
            }
            cacheList.add(key);
            cacheMap.put(key, value);
            if (cacheList.size() > maxCacheSize) {
                String lastKey = cacheList.get(cacheList.size() - 1);
                cacheList.remove(cacheList.size() - 1);
                cacheMap.remove(lastKey);
            }
        } finally {
            lock.unlock();
        }
    }

    public byte[] get(String key) {
        lock.lock();
        try {
            if (cacheMap.containsKey(key)) {
                moveToFirst(key);
                return cacheMap.get(key);
            } else {
                return null;
            }
        } finally {
            lock.unlock();
        }
    }

    public void del(String key) {
        lock.lock();
        try {
            cacheList.remove(key);
            cacheMap.remove(key);
        } finally {
            lock.unlock();
        }
    }

    private static class ImageCacheInstance {
        private static final ImageCache INSTANCE = new ImageCache();
    }
}
