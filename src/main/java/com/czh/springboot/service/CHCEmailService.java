package com.czh.springboot.service;

import com.czh.springboot.mapper.CHCRankingListMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("CHCEmailService")
public class CHCEmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    Configuration configuration; // freeMarker configuration

    @Autowired
    CHCRankingListMapper rankingListMapper;

    private static final String DESKTOP = System.getProperty("user.home") + "/Desktop";
    private final static String SEPARATOR = System.getProperty("line.separator");

    public String CHCSendEmail() throws MessagingException {// 发送邮件测试--带附件内容
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("852347470@qq.com");
        helper.setTo("chenzhehao2008666@icloud.com");
        helper.setSubject("主题：CHC统计名单");

        // 模板邮件velocity
        Map<String, Object> model = new HashMap();
        model.put("username", "陈哲浩");
        Template t;
        try {
            t = configuration.getTemplate("template.ftl");
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            helper.setText(content, true);
        } catch (Exception e) {
            e.printStackTrace();
        } // freeMarker template

        StringBuilder sb = new StringBuilder();

        sb.append("CHC考试复赛参加总人数：" + rankingListMapper.queryCHCCount());
        sb.append(SEPARATOR);
        sb.append(SEPARATOR);

        List<Map> mapList0 = rankingListMapper.queryCHCAll();
        sb.append("CHC考试复赛情况统计（全国）");
        sb.append(SEPARATOR);
        for (Map map : mapList0) {
            sb.append(map.get("tongji"));
            sb.append(map.get("num"));
            sb.append(SEPARATOR);
        }
        sb.append(SEPARATOR);


        List<Map> mapList1 = rankingListMapper.queryCHCQaunGuo();
        sb.append("CHC考试复赛情况统计（各省）");
        sb.append(SEPARATOR);
        for (Map map : mapList1) {
            sb.append(map.get("sheng") + ":");
            sb.append(map.get("tongji"));
            sb.append(map.get("num"));
            sb.append(SEPARATOR);
        }
        sb.append(SEPARATOR);


        List<Map> mapList2 = rankingListMapper.queryCHCSheng();
        sb.append("各省份参加人数");
        sb.append(SEPARATOR);
        for (Map map : mapList2) {
            sb.append(map.get("sheng") + ":");
            sb.append(map.get("num"));
            sb.append(SEPARATOR);
        }
        sb.append(SEPARATOR);


        List<Map> mapList3 = rankingListMapper.queryCHCBangdan();
        sb.append("到目前排名统计：").append(SEPARATOR);
        sb.append("姓名\t\t性别\t\t手机号\t\t答对题目数\t\t排名\t\t答题时间\t\t医院\t\t技术职称\t\t行政职务\t\t科室\t\t课程学习数\t\t证书数\t\t省\t\t市\t\t区县\t\t乡镇\n");
        for (Map map : mapList3) {
            sb.append(map.get("doctor_name") + "\t\t");
            sb.append(map.get("sex") + "\t\t");
            sb.append(map.get("correct_no") + "\t\t");
            sb.append(map.get("seq_no") + "\t\t");
            sb.append(map.get("ctime") + "\t\t");
            sb.append(map.get("hospital") + "\t\t");
            sb.append(map.get("title") + "\t\t");
            sb.append(map.get("ctype") + "\t\t");
            sb.append(map.get("department") + "\t\t");
            sb.append(map.get("courseNum") + "\t\t");
            sb.append(map.get("cerNum") + "\t\t");
            sb.append(map.get("province_name") + "\t\t");
            sb.append(map.get("city_name") + "\t\t");
            sb.append(map.get("county_name") + "\t\t");
            sb.append(map.get("town_name") + "\t\t");
            sb.append(SEPARATOR);
        }

        try {
            FileWriter fw = new FileWriter(DESKTOP + "/result.txt");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 发送附件
        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\DELL\\Desktop\\result.txt"));
        helper.addAttachment("附件-1.txt", file);
//        helper.addAttachment("附件-2.txt", file);

//        FileSystemResource pic = new FileSystemResource(new File("/Users/chenzhehao/Desktop/20160712110935064.png"));
        // 嵌入静态资源
//        helper.addInline("weixin", pic);
        mailSender.send(mimeMessage);
        return "success";
    }
}
