package com.example.finalexam.dto;

import java.math.BigDecimal;

/**
 * 查询条件 DTO。
 * 所有字段都是可选条件。
 */
public class LostItemQueryDTO {

    private String keyword;
    private String type;
    private String status;
    private BigDecimal maxReward;

    public LostItemQueryDTO() {
    }

    public LostItemQueryDTO(String keyword, String type, String status, BigDecimal maxReward) {
        this.keyword = keyword;
        this.type = type;
        this.status = status;
        this.maxReward = maxReward;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getMaxReward() {
        return maxReward;
    }

    public void setMaxReward(BigDecimal maxReward) {
        this.maxReward = maxReward;
    }
}
