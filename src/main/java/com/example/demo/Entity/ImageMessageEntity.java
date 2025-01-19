package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.Message;


@Entity
@Table(name = "imagemessage")
@NoArgsConstructor
@Data
public class ImageMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "_id")
    @JsonIgnore
    private MessageEntity message;
    private String urlImg;

    public ImageMessageEntity(MessageEntity message, String urlImg) {
        this.message = message;
        this.urlImg = urlImg;
    }
}