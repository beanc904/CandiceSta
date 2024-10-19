package com.beanc.util;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Profiles implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;



    //表单布局形式
    private String tableOverlooking;
    public String getTableOverlooking() {
        return tableOverlooking;
    }
    public void setTableOverlooking(String tableOverlooking) {
        this.tableOverlooking = tableOverlooking;
    }



    //日志显示
    private String logOverlooking;
    public String getLogOverlooking() {
        return logOverlooking;
    }
    public void setLogOverlooking(String logOverlooking) {
        this.logOverlooking = logOverlooking;
    }



    //消费体活和罚除体活的单值
    private Double rewardValue;
    private Double punishValue;
    public Double getRewardValue() {
        return rewardValue;
    }
    public Double getPunishValue() {
        return punishValue;
    }
    public void setRewardValue(Double rewardValue) {
        this.rewardValue = rewardValue;
    }
    public void setPunishValue(Double punishValue) {
        this.punishValue = punishValue;
    }



    //主题配置
    private String theme;
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }



    //管理员密码
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    //两个操作员密码
    private String member1password;
    private String member2password;
    public String getMember1password() {
        return member1password;
    }
    public String getMember2password() {
        return member2password;
    }
    public void setMember1password(String member1password) {
        this.member1password = member1password;
    }
    public void setMember2password(String member2password) {
        this.member2password = member2password;
    }

    private List<String> dorm;
    public List<String> getDorm() {
        return dorm;
    }
    public void setDorm(List<String> dorm) {
        this.dorm = dorm;
    }

    public Profiles(String tableOverlooking,
                    String logOverlooking,
                    Double rewardValue,
                    Double punishValue,
                    String theme,
                    String password,
                    String member1password,
                    String member2password,
                    List<String> dorm) {
        this.tableOverlooking = tableOverlooking;
        this.logOverlooking = logOverlooking;
        this.rewardValue = rewardValue;
        this.punishValue = punishValue;
        this.theme = theme;
        this.password = password;
        this.member1password = member1password;
        this.member2password = member2password;
        this.dorm = dorm;
    }
}
