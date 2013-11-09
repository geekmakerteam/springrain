package org.springrain.frame.shiro;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.BaseLogger;
import org.springrain.frame.util.SerializeUtil;

public class ShiroRedisCache<K,V> extends BaseLogger implements Cache<K,V>    {
private String name;
private ICached cached;
private final String SHIRO_REDIS_CACHE = "shiro_redis_cache-";
public ShiroRedisCache(String name,ICached cached){
	this.name=name;
	this.cached=cached;
}


/**
 * 获得byte[]型的key
 * @param key
 * @return
 */
private byte[] getByteKey(K key){
	if(key instanceof String){
		String preKey = this.SHIRO_REDIS_CACHE + key;
		return preKey.getBytes();
	}else{
		return SerializeUtil.serialize(key);
	}
}
	
@Override
public V get(K key) throws CacheException {
	logger.debug("根据key从Redis中获取对象 key [" + key + "]");
	try {
		if (key == null) {
            return null;
        }else{
        	V value= (V) cached.getCached(getByteKey(key));
        	return value;
        }
	} catch (Throwable t) {
		throw new CacheException(t);
	}

}

@Override
public V put(K key, V value) throws CacheException {
	logger.debug("根据key从存储 key [" + key + "]");
	 try {
		 	cached.updateCached(getByteKey(key), SerializeUtil.serialize(value),null);
            return value;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
}

@Override
public V remove(K key) throws CacheException {
	logger.debug("从redis中删除 key [" + key + "]");
	try {
        V previous = get(key);
        cached.deleteCached(getByteKey(key));
        return previous;
    } catch (Throwable t) {
        throw new CacheException(t);
    }
}

@Override
public void clear() throws CacheException {
	logger.debug("从redis中删除所有元素");
	try {
        cached.clearDB();
    } catch (Throwable t) {
        throw new CacheException(t);
    }
}

@Override
public int size() {
	try {
		Long longSize = new Long(cached.getDBSize());
        return longSize.intValue();
    } catch (Throwable t) {
        throw new CacheException(t);
    }
}

@SuppressWarnings("unchecked")
@Override
public Set<K> keys() {
	try {
        Set<K> keys = cached.getKeys((this.SHIRO_REDIS_CACHE + "*").getBytes());
      return keys;
    } catch (Throwable t) {
        throw new CacheException(t);
    }
}

@Override
public Collection<V> values() {
	try {
		Collection<V> values = cached.getKeys((this.SHIRO_REDIS_CACHE + "*").getBytes());
      return values;
    } catch (Throwable t) {
        throw new CacheException(t);
    }
}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public ICached getCached() {
		return cached;
	}


	public void setCached(ICached cached) {
		this.cached = cached;
	}

}
