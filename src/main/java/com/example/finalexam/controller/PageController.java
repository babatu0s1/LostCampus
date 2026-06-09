package com.example.finalexam.controller;

import com.example.finalexam.component.StudentProfile;
import com.example.finalexam.dto.LostItemQueryDTO;
import com.example.finalexam.service.LostItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 页面 Controller。
 * 返回 Thymeleaf 模板，不返回 JSON。
 */
@Controller
public class PageController {

    private final LostItemService lostItemService;
    private final StudentProfile studentProfile;

    public PageController(LostItemService lostItemService, StudentProfile studentProfile) {
        this.lostItemService = lostItemService;
        this.studentProfile = studentProfile;
    }

    @GetMapping({"/", "/items"})
    public String itemsPage(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String maxReward,
            Model model
    ) {
        BigDecimal maxRewardValue = parseBigDecimal(maxReward);

        LostItemQueryDTO queryDTO = new LostItemQueryDTO(keyword, type, status, maxRewardValue);

        model.addAttribute("items", lostItemService.search(queryDTO));
        model.addAttribute("query", queryDTO);
        model.addAttribute("stats", lostItemService.stats());
        model.addAttribute("studentName", studentProfile.getName());
        model.addAttribute("studentNo", studentProfile.getNo());
        model.addAttribute("studentLabel", studentProfile.getLabel());

        return "items";
    }

    private BigDecimal parseBigDecimal(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }
        return new BigDecimal(text.trim());
    }
}
