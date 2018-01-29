package hornsandhooves.hornsandhooves.dao;

import hornsandhooves.hornsandhooves.model.PersistentModel;

public interface IDao<T extends PersistentModel> {

    void save(T model);

    void update(T model);

    void delete(T model);

    T get(long id);

}
