package com.onlinelearning.Models;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {

    private Integer id;

    private Integer lessonId;

    private Integer userId;

    private String content;

    private LocalDateTime createdAt;

    private Integer parentId;

    private List<Comment> childComments;

    private User user;

}
