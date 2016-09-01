package com.propel.services;

import java.util.List;

/**
 * Created by U0011960 on 8/24/16.
 */
public interface RecordStoreService<T> {

    int save(List<T> records);

}
