package cn.qdd.tlias.service;

import cn.qdd.tlias.pojo.vo.JobOption;
import cn.qdd.tlias.pojo.vo.StudentCountOption;

import java.util.List;
import java.util.Map;

public interface IReportService {
    JobOption getEmpJobData();

    List<Map> getEmpGenderData();

    StudentCountOption getStudentCountData();

    List<Map> getStudentDegreeData();

}
