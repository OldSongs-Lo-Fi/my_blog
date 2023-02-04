package com.example.blog.Controllers;

import com.example.blog.Components.MainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class MainView {

    @Autowired
    MainModel mainModel;

    @GetMapping(value = {"", "/", "/home"})
    public String home(){
        return "home";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    //Все посты в ленте
    @GetMapping("/posts")
    public String posts(Model model){
        List<Map<String, Object>> posts =  mainModel.getTable("post");
        Collections.reverse(posts);
        model.addAttribute("list", posts);
        //TODO
        return "posts";
    }

    //Конкретный пост
    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") int postId, Model model){
        List<Map<String, Object>> post =  mainModel.getPost(postId);
        Map<String, Object> usable = post.get(0);
        List<Map<String, Object>> comments = mainModel.getCommentsByPost(postId);
        //TODO
        //Обработать приходящий список постов.
        Collections.reverse(comments);
        model.addAttribute("post", usable);
        model.addAttribute("comments", comments);
        return "post";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/credits")
    public String credits(){
        return "credits";
    }
}
