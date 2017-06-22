package com.enttax.service;

import com.enttax.vo.MsgInfo;

import java.util.List;

/**
 * Created by brainy on 17-5-24.
 */
public interface MsgService {

    /**
     * @param toSId
     * @param mRead
     * @return
     */
    List<MsgInfo> getMsgByToSIdAndMRead(String toSId, Integer mRead);

    /**
     * @param toSId
     * @return
     */
    List<MsgInfo> getMsgByToSId(String toSId);

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

    /**
     * @param toSId
     * @return
     */
    int deleteByToSId(String toSId);
}
