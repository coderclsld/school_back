package com.clsld.management.controller;

import com.clsld.api.pojo.Answer;
import com.clsld.api.pojo.Comment;
import com.clsld.api.pojo.Question;
import com.clsld.api.pojo.User;
import com.clsld.management.service.ManageServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowCredentials="true",allowedHeaders="*",origins="*")
@RestController
@RequestMapping("/manage")
public class ManageController {

    @Autowired()
    private ManageServiceFeign manageServiceFeign;

    /*点赞数据*/
    @RequestMapping(value = "/getZangData")
    public List<Answer> getZangData(@RequestParam("page")int page){
        return manageServiceFeign.getAnswerOrderByZang(page);
    }
    @RequestMapping(value = "/getZangDataByQid")
    public List<Answer> getZangDataByQid(@RequestParam("question_id")String question_id,
                                               @RequestParam("page")int page){
        return manageServiceFeign.selectOrderByZangByQid(Integer.parseInt(question_id),page);
    }
    @RequestMapping(value = "/getCount")
    public int getCount(){
        return manageServiceFeign.getCount();
    }
    @RequestMapping(value = "/getCountByQid")
    public int getCountByQid(@RequestParam("question_id")int question_id){
        return manageServiceFeign.getCountByQid(question_id);
    }


    /*访问数据*/
    @RequestMapping(value = "/getAccessData")
    public List<ManageServiceFeign.AccessData> getAccessData(){
        return manageServiceFeign.getAccessData();
    }


    /*用户信息*/
    @RequestMapping(value = "getUserAll")
    public List<User> getUserAll(){
        return manageServiceFeign.getUserAll();
    }

    @RequestMapping(value = "deleteUser")
    public int deleteUser(@RequestParam("userid")String userid){
        return manageServiceFeign.deleteUser(userid);
    }


    /*问题数据*/
    @RequestMapping(value = "/getQuestion")
    public List<Question> getQuestion(){
        return manageServiceFeign.selectQuestionAll();
    }

    @RequestMapping(value = "/deleteQuestion")
    public boolean deleteQuestion(@RequestParam("question_id")int question_id){
        return manageServiceFeign.deleteQuestion(question_id);
    }


    /*回答数据*/
    @RequestMapping(value = "/getAllAnswers")
    public List<Answer> getAllAnswers(){
        return manageServiceFeign.getAllAnswers();
    }

    @RequestMapping(value = "/deleteAnswer")
    public boolean deleteAnswer(@RequestParam("answer_id") int answer_id){
        return manageServiceFeign.deleteAnswer(answer_id);
    }


    /*评论数据*/
    @RequestMapping(value = "/getComment")
    public List<Comment> getComment(@RequestParam("page")int page){
        return manageServiceFeign.selectCommentByLimit(page);
    }

    @RequestMapping(value = "/deleteComment")
    public int deleteComment(@RequestParam("comment_id")int comment_id){
        return manageServiceFeign.deleteComment(comment_id);
    }
}
