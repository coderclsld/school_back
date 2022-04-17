package com.clsld.provide.controller;

import com.clsld.provide.dao.QuestionDao;
import com.clsld.api.pojo.Question;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Resource
    public QuestionDao questionDao;

    /*增加问题*/
    @RequestMapping(value = "/addQuestion",method = RequestMethod.GET)
    public boolean addQuestion(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("userid") String userid, @RequestParam("username") String username){
        int focus = 0;
        try {
            Question question = new Question(title,content,userid,focus,username);
            questionDao.insert(question);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /*删除一条问题*/
    @RequestMapping(value = "/deleteQuestion",method = RequestMethod.GET)
    public boolean deleteQuestion(@RequestParam("question_id") int question_id) {
        try {
            questionDao.deleteByPrimaryKey(question_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*得到所有问题，后续需要改为分表模式*/
    // TODO: 31/3/2022 后续将问题的返回改变为分页的格式 
    @RequestMapping(value = "/getQuestion",method = RequestMethod.GET)
    public List<Question> getQuestion(){
        return questionDao.selectAll();
    }


    @RequestMapping(value = "selectByLimit")
    public List<Question> selectByLimit(@RequestParam("question_id")int question_id){
        return questionDao.selectByLimit(question_id);
    }


    /*查询一条问题，目前想不出有什么用先放着*/
    @RequestMapping(value = "/selectQuestion",method = RequestMethod.GET)
    public Question selectQuestionById(@RequestParam("question_id") int question_id){
        return questionDao.selectByPrimaryKey(question_id);

    }

    @RequestMapping(value = "/selectQuestionAll",method = RequestMethod.GET)
    public List<Question> selectQuestionById(){
        return questionDao.selectAll();
    }

    /*增加关注数*/
    @RequestMapping(value = "/addfocus",method = RequestMethod.GET)
    public int addfocus(@RequestParam("question_id") int question_id){
        try {
            questionDao.addfocus(question_id);
        }catch (Exception e) {
            return 404;
        }
        return questionDao.getfocus(question_id);
    }


    /*减少关注数*/
    @RequestMapping(value = "/cutfocus",method = RequestMethod.GET)
    public int cutfocus(@RequestParam("question_id") int question_id){
        try {
            questionDao.cutfocus(question_id);
        }catch (Exception e){
            return 404;
        }
        return questionDao.getfocus(question_id);
    }
}
