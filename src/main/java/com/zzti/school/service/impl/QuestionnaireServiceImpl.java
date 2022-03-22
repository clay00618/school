package com.zzti.school.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Questionnaire;
import com.zzti.school.entity.User;
import com.zzti.school.mapper.QuestionnaireMapper;
import com.zzti.school.mapper.UserMapper;
import com.zzti.school.service.QuestionnaireService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;


    @Override
    public PageInfo<Questionnaire> getAllQuestionnaires(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Questionnaire> questionnaireList0 = questionnaireMapper.getAllQuestionnaires();
        PageInfo<Questionnaire> pageInfo = new PageInfo<>(questionnaireList0);
        PageInfo<Questionnaire> target = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,target);
        List<Questionnaire> questionnaireList = new ArrayList<>(pageInfo.getList());
        List<Questionnaire> questionnaireList1 = new ArrayList<>();
        List<Integer> typeList = new ArrayList<>();
        for (int i=0;i<pageInfo.getList().size();i++){
            //创建者ID列表
            typeList.add(userMapper.findTypeByID(questionnaireList.get(i).getUser_id()));
        }
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userMapper.findIDByNum(user.getUser_student_num());
        for (int j=0;j<typeList.size();j++){
            //班级管理员
            if (typeList.get(j)==2){
                //登录用户权限范围
                String user_state = userMapper.findStateByID(user_id);
                String manager_state = userMapper.findStateByID(questionnaireList.get(j).getUser_id());
                if (user_state.equals(manager_state)){
                    questionnaireList1.add(questionnaireList.get(j));
                }
            }
            //院级管理员
            if (typeList.get(j)==3){
                String user_state = userMapper.findStateByID(user_id);
                String manager_state = userMapper.findStateByID(questionnaireList.get(j).getUser_id());
                String sub__user_state = user_state.substring(4, 6);
                String sub_manager_state = manager_state.substring(4, 6);
                if (sub__user_state.equals(sub_manager_state)){
                    questionnaireList1.add(questionnaireList.get(j));
                }
            }
            //校级管理员
            if (typeList.get(j)==5){
                questionnaireList1.add(questionnaireList.get(j));
            }
        }
        target.setList(questionnaireList1);
        return target;
    }

    @Override
    public List<Questionnaire> findQuestionnaireByName(String questionnaire_name) {
        return questionnaireMapper.findQuestionnaireByName(questionnaire_name);
    }

    @Override
    public String findIDByTitle(String questionnaire_name) {
        return questionnaireMapper.findIDByTitle(questionnaire_name);
    }
}
