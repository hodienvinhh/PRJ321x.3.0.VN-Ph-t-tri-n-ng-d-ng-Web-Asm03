package com.example.assignment03.service;

import com.example.assignment03.dto.PostsDTO;
import com.example.assignment03.entity.Posts;
import com.example.assignment03.form.CreatePostForm;

import java.text.ParseException;

public interface IPostsService {
    void createPost(CreatePostForm form) throws ParseException;


    void changeConfirmByDoctor(int id, int status, String content);

    PostsDTO viewAppointmentsUserByAdmin(int id);

}
