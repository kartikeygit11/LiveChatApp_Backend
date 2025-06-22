package com.chat.app.Controller;

import com.chat.app.Repository.RoomRepository;
import com.chat.app.entities.Message;
import com.chat.app.entities.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController

@RequestMapping("/api/v1/rooms")
@CrossOrigin("${frontend.url}")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Create a new room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {
        roomId = roomId.replace("\"", "").trim(); // Remove quotes if sent as raw text

        if (roomId.isEmpty()) {
            return ResponseEntity.badRequest().body("Room ID cannot be empty");
        }

        if (roomRepository.findByRoomIdIgnoreCase(roomId) != null) {
            return ResponseEntity.badRequest().body("Room already exists!");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }

    // Get room by roomId
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        Room room = roomRepository.findByRoomIdIgnoreCase(roomId);
        if (room == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room not found!!");
        }
        return ResponseEntity.ok(room);
    }

    // Get paginated messages from a room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {

        Room room = roomRepository.findByRoomIdIgnoreCase(roomId);
        if (room == null) {
            return ResponseEntity.badRequest().body("Room not found!!");
        }

        List<Message> messages = room.getMessages();
        if (messages == null) messages = Collections.emptyList();

        int total = messages.size();
        int fromIndex = Math.max(0, total - (page + 1) * size);
        int toIndex = Math.min(total, fromIndex + size);

        List<Message> paginatedMessages = messages.subList(fromIndex, toIndex);
        return ResponseEntity.ok(paginatedMessages);
    }
}
