package com.chat.app.Controller;

import com.chat.app.Repository.RoomRepository;
import com.chat.app.Repository.MessageRepository;
import com.chat.app.entities.Message;
import com.chat.app.entities.Room;
import com.chat.app.playload.MessageRequest;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@Controller
@CrossOrigin("${frontend.url}")
public class ChatController {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(RoomRepository roomRepository,
                          MessageRepository messageRepository,
                          SimpMessagingTemplate messagingTemplate) {
        this.roomRepository = roomRepository;
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, MessageRequest request) {
        Room room = roomRepository.findByRoomIdIgnoreCase(request.getRoomId());

        if (room == null) {
            throw new RuntimeException("Room not found: " + request.getRoomId());
        }

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());
        message.setRoom(room); // ✅ Associate message with room

        messageRepository.save(message); // ✅ Save the message

        // ✅ Broadcast message to subscribers
        messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
    }
}
