package code.hub.ed.team1.service.api;

public interface GeneralService<T, K> {
    T create(T t);

    T read(K id);

    T update(T t);

    void delete(K id);
}
