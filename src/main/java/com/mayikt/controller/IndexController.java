package com.mayikt.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.mayikt.entity.CourseEntity;
import com.mayikt.entity.Person;
import com.mayikt.entity.StudentEntity;
import com.mayikt.entity.TeacherEntity;
import com.mayikt.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/2/28 0028 上午 10:46
 * @version: V1.0
 * @Copyright:该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下， 私自分享视频和源码属于违法行为。
 */
@Controller
public class IndexController {

    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person(1, "路飞","1",new Date());
        Person person2 = new Person(2, "娜美","2", new Date());
        Person person3 = new Person(3, "索隆","1", new Date());
        Person person4 = new Person(4, "小狸猫","1", new Date());
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        ExcelUtils.exportExcel(personList,"花名册","草帽一伙", Person.class,"海贼王.xls", response);
    }

    @RequestMapping("/exportCollection")
    public void exportCollection(HttpServletResponse response) {
        //模拟从数据库获取需要导出的数据
        List<CourseEntity> list = new ArrayList<>();
        TeacherEntity teacherEntity1 = new TeacherEntity("1", "数学老师1");
        List<StudentEntity> studentEntityList1 = new ArrayList<>();
        studentEntityList1.add(new StudentEntity("1", "aaa", 1, new Date(), new Date()));
        studentEntityList1.add(new StudentEntity("2", "bbb", 2, new Date(), new Date()));
        CourseEntity courseEntity1 = new CourseEntity("1", "路飞", teacherEntity1, studentEntityList1, "static/img/girl.jpg");

        TeacherEntity teacherEntity2 = new TeacherEntity("1", "数学老师1");
        List<StudentEntity> studentEntityList2 = new ArrayList<>();
        studentEntityList2.add(new StudentEntity("1", "ccc", 1, new Date(), new Date()));
        studentEntityList2.add(new StudentEntity("2", "ddd", 2, new Date(), new Date()));
        CourseEntity courseEntity2 = new CourseEntity("1", "美娜", teacherEntity2, studentEntityList2, "static/img/girl.jpg");
        list.add(courseEntity1);
        list.add(courseEntity2);

        //导出操作
        ExcelUtils.exportExcel(list,"花名册","草帽一伙", CourseEntity.class,"海贼王2.xls", response);
    }

    @RequestMapping("/bigDataExport")
    public void bigDataExport() throws Exception {
        List<CourseEntity> list = new ArrayList<>();
        Workbook workbook = null;
        Date start = new Date();
        ExportParams params = new ExportParams("大数据测试", "测试");
        for (int i = 0; i < 1000000; i++) {  //一百万数据量
            TeacherEntity teacherEntity1 = new TeacherEntity("1", "数学老师1");
            List<StudentEntity> studentEntityList1 = new ArrayList<>();
            studentEntityList1.add(new StudentEntity("1", "aaa", 1, new Date(), new Date()));
            studentEntityList1.add(new StudentEntity("2", "bbb", 2, new Date(), new Date()));
            CourseEntity courseEntity1 = new CourseEntity("1", "路飞", teacherEntity1, studentEntityList1, "static/img/girl.jpg");

            list.add(courseEntity1);
            if(list.size() == 10000){
                workbook = ExcelExportUtil.exportBigExcel(params, CourseEntity.class, list);
                list.clear();
            }
        }
        ExcelExportUtil.closeExportBigExcel();
        System.out.println("耗时：" + (new Date().getTime() - start.getTime()));
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/ExcelExportBigData.bigDataExport.xlsx");
        workbook.write(fos);
        fos.close();
    }

    @RequestMapping("/import")
    @ResponseBody
    public String importExcel() {
        String filePath = "static/excel/海贼王.xls";
        Resource resource = new ClassPathResource(filePath);
        File file = null;
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //解析excel，
        List<Person> personList = ExcelUtils.importExcel(file,1,1, Person.class);
        //也可以使用MultipartFile,使用 ExcelUtils.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入

        //TODO 保存数据库
        for(Person person: personList) {
            System.out.println(person);
        }

        System.out.println("导入数据一共【" + personList.size() + "】行");
        return "success";
    }

}
