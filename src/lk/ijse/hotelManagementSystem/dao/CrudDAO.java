package lk.ijse.hotelManagementSystem.dao;

import java.util.List;

public interface CrudDAO<T,Id> extends SuperDAO{
    public boolean add(T dto) throws Exception;

    public boolean update(T dto) throws Exception;

    public boolean delete(Id id) throws Exception;

    public T find(Id id) throws Exception;

    public List<T> findAll() throws Exception;
}
