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
public class Comment implements Serializable {
    private Integer comment_id;
    private String userid;
    private String username;
    private Integer answer_id;
    private Integer parent_id;
    private String parent_name;
    private String content;
    private String avatar_url;
}