package com.clsld.provide.controller;

import com.clsld.api.pojo.Comment;
import com.clsld.api.util.CommentNode;
import com.clsld.api.util.TreeUtil;
import com.clsld.provide.dao.CommentDao;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "/getCommentByAnswerId",method = RequestMethod.GET,produces = "application/json")
    public List<CommentNode> getCommentByAnswerId(@RequestParam("answerId") int answer_id){
        List<Comment> info= commentDao.selectByAnswerId(answer_id);
        TreeUtil treeUtils = new TreeUtil();
        List<CommentNode> node = treeUtils.buildTree(info, 100);
        return node;
    }

    @RequestMapping(value = "/addComment",method = RequestMethod.GET)
    public boolean addComment(@RequestParam("userid")String userid, @RequestParam("parent_id")Integer parent_id,
                              @RequestParam("content")String content, @RequestParam("answer_id")Integer answer_id,
                              @RequestParam("parent_name")String parent_name, @RequestParam("username")String username,
                              @RequestParam("avatar_url")String avatar_url){
        Comment c = new Comment();
        c.setUserid(userid);c.setParent_id(parent_id);c.setContent(content);c.setAvatar_url(avatar_url);
        c.setAnswer_id(answer_id);c.setParent_name(parent_name);c.setUsername(username);
        try {
            commentDao.insert(c);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/deleteComment",method = RequestMethod.GET)
    public int deleteComment(@RequestParam("comment_id")int comment_id){
        return commentDao.deleteByPrimaryKey(comment_id);
    }

    // TODO: 31/3/2022 评论查询优化，优化成范围定向查询
    @RequestMapping(value = "/selectByLimit",method = RequestMethod.GET)
    public List<Comment> selectByLimit(@RequestParam("page")int page){
        return commentDao.selectByLimit(page);
    }
}
