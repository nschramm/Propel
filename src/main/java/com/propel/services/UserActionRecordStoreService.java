package com.propel.services;

import com.propel.entity.UserAction;
import com.propel.repositories.UserActionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by U0011960 on 8/23/16.
 */
public class UserActionRecordStoreService implements RecordStoreService<UserAction> {

    @Autowired
    UserActionRepository userActionRepository;

    public UserActionRecordStoreService() {}

    @Override
    public int save(List<UserAction> userActions) {
        return userActionRepository.save(userActions).size();
    }
}
