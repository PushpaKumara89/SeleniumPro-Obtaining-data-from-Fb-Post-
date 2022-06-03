package sampleapp.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CurdDao <T, ID> extends SuperDao{
    public boolean save(T t) throws ClassNotFoundException, SQLException, InterruptedException;
    public boolean delete(ID id) throws ClassNotFoundException, SQLException;
    public boolean update(ID id) throws ClassNotFoundException, SQLException;
    public T get(ID id) throws ClassNotFoundException, SQLException;
    public ArrayList<T> getAll(String txt) throws ClassNotFoundException, SQLException;
}
