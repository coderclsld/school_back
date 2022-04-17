package com.clsld.api.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * answer
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer implements Serializable {
    private int answer_id;
    private String answer;
    private int zang;
    private String userid;
    private int question_id;
    private String username;
    private String avatar_url;

    public Answer(String answer,int zang,String userid,int questionId,String username,String avatarUrl){
        this.answer = answer;
        this.zang = zang;
        this.username = username;
        this. userid = userid;
        this.question_id = questionId;
        this.avatar_url = avatarUrl;
    }
    public Answer(String answer,int zang,String userid,int questionId,String username){
        this.answer = answer;
        this.zang = zang;
        this.username = username;
        this. userid = userid;
        this.question_id = questionId;
    }
}