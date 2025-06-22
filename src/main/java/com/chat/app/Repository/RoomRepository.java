package com.chat.app.Repository;
import com.chat.app.entities.Room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomIdIgnoreCase(String roomId);
}

