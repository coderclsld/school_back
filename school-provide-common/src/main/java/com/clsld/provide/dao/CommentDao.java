package com.clsld.provide.dao;

import com.clsld.api.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectAll();

    List<Comment> selectByLimit(@Param("page") Integer page);

    List<Comment> selectByAnswerId(Integer answerId);


    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}