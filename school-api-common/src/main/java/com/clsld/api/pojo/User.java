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
public class User implements Serializable {
    private String userid;
    private String nickname;
    private String genderclass;
    private Integer gender;
    private Integer studentNum;
    private String avatarUrl;
}