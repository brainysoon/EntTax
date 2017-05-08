package com.enttax.service;

import com.enttax.model.Perms;

import java.util.List;

/**
 * Created by brainy on 17-4-26.
 */
public interface PermsService {

    /**
     * @param rId
     * @return
     */
    List<Perms> selectByRId(String rId);

    /**
     * @param sId
     * @return
     */
    List<Perms> listPermsBySId(String sId);

    /**
     * @param sId
     * @return
     */
    List<String> listPermsNameBySId(String sId);
}
