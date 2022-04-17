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
public class Question implements Serializable {
    private int question_id;
    private String title;
    private String content;
    private String userid;
    private int focus;
    private String username;
    public Question(Integer questionId,String title, String content,String userid,int focus,String username){
        this.question_id = questionId;
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.focus = focus;
        this.username = username;
    }
    public Question(String title, String content,String userid,int focus,String username){
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.focus = focus;
        this.username = username;
    }
}