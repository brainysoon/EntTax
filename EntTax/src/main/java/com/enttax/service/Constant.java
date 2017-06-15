package com.enttax.service;

/**
 * Created by brainy on 17-5-24.
 */
public interface Constant {

    /**
     * 标记 消息已经读
     */
    int MSG_MARK_READ = 1;

    /**
     * 标记  消息未读
     */
    int MSG_MARK_UNREAD = -1;

    /**
     * 假删除的标记  标记死亡
     */
    int PHONY_MARK_DEATH = -1;

    /**
     * 假删除的标记  标记 存活
     */
    int PHONY_MARK_ALIVE = 1;
}
