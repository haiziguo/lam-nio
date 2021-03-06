package lam.dao.support;

/**
* <p>
* Dao-处理雪崩
* </p>
* @author linanmiao
* @date 2017年6月29日
* @version 1.0
*/
public interface CacheDao <T>{
	
	public T get(Long id);
	
	public boolean set(T t);
	
	public boolean delete(Long id);

}
