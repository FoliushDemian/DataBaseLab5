/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: rostyk
 * Package name: iot.lviv.ua.rostyk.service
 * Interface: GeneralService
 */

package com.example.database5.service;

import java.util.List;

public interface GeneralService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T create(T entity);

    void update(ID id, T entity);

    void delete(ID id);
}
