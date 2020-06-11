package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.ISystemDictionary;
import org.hibernate.SessionFactory;

public class SystemDictionaryDao implements ISystemDictionary {
    private final SessionFactory factory;

    public SystemDictionaryDao(SessionFactory factory) {
        this.factory = factory;
    }

}
