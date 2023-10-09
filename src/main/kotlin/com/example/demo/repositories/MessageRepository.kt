package com.example.demo.repositories

import com.example.demo.models.Message
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class MessageRepository(val db: JdbcTemplate) {

    fun findMessages(): List<Message> {
        return db.query("SELECT * FROM messages") { response, _ ->
            Message(
                response.getString("id"),
                response.getString("text")
            )
        }
    }

    fun findMessageById(id: String): Message? {
        val results = db.query("SELECT * FROM messages WHERE id = ?", id) { response, _ ->
            Message(
                response.getString("id"),
                response.getString("text")
            )
        }
        return results.firstOrNull()
    }

    fun saveMessage(message: Message) {
        db.update("INSERT INTO messages (text) VALUES (?)", message.text)
    }

    fun updateMessage(id: String, newText: String): Boolean {
        val rowsUpdated = db.update("UPDATE messages SET text = ? WHERE id = ?", newText, id)
        return rowsUpdated > 0
    }

    fun deleteMessage(id: String): Boolean {
        val rowsDeleted = db.update("DELETE FROM messages WHERE id = ?", id)
        return rowsDeleted > 0
    }

}
