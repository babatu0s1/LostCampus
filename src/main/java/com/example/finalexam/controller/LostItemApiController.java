package com.example.finalexam.controller;

import com.example.finalexam.dto.LostItemCreateDTO;
import com.example.finalexam.dto.LostItemQueryDTO;
import com.example.finalexam.entity.LostItem;
import com.example.finalexam.service.LostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;




/**
 * JSON API Controller。
 * 用于前端 fetch 和 IDEA HTTP Client 测试。
 */
@RestController
@RequestMapping("/api/lost-items")
public class LostItemApiController {

    private final LostItemService lostItemService;

    public LostItemApiController(LostItemService lostItemService) {
        this.lostItemService = lostItemService;
    }

    @GetMapping
    public List<LostItem> search(LostItemQueryDTO queryDTO) {
        return lostItemService.search(queryDTO);
    }

    @GetMapping("/stats")
    public Map<String, Object> stats() {
        return lostItemService.stats();
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody LostItemCreateDTO createDTO) {
        LostItem item = lostItemService.create(createDTO);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("code", 200);
        result.put("message", "新增成功");
        result.put("item", item);
        return result;
    }

    @PutMapping("/{id}/found")
    public Map<String, Object> markFound(@PathVariable Integer id) {
        lostItemService.markFound(id);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("code", 200);
        result.put("message", "已将编号 " + id + " 标记为已找回");
        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        lostItemService.delete(id);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("code", 200);
        result.put("message", "已删除编号 " + id + " 的记录");
        return result;
    }

    /**
     * 故意制造异常，用来测试全局异常处理和 AOP finally 是否能打印耗时。
     */


    }


