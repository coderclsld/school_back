package com.clsld.consumer.Service;

import com.clsld.api.pojo.Answer;
import com.clsld.api.pojo.Question;
import com.clsld.api.pojo.User;
import com.clsld.api.util.CommentNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "school-provide-common")
public interface CommunityServiceFeign {

    /*----------------------问题--------------------*/
    /*增加问题*/
    @RequestMapping(value = "/question/addQuestion")
    public boolean addQuestion(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("userid") String userid, @RequestParam("username") String username);

    /*删除一条问题*/
    @RequestMapping(value = "/question/deleteQuestion")
    public boolean deleteQuestion(@RequestParam("question_id") int question_id);

    /*得到所有问题，后续需要改为分表模式*/
    @RequestMapping(value = "/question/getQuestion")
    public List<Question> getQuestion();

    /*查询一条问题，目前想不出有什么用先放着*/
    @RequestMapping(value = "/question/selectQuestion")
    public Question selectQuestionById(@RequestParam("question_id") int question_id);

    /*增加关注数*/
    @RequestMapping(value = "/question/addfocus")
    public int addfocus(@RequestParam("question_id") int question_id);

    /*减少关注数*/
    @RequestMapping(value = "/question/cutfocus")
    public int cutfocus(@RequestParam("question_id") int question_id);


    /*---------------------- 评论--------------------*/

    @RequestMapping(value = "/comment/getCommentByAnswerId")
    public List<CommentNode> getCommentByAnswerId(@RequestParam("answerId") int answer_id);

    @RequestMapping(value = "/comment/addComment")
    public boolean addComment(@RequestParam("userid") String userid, @RequestParam("parent_id") Integer parent_id,
                              @RequestParam("content") String content, @RequestParam("answer_id") Integer answer_id,
                              @RequestParam("parent_name") String parent_name, @RequestParam("username") String username,
                              @RequestParam("avatar_url") String avatar_url);

    /*--------------------- 回答----------------*/
    /*添加回答*/
    @RequestMapping(value = "/answer/addAnswer")
    public boolean addAnswer(@RequestParam("answer") String answer, @RequestParam("userid") String userid,
                             @RequestParam("question_id") int question_id, @RequestParam("username") String username,
                             @RequestParam("avatar_url")String avatar_url);

    /*删除回答*/
    @RequestMapping(value = "/answer/deleteAnswer")
    public boolean deleteAnswer(@RequestParam("answer_id") int answer_id);

    /*获得所有回答*/
    @RequestMapping(value = "/answer/getAllAnswers")
    public List<Answer> getAllAnswers();

    /*获得属于特定的question_id的回答*/
    @RequestMapping(value = "/answer/getAnswerByQId")
    public List<Answer> getAnswerByQId(@RequestParam("question_id") int question_id);

    @RequestMapping(value = "/answer/getAnswerById")
    public Answer getAnswerById(@RequestParam("answer_id") int answer_id);

    /*点赞*/
    @RequestMapping(value = "/answer/addZang")
    public int addZang(@RequestParam("answer_id") int answer_id);

    /*减赞*/
    @RequestMapping(value = "/answer/cutZang")
    public int cutZang(@RequestParam("answer_id") int answer_id);


    /*------------------用户-----------------*/
    @RequestMapping(value = "/user/getUserById")
    public User getUserByid(@RequestParam("userid") String userid);

    @RequestMapping(value = "/user/addUser")
    public int addUser(@RequestParam("nickname") String nickname,
                       @RequestParam("avatarurl") String avatarurl);

    @RequestMapping(value = "/user/deleteUser")
    public int deleteUser(@RequestParam("userid") String userid);
}