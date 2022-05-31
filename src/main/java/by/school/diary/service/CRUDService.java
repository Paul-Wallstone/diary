package by.school.diary.service;


import java.util.List;

public interface CRUDService<T, R> {
    T getById(Long id);

    List<T> getAll();

    T save(R dto);

    T update(R dto, Long id);

    T update(R dto);

    void deleteById(Long id);

}
