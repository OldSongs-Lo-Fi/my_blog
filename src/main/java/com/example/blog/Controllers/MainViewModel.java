package com.example.blog.Controllers;

import com.example.blog.Components.MainModel;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
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
                             @RequestParam("main_text") String mainText,
                             @RequestParam("image") MultipartFile image) throws IOException {
        System.out.println("#######ADDING!########");
        if(!image.isEmpty()){
            System.out.println("#######IMAGE IS EXIST!########");
            System.out.println("bytes: " + Arrays.toString(image.getBytes()));
            mainModel.insertPost(title, mainText, image.getBytes());
            return "redirect:/home";
        }
        System.out.println("#######IMAGE IS NOT EXIST!########");

        mainModel.insertPost(title, mainText);
        return "redirect:/home";
    }
    @PostMapping("/insertUsr")
    public String insertUsr(@RequestParam("login") String login,
                            @RequestParam("pass") String pass,
                            @RequestParam("email") String email){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodedPassword = encoder.encode(pass);

        mainModel.insertUsr(login, encodedPassword, email);
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

    @RequestMapping(value = "/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long imageId) {

        byte[] imageContent = (byte[]) mainModel.getPost(Math.toIntExact(imageId)).get(0).get("image");
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }
}
