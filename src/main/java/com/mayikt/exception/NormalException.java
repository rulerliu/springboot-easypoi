package com.mayikt.exception;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/2/28 0028 上午 11:23
 * @version: V1.0
 * @Copyright:该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下， 私自分享视频和源码属于违法行为。
 */
public class NormalException extends Exception {

    /**
     * 无参构造方法
     */
    public NormalException(){
        super();
    }

    /**
     * 有参的构造方法
     * @param msg
     */
    public NormalException(String msg){
        super(msg);

    }

    /**
     * 用指定的详细信息和原因构造一个新的异常
     * @param msg
     * @param cause
     */
    public NormalException(String msg, Throwable cause){
        super(msg,cause);
    }

    /**
     * 用指定原因构造一个新的异常
     * @param cause
     */
    public NormalException(Throwable cause) {
        super(cause);
    }

}
