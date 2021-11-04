package cn.com.sheep.service.impl;

import cn.com.sheep.dao.SheepTestMapper;
import cn.com.sheep.service.SheepTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SheepTestServiceImpl implements SheepTestService {

    @Autowired
    private SheepTestMapper sheepTestMapper;

    @Override
    public List<SheepTestService> selectList() {
        return sheepTestMapper.selectList();
    }
}


