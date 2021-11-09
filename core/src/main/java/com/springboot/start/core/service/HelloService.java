package com.springboot.start.core.service;


/**
 * @author FireWang
 * @date 2020/11/08
 */
public interface HelloService {
    /**
     * 查询demo
     *
     * @return
     */
    String sayhello();

    /**
     * 插入demo
     *
     * @return
     */
    String addhello();

    /**
     * 删除数据
     *
     * @return
     */
    String deletehello();
}
