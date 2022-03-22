package com.zzti.school.service;

import com.zzti.school.entity.Report;

import java.util.List;

public interface ReportService {

    //添加建议和举报信息
    void addReport(String user_id, String kind, String j_time, String j_content);

    //根据用户ID获取所有举报和建议信息
    List<Report> getAllReportsByUID(String user_id);
}
