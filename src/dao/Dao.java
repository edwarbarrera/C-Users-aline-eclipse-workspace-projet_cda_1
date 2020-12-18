package dao;
import java.util.HashMap;

public interface Dao <T> {
	
	public void insert(T t);
	public boolean delete (int id);
	public T  findById(int id);
}
