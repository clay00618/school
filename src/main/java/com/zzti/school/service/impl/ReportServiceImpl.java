package com.zzti.school.service.impl;

import com.zzti.school.entity.Report;
import com.zzti.school.mapper.ReportMapper;
import com.zzti.school.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;


    @Override
    public void addReport(String user_id, String kind, String j_time, String j_content) {
        reportMapper.addReport(user_id,kind,j_time,j_content);
    }

    @Override
    public List<Report> getAllReportsByUID(String user_id) {
        return reportMapper.getAllReportsByUID(user_id);
    }
}
