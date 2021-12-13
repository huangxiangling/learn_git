package cn.com.sheep.workspace.dao;


import org.springframework.stereotype.Repository;

@Repository
public interface HelloMapper {

    String selectOneAccountName();
}
