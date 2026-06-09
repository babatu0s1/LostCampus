package com.example.finalexam.service;

import com.example.finalexam.component.StudentProfile;
import com.example.finalexam.dto.LostItemCreateDTO;
import com.example.finalexam.dto.LostItemQueryDTO;
import com.example.finalexam.entity.LostItem;
import com.example.finalexam.exception.BusinessException;
import com.example.finalexam.mapper.LostItemMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务层。
 * Controller 不直接操作数据库，而是调用 Service。
 */
@Service
public class LostItemService {

    private final LostItemMapper lostItemMapper;
    private final StudentProfile studentProfile;

    public LostItemService(LostItemMapper lostItemMapper, StudentProfile studentProfile) {
        this.lostItemMapper = lostItemMapper;
        this.studentProfile = studentProfile;
    }

    public List<LostItem> search(LostItemQueryDTO queryDTO) {
        return lostItemMapper.search(queryDTO);
    }

    public LostItem create(LostItemCreateDTO createDTO) {
        validateCreateDTO(createDTO);

        LostItem lostItem = new LostItem();
        lostItem.setTitle(createDTO.getTitle().trim());
        lostItem.setType(createDTO.getType().trim());
        lostItem.setLocation(createDTO.getLocation().trim());
        lostItem.setContact(createDTO.getContact().trim());
        lostItem.setStatus("寻找中");
        lostItem.setOwnerName(studentProfile.getLabel());

        if (createDTO.getReward() == null) {
            lostItem.setReward(BigDecimal.ZERO);
        } else {
            lostItem.setReward(createDTO.getReward());
        }

        lostItemMapper.insert(lostItem);

        return lostItemMapper.findById(lostItem.getId());
    }

    public void markFound(Integer id) {
        LostItem item = lostItemMapper.findById(id);
        if (item == null) {
            throw new BusinessException("编号为 " + id + " 的失物记录不存在，无法标记为已找回。");
        }

        lostItemMapper.updateStatus(id, "已找回");
    }

    public void delete(Integer id) {
        LostItem item = lostItemMapper.findById(id);
        if (item == null) {
            throw new BusinessException("编号为 " + id + " 的失物记录不存在，无法删除。");
        }

        lostItemMapper.deleteById(id);
    }

    public Map<String, Object> stats() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total", lostItemMapper.countByStatus(null));
        result.put("looking", lostItemMapper.countByStatus("寻找中"));
        result.put("found", lostItemMapper.countByStatus("已找回"));
        return result;
    }

    private void validateCreateDTO(LostItemCreateDTO createDTO) {
        if (createDTO == null) {
            throw new BusinessException("请求体不能为空，请提交 JSON 数据。");
        }

        if (isBlank(createDTO.getTitle())) {
            throw new BusinessException("物品标题不能为空。");
        }

        if (isBlank(createDTO.getType())) {
            throw new BusinessException("物品类型不能为空。");
        }

        if (isBlank(createDTO.getLocation())) {
            throw new BusinessException("地点不能为空。");
        }

        if (isBlank(createDTO.getContact())) {
            throw new BusinessException("联系方式不能为空。");
        }

        if (createDTO.getReward() != null && createDTO.getReward().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("悬赏金额不能为负数。");
        }

        String title = createDTO.getTitle();
        if (title.contains("代考") || title.contains("外挂") || title.contains("刷单")) {
            throw new BusinessException("请不要发布违规信息：" + title);
        }
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
