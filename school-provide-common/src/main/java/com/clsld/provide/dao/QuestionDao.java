package com.clsld.provide.dao;

import com.clsld.api.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface QuestionDao {
    int deleteByPrimaryKey(Integer questionId);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer questionId);

    List<Question> selectAll();

    /*问题查询分页*/
    List<Question> selectByLimit(Integer question_id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    int addfocus(Integer questionId);

    int getfocus(Integer questionId);

    int cutfocus(Integer questionId);
}