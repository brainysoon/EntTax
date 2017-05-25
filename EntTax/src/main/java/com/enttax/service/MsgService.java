package com.enttax.service;

import com.enttax.model.Msg;

import java.util.List;

/**
 * Created by brainy on 17-5-24.
 */
public interface MsgService {

    /**
     * 通过员工id 找到这个员工 对应的消息
     *
     * @param toSId
     * @return
     */
    List<Msg> getMsgByToSId(String toSId);

    /**
     * @param mIds
     * @return
     */
    int markReadByMIds(String[] mIds);

    /**
     * @param mIds
     * @return
     */
    int deleteByMIds(String[] mIds);

    /**
     * @param toSId
     * @param mContent
     * @return
     */
    int sendMsg(String toSId, String mContent, String fromSId);
}
