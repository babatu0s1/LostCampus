package com.example.finalexam.mapper;

import com.example.finalexam.dto.LostItemQueryDTO;
import com.example.finalexam.entity.LostItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LostItemMapper {

    List<LostItem> search(LostItemQueryDTO queryDTO);

    LostItem findById(@Param("id") Integer id);

    int insert(LostItem lostItem);

    int updateStatus(@Param("id") Integer id, @Param("status") String status);

    int deleteById(@Param("id") Integer id);

    int countByStatus(@Param("status") String status);
}
