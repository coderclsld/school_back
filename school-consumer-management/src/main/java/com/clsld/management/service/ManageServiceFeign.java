package com.clsld.management.service;

import com.clsld.api.pojo.Answer;
import com.clsld.api.pojo.Comment;
import com.clsld.api.pojo.Question;
import com.clsld.api.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "school-provide-common")
public interface ManageServiceFeign {

    /*answer*/
    @RequestMapping(value = "/answer/getAnswerOrderByZang")
    public List<Answer> getAnswerOrderByZang(@RequestParam("page")int page);

    @RequestMapping(value = "/answer/getAnswerOrderByZangByQid")
    public List<Answer> selectOrderByZangByQid(@RequestParam("question_id")int question_id,
                                               @RequestParam("page")int page);
    @RequestMapping(value = "/answer/getCount")
    public int getCount();

    @RequestMapping(value = "/answer/getCountByQid")
    public int getCountByQid(@RequestParam("question_id")int question_id);

    @RequestMapping(value = "/answer/getAccessData")
    public List<AccessData> getAccessData();

    @RequestMapping(value = "/answer/getAllAnswers")
    public List<Answer> getAllAnswers();

    @RequestMapping(value = "/answer/deleteAnswer")
    public boolean deleteAnswer(@RequestParam("answer_id") int answer_id);

    class AccessData{
        private Answer answer;
        private int accessCount;

        public Answer getAnswer() {
            return answer;
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public int getAccessCount() {
            return accessCount;
        }

        public void setAccessCount(int accessCount) {
            this.accessCount = accessCount;
        }
    }

    /*question*/
    @RequestMapping(value = "/question/selectQuestionAll")
    public List<Question> selectQuestionAll();

    @RequestMapping(value = "/question/deleteQuestion")
    public boolean deleteQuestion(@RequestParam("question_id")int question_id);


    /*comment*/
    @RequestMapping(value = "/comment/selectByLimit")
    public List<Comment> selectCommentByLimit(@RequestParam("page")int page);

    @RequestMapping(value = "/deleteComment")
    public int deleteComment(@RequestParam("comment_id")int comment_id);

    /*user*/
    @RequestMapping(value = "/user/getUserAll")
    public List<User> getUserAll();

    @RequestMapping(value = "/user/deleteUser")
    public int deleteUser(@RequestParam("userid")String userid);
}
