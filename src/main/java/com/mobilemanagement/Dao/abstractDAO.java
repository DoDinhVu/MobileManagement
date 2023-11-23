/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import java.util.List;

/**
 *
 * @author DINHVU
 */
public abstract class abstractDAO<entityType, KeyType> {

    public abstract void insert(entityType entity);

    public abstract void update(entityType entity);

    public abstract void delete(KeyType id);

    public abstract List<entityType> selectAll();

    public abstract entityType selectById(KeyType id);

    public abstract List<entityType> selectBySql(String sql, Object... args);
}
