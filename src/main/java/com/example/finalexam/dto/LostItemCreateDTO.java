package com.example.finalexam.dto;

import java.math.BigDecimal;

/**
 * 新增失物信息 DTO。
 * 前端通过 JSON 请求体提交这些字段。
 */
public class LostItemCreateDTO {

    private String title;
    private String type;
    private String location;
    private String contact;
    private BigDecimal reward;

    public LostItemCreateDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }
}
