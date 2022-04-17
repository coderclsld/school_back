package com.clsld.provide.controller;

import com.clsld.api.pojo.Answer;
import com.clsld.provide.dao.AnswerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AnswerDao answerDao;

    /*添加回答*/
    @RequestMapping(value = "/addAnswer",method = RequestMethod.GET)
    public boolean addAnswer(@RequestParam("answer") String answer, @RequestParam("userid") String userid,
                             @RequestParam("question_id") int question_id, @RequestParam("username") String username,
                             @RequestParam("avatar_url")String avatar_url){
        try{
            int zang = 0;
            Answer Ranswer  =new Answer(answer,zang,userid, question_id,username,avatar_url);
            answerDao.insert(Ranswer);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /*删除回答*/
    @RequestMapping(value = "/deleteAnswer",method = RequestMethod.GET)
    public boolean deleteAnswer(@RequestParam("answer_id") int answer_id){
        try {
            answerDao.deleteByPrimaryKey(answer_id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /*获得所有回答*/
    // TODO: 31/3/2022 后续改为分页格式 
    @RequestMapping(value = "/getAllAnswers",method = RequestMethod.GET)
    public List<Answer> getAllAnswers(){
        return answerDao.selectAll();
    }

    /*获得属于特定的question_id的回答*/
    @RequestMapping(value = "/getAnswerByQId")
    public List<Answer> getAnswerByQId(@RequestParam("question_id")int question_id){
        return answerDao.selectAnswerByQid(question_id);
    }

    @RequestMapping(value = "/getAnswerById")
    public Answer getAnswerById(@RequestParam("answer_id")int answer_id){
        redisTemplate.opsForZSet().incrementScore("access", String.valueOf(answer_id), 1);
        return answerDao.selectByPrimaryKey(answer_id);
    }

    /*点赞*/
    @RequestMapping(value = "/addZang")
    public int addZang(@RequestParam("answer_id")int answer_id){
        try {
            answerDao.addZang(answer_id);
        }catch (Exception e) {
            return 404;
        }
        return answerDao.getZang(answer_id);
    }

    /*减赞*/
    @RequestMapping(value = "cutZang")
    public int cutZang(@RequestParam("answer_id") int answer_id){
        try {
            answerDao.cutZang(answer_id);
        }catch (Exception e) {
            return 404;
        }
        return answerDao.getZang(answer_id);
    }

    @RequestMapping(value = "getAnswerOrderByZang")
    public List<Answer> getAnswerOrderByZang(@RequestParam("page")int page){

        return answerDao.selectGyZang(page);
    }

    @RequestMapping(value = "getAnswerOrderByZangByQid")
    public List<Answer> selectOrderByZangByQid(@RequestParam("question_id")int question_id,
                                               @RequestParam("page")int page){
        return answerDao.selectGyZangByQid(question_id,page);
    }

    @RequestMapping(value = "getCount")
    public int getCount(){
        return answerDao.getCount();
    }

    @RequestMapping(value = "getCountByQid")
    public int getCountByQid(@RequestParam("question_id")int question_id){
        return answerDao.getCountByQid(question_id);
    }

    @RequestMapping(value = "getAccessData")
    public List<AccessData> getAccessData(){
        Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().rangeWithScores("access", 0, -1);
        List<AccessData> accessData  = new ArrayList<>();
        System.out.println(tuples);
        if(tuples.size()!=0){
            for (ZSetOperations.TypedTuple<String> tuple : tuples) {
                AccessData a = new AccessData();
                a.setAnswer(answerDao.selectByPrimaryKey(Integer.valueOf(tuple.getValue())));
                a.setAccessCount((int)Double.parseDouble(String.valueOf(tuple.getScore())));
                accessData.add(a);
            }
        }
        return accessData;
    }

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

}
