package cn.com.sheep.dao;

import cn.com.sheep.service.SheepTestService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheepTestMapper {

    List<SheepTestService> selectList();
}
