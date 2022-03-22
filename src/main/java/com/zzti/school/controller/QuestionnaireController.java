package com.zzti.school.controller;


import com.github.pagehelper.PageInfo;
import com.zzti.school.entity.Questionnaire;
import com.zzti.school.entity.Subject;
import com.zzti.school.entity.User;
import com.zzti.school.service.QuestionnaireService;
import com.zzti.school.service.SubjectService;
import com.zzti.school.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class QuestionnaireController {


    @Autowired
    private UserService userService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/questionnaire")
    public String questionnaire(Model model,HttpServletRequest request,Integer pageNum) throws ParseException {
        if (pageNum == null){
            pageNum = 1;
        }
        PageInfo<Questionnaire> pageInfo = questionnaireService.getAllQuestionnaires(pageNum,10);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("number", 10 * (pageNum-1));
        List<Questionnaire> questionnaireList = pageInfo.getList();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date));
        List<String> stateList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<questionnaireList.size();i++){
            if (date.before(format.parse(questionnaireList.get(i).getQuestionnaire_end_time())) && date.after(format.parse(questionnaireList.get(i).getQuestionnaire_start_time()))){
                stateList.add("进行中");
            }else {
                stateList.add("已结束");
            }
        }
        model.addAttribute("stateList", stateList);
        for (int j=0;j<questionnaireList.size();j++){
            nameList.add(userService.findNameByID(questionnaireList.get(j).getUser_id()));
        }
        model.addAttribute("nameList", nameList);
        return "questionnaire";
    }

    @GetMapping("/search_q")
    public String search_q(Model model, HttpServletRequest request) throws ParseException {
        String title = request.getParameter("value");
        List<Questionnaire> questionnaireList = questionnaireService.findQuestionnaireByName(title);
        model.addAttribute("questionnaireList", questionnaireList);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date));
        List<String> stateList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<questionnaireList.size();i++){
            if (date.before(format.parse(questionnaireList.get(i).getQuestionnaire_end_time())) && date.after(format.parse(questionnaireList.get(i).getQuestionnaire_start_time()))){
                stateList.add("进行中");
            }else {
                stateList.add("已结束");
            }
        }
        model.addAttribute("stateList", stateList);
        for (int j=0;j<questionnaireList.size();j++){
            nameList.add(userService.findNameByID(questionnaireList.get(j).getUser_id()));
        }
        model.addAttribute("nameList", nameList);
        return "questionnaire";
    }

    @GetMapping("/questionnaire_content")
    public String questionnaire_content(Model model,String id,String title){
        List<Subject> subjectList = subjectService.getAllSubjectsByID(id);
        Map<String,List<String>> optionList = new HashMap<>();
        //题类型列表，1是单选，2是多选
        List<String> typeList = new ArrayList<>();
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("title", title);
        model.addAttribute("questionnaire_id", id);
        for (int i=0;i<subjectList.size();i++){
            List<String> options = new ArrayList<>();
            if (subjectList.get(i).getAnswer_a() != null){
                options.add(subjectList.get(i).getAnswer_a());
            }
            if (subjectList.get(i).getAnswer_b() != null){
                options.add(subjectList.get(i).getAnswer_b());
            }
            if (subjectList.get(i).getAnswer_c() != null){
                options.add(subjectList.get(i).getAnswer_c());
            }
            if (subjectList.get(i).getAnswer_d() != null){
                options.add(subjectList.get(i).getAnswer_d());
            }
            if (subjectList.get(i).getAnswer_e() != null){
                options.add(subjectList.get(i).getAnswer_e());
            }
            if (subjectList.get(i).getAnswer_f() != null){
                options.add(subjectList.get(i).getAnswer_f());
            }
            if (subjectList.get(i).getAnswer_g() != null){
                options.add(subjectList.get(i).getAnswer_g());
            }
            optionList.put(subjectList.get(i).getQuestion_name(), options);
            if (subjectList.get(i).getQuestion_type().equals("1")){
                typeList.add("1");
            }else {
                typeList.add("2");
            }
        }
        model.addAttribute("optionList", optionList);
        System.out.println(typeList);
        model.addAttribute("typeList", typeList);
        return "questionnaire_content";
    }

    @GetMapping("submit_questionnaire")
    public String submit_questionnaire(Model model,HttpServletRequest request) throws ParseException {
        User user = (User) request.getSession().getAttribute("user");
        String user_id = userService.findIDByNum(user.getUser_student_num());
        List<String> cvalueList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        String title = request.getParameter("title");
        String questionnaire_id = request.getParameter("questionnaire_id");
        String cvalue1 = request.getParameter("checkbox_value1");cvalueList.add(cvalue1);
        String cvalue2 = request.getParameter("checkbox_value2");cvalueList.add(cvalue2);
        String cvalue3 = request.getParameter("checkbox_value3");cvalueList.add(cvalue3);
        String cvalue4 = request.getParameter("checkbox_value4");cvalueList.add(cvalue4);
        String cvalue5 = request.getParameter("checkbox_value5");cvalueList.add(cvalue5);
        String cvalue6 = request.getParameter("checkbox_value6");cvalueList.add(cvalue6);
        String cvalue7 = request.getParameter("checkbox_value7");cvalueList.add(cvalue7);
        String cvalue8 = request.getParameter("checkbox_value8");cvalueList.add(cvalue8);
        String cvalue9 = request.getParameter("checkbox_value9");cvalueList.add(cvalue9);
        String cvalue10 = request.getParameter("checkbox_value10");cvalueList.add(cvalue10);
        String cvalue11 = request.getParameter("checkbox_value11");cvalueList.add(cvalue11);
        String cvalue12 = request.getParameter("checkbox_value12");cvalueList.add(cvalue12);
        String cvalue13 = request.getParameter("checkbox_value13");cvalueList.add(cvalue13);
        String cvalue14 = request.getParameter("checkbox_value14");cvalueList.add(cvalue14);
        String cvalue15 = request.getParameter("checkbox_value15");cvalueList.add(cvalue15);
        String value1 = request.getParameter("value1");valueList.add(value1);
        String value2 = request.getParameter("value2");valueList.add(value2);
        String value3 = request.getParameter("value3");valueList.add(value3);
        String value4 = request.getParameter("value4");valueList.add(value4);
        String value5 = request.getParameter("value5");valueList.add(value5);
        String value6 = request.getParameter("value6");valueList.add(value6);
        String value7 = request.getParameter("value7");valueList.add(value7);
        String value8 = request.getParameter("value8");valueList.add(value8);
        String value9 = request.getParameter("value9");valueList.add(value9);
        String value10 = request.getParameter("value10");valueList.add(value10);
        String value11 = request.getParameter("value11");valueList.add(value11);
        String value12 = request.getParameter("value12");valueList.add(value12);
        String value13 = request.getParameter("value13");valueList.add(value13);
        String value14 = request.getParameter("value14");valueList.add(value14);
        String value15 = request.getParameter("value15");valueList.add(value15);
        List<Subject> cSubjectList = subjectService.getCValueByID(questionnaire_id);
        List<Subject> subjectList = subjectService.getValueByID(questionnaire_id);
        for (int i=0;i<cvalueList.size();i++){
            if (cvalueList.get(i).equals("")){
                cvalueList.remove(i);
                i--;
            }
        }
        System.out.println(cvalueList.size());
        for (int i=0;i<valueList.size();i++){
            if (valueList.get(i).equals("undefined")){
                valueList.remove(i);
                i--;
            }
        }
        System.out.println(valueList.size());
        String max_number = subjectService.getMax_number(questionnaire_id);
        if ((cvalueList.size() + valueList.size()) < Integer.parseInt(max_number) || subjectService.getAnswer(questionnaire_id,user_id) != null) {
            List<Subject> subjectList2 = subjectService.getAllSubjectsByID(questionnaire_id);
            Map<String,List<String>> optionList = new HashMap<>();
            //题类型列表，1是单选，2是多选
            List<String> typeList = new ArrayList<>();
            model.addAttribute("subjectList", subjectList2);
            model.addAttribute("title", title);
            model.addAttribute("questionnaire_id", questionnaire_id);
            for (int i=0;i<subjectList2.size();i++){
                List<String> options = new ArrayList<>();
                if (subjectList2.get(i).getAnswer_a() != null){
                    options.add(subjectList2.get(i).getAnswer_a());
                }
                if (subjectList2.get(i).getAnswer_b() != null){
                    options.add(subjectList2.get(i).getAnswer_b());
                }
                if (subjectList2.get(i).getAnswer_c() != null){
                    options.add(subjectList2.get(i).getAnswer_c());
                }
                if (subjectList2.get(i).getAnswer_d() != null){
                    options.add(subjectList2.get(i).getAnswer_d());
                }
                if (subjectList2.get(i).getAnswer_e() != null){
                    options.add(subjectList2.get(i).getAnswer_e());
                }
                if (subjectList2.get(i).getAnswer_f() != null){
                    options.add(subjectList2.get(i).getAnswer_f());
                }
                if (subjectList2.get(i).getAnswer_g() != null){
                    options.add(subjectList2.get(i).getAnswer_g());
                }
                optionList.put(subjectList2.get(i).getQuestion_name(), options);
                if (subjectList2.get(i).getQuestion_type().equals("1")){
                    typeList.add("1");
                }else {
                    typeList.add("2");
                }
            }
            model.addAttribute("optionList", optionList);
            model.addAttribute("typeList", typeList);
            model.addAttribute("errorInfo", "问卷未填写完毕或您已填写该问卷！");
            return "questionnaire_content";
        }
        for (int k=1;k<=Integer.parseInt(max_number);k++){
            subjectService.addAnswer(questionnaire_id,String.valueOf(k),user_id,subjectService.getSubjectID(questionnaire_id,String.valueOf(k)));
        }
        for (int i=0;i<cvalueList.size();i++){
            String[] optionList = cvalueList.get(i).split(",");
            for (int j=0;j<optionList.length;j++){
                //修改答案
                if (optionList[j].equals(cSubjectList.get(i).getAnswer_a())){
                    subjectService.updateAnswer_a("1",questionnaire_id,cSubjectList.get(i).getQuestion_number(),user_id);
                }
                if (optionList[j].equals(cSubjectList.get(i).getAnswer_b())){
                    subjectService.updateAnswer_b("1",questionnaire_id,cSubjectList.get(i).getQuestion_number(),user_id);
                }
                if (optionList[j].equals(cSubjectList.get(i).getAnswer_c())){
                    subjectService.updateAnswer_c("1",questionnaire_id,cSubjectList.get(i).getQuestion_number(),user_id);
                }
                if (optionList[j].equals(cSubjectList.get(i).getAnswer_d())){
                    subjectService.updateAnswer_d("1",questionnaire_id,cSubjectList.get(i).getQuestion_number(),user_id);
                }
                if (optionList[j].equals(cSubjectList.get(i).getAnswer_e())){
                    subjectService.updateAnswer_e("1",questionnaire_id,cSubjectList.get(i).getQuestion_number(),user_id);
                }
                if (optionList[j].equals(cSubjectList.get(i).getAnswer_f())){
                    subjectService.updateAnswer_f("1",questionnaire_id,cSubjectList.get(i).getQuestion_number(),user_id);
                }
                if (optionList[j].equals(cSubjectList.get(i).getAnswer_g())){
                    subjectService.updateAnswer_g("1",questionnaire_id,cSubjectList.get(i).getQuestion_number(),user_id);
                }
            }
        }
        for (int i=0;i<valueList.size();i++){
            if (valueList.get(i).equals(subjectList.get(i).getAnswer_a())){
                subjectService.updateAnswer_a("1",questionnaire_id,subjectList.get(i).getQuestion_number(),user_id);
            }
            if (valueList.get(i).equals(subjectList.get(i).getAnswer_b())){
                subjectService.updateAnswer_b("1",questionnaire_id,subjectList.get(i).getQuestion_number(),user_id);
            }
            if (valueList.get(i).equals(subjectList.get(i).getAnswer_c())){
                subjectService.updateAnswer_c("1",questionnaire_id,subjectList.get(i).getQuestion_number(),user_id);
            }
            if (valueList.get(i).equals(subjectList.get(i).getAnswer_d())){
                subjectService.updateAnswer_d("1",questionnaire_id,subjectList.get(i).getQuestion_number(),user_id);
            }
            if (valueList.get(i).equals(subjectList.get(i).getAnswer_e())){
                subjectService.updateAnswer_e("1",questionnaire_id,subjectList.get(i).getQuestion_number(),user_id);
            }
            if (valueList.get(i).equals(subjectList.get(i).getAnswer_f())){
                subjectService.updateAnswer_f("1",questionnaire_id,subjectList.get(i).getQuestion_number(),user_id);
            }
            if (valueList.get(i).equals(subjectList.get(i).getAnswer_g())){
                subjectService.updateAnswer_g("1",questionnaire_id,subjectList.get(i).getQuestion_number(),user_id);
            }
        }
        PageInfo<Questionnaire> pageInfo = questionnaireService.getAllQuestionnaires(1,10);
        model.addAttribute("number", 0);
        model.addAttribute("pageInfo", pageInfo);
        List<Questionnaire> questionnaireList = pageInfo.getList();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date));
        List<String> stateList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for (int i=0;i<questionnaireList.size();i++){
            if (date.before(format.parse(questionnaireList.get(i).getQuestionnaire_end_time())) && date.after(format.parse(questionnaireList.get(i).getQuestionnaire_start_time()))){
                stateList.add("进行中");
            }else {
                stateList.add("已结束");
            }
        }
        model.addAttribute("stateList", stateList);
        for (int j=0;j<questionnaireList.size();j++){
            nameList.add(userService.findNameByID(questionnaireList.get(j).getUser_id()));
        }
        model.addAttribute("nameList", nameList);
        return "questionnaire";
    }


}
