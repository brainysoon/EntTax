package com.enttax.dao;

import com.enttax.model.Msg;
import com.enttax.vo.MsgInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by brainy on 17-5-24.
 */
public interface MsgMapper {

    /**
     * @param mId
     * @return
     */
    int deleteByPrimaryKey(String mId);

    /**
     * @param msg
     * @return
     */
    int insert(Msg msg);

    /**
     * @param msg
     * @return
     */
    int updateByPrimaryKey(Msg msg);

    /**
     * @param mId
     * @return
     */
    Msg selectByPrimaryKey(String mId);

    /**
     * @return
     */
    List<Msg> selectAll();

    /**
     * @param toSId
     * @return
     */
    List<MsgInfo> selectByToSId(String toSId);

    /**
     * @param fromSId
     * @return
     */
    List<Msg> selectByFromSId(String fromSId);

    /**
     * @param toSId
     * @param mRead
     * @return
     */
    List<MsgInfo> selectByToSIdAndMRead(@Param("toSId") String toSId, @Param("mRead") Integer mRead);

    /**
     * @param mId
     * @return
     */
    int markReadByMId(@Param("mId") String mId);

    /**
     * @param mId
     * @return
     */
    int markUnReadByMId(@Param("mId") String mId);

    /**
     * 假删除  只是将标记值设置位 -1
     *
     * @param mId
     * @return
     */
    int phonyDeleteByPrimaryKey(String mId);

    /**
     * @param toSId
     * @return
     */
    int deleteByToSId(String toSId);
}
