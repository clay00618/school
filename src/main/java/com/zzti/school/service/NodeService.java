package com.zzti.school.service;


import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Node;

import java.util.List;

public interface NodeService {

    //添加帖子
    void addNode(String topic_name, String topic_content, String topic_time, String user_id);

    //获取所有帖子信息
    PageInfo<Node> getAllNodes(int pageNum,int pageSize);

    //根据帖子ID查找帖子
    Node findNodeByID(String node_id);

    // 根据用户ID返回所有帖子
    List<Node> getAllNodesByUID(String user_id);

    //根据搜索内容查找相关话题
    List<Node> findNodesByContent(String topic_name);


}
