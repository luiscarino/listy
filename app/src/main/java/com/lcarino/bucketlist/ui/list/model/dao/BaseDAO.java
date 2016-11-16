package com.lcarino.bucketlist.ui.list.model.dao;

import com.google.firebase.database.ValueEventListener;

/**
 * @author Luis Carino.
 */

public interface BaseDAO<T> {

    void findAll(ValueEventListener listener);

    void findById(String id, ValueEventListener listener);

    void insertInspiration(T inspiration, ValueEventListener listener);

    void updateInspiration(String id, ValueEventListener listener);

    void deleteInspiration(String id, ValueEventListener listener);
}