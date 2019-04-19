package com.maxlong.study.curator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/19 13:43
 * 类说明:
 */
public interface ClusterLeaderListener {

    public void gainLeader();

    public void loserLeader();
}