package com.zzti.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Article;
import com.zzti.school.entity.Node;
import com.zzti.school.mapper.NodeMapper;
import com.zzti.school.mapper.UserMapper;
import com.zzti.school.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;



    @Override
    public void addNode(String topic_name, String topic_content, String topic_time, String user_id) {
        nodeMapper.addNode(topic_name,topic_content,topic_time,user_id);
    }

    @Override
    public PageInfo<Node> getAllNodes(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Node> nodeList = nodeMapper.getAllNodes();
        PageInfo<Node> pageInfo = new PageInfo<>(nodeList);
        return pageInfo;
    }


    @Override
    public Node findNodeByID(String node_id) {
        return nodeMapper.findNodeByID(node_id);

    }

    @Override
    public List<Node> getAllNodesByUID(String user_id) {
        return nodeMapper.getAllNodesByUID(user_id);
    }

    @Override
    public List<Node> findNodesByContent(String topic_name) {
        return nodeMapper.findNodesByContent(topic_name);
    }

}
