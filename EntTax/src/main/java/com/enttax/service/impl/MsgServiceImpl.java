package com.enttax.service.impl;

import com.enttax.dao.MsgMapper;
import com.enttax.model.Msg;
import com.enttax.service.Constant;
import com.enttax.service.MsgService;
import com.enttax.util.tools.ToolRandoms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by brainy on 17-5-24.
 */
@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgMapper msgMapper;

    @Override
    public List<Msg> getMsgByToSId(String toSId) {
        return msgMapper.selectByToSId(toSId);
    }

    @Override
    public int markReadByMIds(String[] mIds) {

        //将所有的消息 都不标记位已读
        for (String mId : mIds) {

            msgMapper.markReadByMId(mId);
        }

        return mIds.length;
    }

    @Override
    public int deleteByMIds(String[] mIds) {

        //挨个删除
        for (String mId : mIds) {

            msgMapper.phonyDeleteByPrimaryKey(mId);
        }

        return mIds.length;
    }

    @Override
    public int sendMsg(String toSId, String mContent, String fromSId) {

        //初始化数据
        Msg msg = new Msg();

        msg.setMId(ToolRandoms.randomId20());
        msg.setMMark(Constant.PHONY_MARK_ALIVE);
        msg.setMRead(Constant.MSG_MARK_UNREAD);
        msg.setMDate(new Date());

        msg.setFromSId(fromSId);
        msg.setToSId(toSId);
        msg.setMContent(mContent);

        return msgMapper.insert(msg);
    }
}
