package com.propel.services;

import com.propel.entity.Browser;
import com.propel.repositories.BrowserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by U0011960 on 8/22/16.
 */
public class BrowserRecordStoreService implements RecordStoreService<Browser> {

    @Autowired
    BrowserRepository browserRepository;

    public BrowserRecordStoreService() {}

    @Override
    public int save(List<Browser> browsers) {
        return browserRepository.save(browsers).size();
    }
}
