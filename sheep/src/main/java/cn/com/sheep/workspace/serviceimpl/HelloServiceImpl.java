package cn.com.sheep.workspace.serviceimpl;

import cn.com.sheep.workspace.dao.HelloMapper;
import cn.com.sheep.workspace.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private HelloMapper helloMapper;

    @Override
    public String hello(String str) {

        String accountName = helloMapper.selectOneAccountName();
        return accountName + str;
    }
}
