package com.example.blog.Controllers;

import com.example.blog.Components.MainModel;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainViewModel {

    final MainModel mainModel;

    public MainViewModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @GetMapping("/getInfo")
    public String getInfo(){
        mainModel.debugTable("post");
        return "redirect:/home";
    }

    @GetMapping("/data")
    public List<Map<String, Object>> getData() {
        String sql = "SELECT * FROM usr";
        return mainModel.getJdbcTemplate().queryForList(sql);
    }

    @PostMapping("/insertPost")
    public String insertPost(@RequestParam("title") String title,
                             @RequestParam("main_text") String mainText){
        System.out.println("Was here");
        mainModel.insertPost(title, mainText);
        return "redirect:/home";
    }
    @PostMapping("/insertUsr")
    public String insertUsr(@RequestParam("login") String login,
                            @RequestParam("pass") String pass,
                            @RequestParam("email") String email){
        mainModel.insertUsr(login, pass, email);
        return "redirect:/logout";
    }

    @PostMapping("/insertComment")
    public String insertComment(@RequestParam("post_id") int postId,
                                ///@RequestParam("usr_id") int usrId,
                                @RequestParam("text") String text){
        System.out.println("Was here");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int usrId = (int) mainModel.findUsr(username).get("id");
        mainModel.insertComment(postId,usrId,text);
        return "redirect:/post/" + postId;
    }
}
