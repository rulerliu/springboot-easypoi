package com.mayikt.controller;

import com.mayikt.entity.Person;
import com.mayikt.util.ExcelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/import")
    @ResponseBody
    public String importExcel() {
        String filePath = "D:\\海贼王.xls";
        //解析excel，
        List<Person> personList = ExcelUtils.importExcel(filePath,1,1, Person.class);
        //也可以使用MultipartFile,使用 ExcelUtils.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入

        //TODO 保存数据库
        for(Person person: personList) {
            System.out.println(person);
        }

        System.out.println("导入数据一共【" + personList.size() + "】行");
        return "success";
    }

}
