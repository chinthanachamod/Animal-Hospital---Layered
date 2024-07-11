package lk.ijse.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    public boolean save(T obj) throws SQLException,NullPointerException;
    public boolean update(T obj) throws SQLException,NullPointerException;
    public boolean delete(String id) throws SQLException,NullPointerException;
    public List<String> getValue(String... value) throws SQLException,NullPointerException;
    public List<T> getAll() throws SQLException,NullPointerException;
    public T searchById(String value) throws SQLException,NullPointerException;

}
