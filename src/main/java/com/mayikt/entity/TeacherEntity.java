package com.mayikt.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget("teacherEntity")
public class TeacherEntity implements java.io.Serializable {

    private String id;

    /**
     * name
     */
    @Excel(name = "主讲老师_major,代课老师_absent", width = 25, orderNum = "1", needMerge = true, isImportField = "true_major,true_absent")
    private String name;
}