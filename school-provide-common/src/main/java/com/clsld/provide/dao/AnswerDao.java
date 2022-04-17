package com.clsld.provide.dao;

import com.clsld.api.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerDao {
    int deleteByPrimaryKey(Integer answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer answerId);

    List<Answer> selectAll();

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);

    List<Answer> selectAnswerByQid(Integer questionId);

    int addZang(Integer answerId);

    int getZang(Integer answerId);

    int cutZang(Integer answerId);

    List<Answer> selectGyZang(Integer page);

    List<Answer> selectGyZangByQid(@Param("question_id")Integer question_id, @Param("page")Integer page);

    int getCount();

    int getCountByQid(Integer question_id);

}