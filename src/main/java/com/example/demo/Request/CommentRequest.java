package com.example.demo.Request;

import com.example.demo.Response.IEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentRequest implements IEmpty {
    private Long userId;
    private long postId;
    private long parentCommentId;
    private String text;
}
