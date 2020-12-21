package dao;

import java.sql.SQLException;

public interface Dao <T> {
	
	public void insert(T t) throws SQLException;
	public boolean delete (int id);
	public T findById(int id);

}
