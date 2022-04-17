package com.clsld.consumer.controller;

import com.clsld.api.pojo.Answer;
import com.clsld.api.pojo.Comment;
import com.clsld.api.pojo.Question;
import com.clsld.api.pojo.User;
import com.clsld.api.util.CommentNode;
import com.clsld.consumer.Service.CommunityServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityServiceFeign communityServiceFeign;

    /*-----------------------------问题-------------------------------------*/
    @RequestMapping("/getQuestion")
    List<Question> getQuestion(){
        return communityServiceFeign.getQuestion();
    }

    @RequestMapping(value = "/selectQuestion")
    public Question selectQuestionById(@RequestParam("question_id") int question_id){
        return communityServiceFeign.selectQuestionById(question_id);
    }

    @RequestMapping(value = "/addfocus")
    public int addfocus(@RequestParam("question_id") int question_id){
        return communityServiceFeign.addfocus(question_id);
    }

    @RequestMapping(value = "/cutfocus")
    public int cutfocus(@RequestParam("question_id") int question_id){
        return communityServiceFeign.cutfocus(question_id);
    }

    @RequestMapping(value = "/addQuestion")
    public boolean addQuestion(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("userid") String userid, @RequestParam("username") String username){
        return communityServiceFeign.addQuestion(title,content,userid,username);
    }

    /*--------------------------回答----------------------------------------*/

    @RequestMapping(value = "/addAnswer")
    public boolean addAnswer(@RequestParam("answer") String answer, @RequestParam("userid") String userid,
                             @RequestParam("question_id") int question_id, @RequestParam("username") String username,
                             @RequestParam("avatar_url")String avatar_url){
        return communityServiceFeign.addAnswer(answer,userid,question_id,username,avatar_url);
    }

    @RequestMapping(value = "/deleteAnswer")
    public boolean deleteAnswer(@RequestParam("answer_id") int answer_id){
        return communityServiceFeign.deleteAnswer(answer_id);
    }

    @RequestMapping(value = "/getAllAnswers")
    public List<Answer> getAllAnswers(){
        return getAllAnswers();
    }

    @RequestMapping(value = "/getAnswerByQid")
    List<Answer> getAnswerByQid(@RequestParam("question_id") int question_id){
        return communityServiceFeign.getAnswerByQId(question_id);
    }

    @RequestMapping(value = "/getAnswerById")
    Answer getAnswerByAId(@RequestParam("answer_id") int answer_id){
        return communityServiceFeign.getAnswerById(answer_id);
    }

    @RequestMapping(value = "/addZang")
    public int addZang(@RequestParam("answer_id")int answer_id){
        return communityServiceFeign.addZang(answer_id);
    }

    @RequestMapping(value = "/cutZang")
    public int cutZang(@RequestParam("answer_id") int answer_id){
        return communityServiceFeign.cutZang(answer_id);
    }

    /*---------------------------评论------------------------------------*/
    @RequestMapping(value = "/getCommentByAnswerId")
    public List<CommentNode> getCommentByAnswerId(@RequestParam("answerId") int answer_id){
        return communityServiceFeign.getCommentByAnswerId(answer_id);
    }

    @RequestMapping(value = "/addComment")
    public boolean addComment(@RequestParam("userid")String userid,@RequestParam("parent_id")Integer parent_id,
                              @RequestParam("content")String content,@RequestParam("answer_id")Integer answer_id,
                              @RequestParam("parent_name")String parent_name, @RequestParam("username")String username,
                              @RequestParam("avatar_url")String avatar_url){
        return communityServiceFeign.addComment(userid,parent_id,content,answer_id,parent_name,username,avatar_url);
    }

    /*---------------------------用户-------------------------------------*/
    @RequestMapping(value = "/getUserById")
    public User getUserByid(@RequestParam("userid")String userid){
        return communityServiceFeign.getUserByid(userid);
    }


    @RequestMapping(value = "/addUser")
    public int addUser(@RequestParam("nickname") String nickname,
                       @RequestParam("avatarurl") String avatarurl){ return communityServiceFeign.addUser(nickname,avatarurl); }

    @RequestMapping(value = "/deleteUser")
    public int deleteUser(@RequestParam("userid") String userid){return communityServiceFeign.deleteUser(userid);}


}