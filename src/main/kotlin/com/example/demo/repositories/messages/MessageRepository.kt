package com.example.demo.repositories.messages

import com.example.demo.models.Message
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MessageRepository(val db: JdbcTemplate) {

    fun findMessages(): List<Message> {
        val sql = "SELECT * FROM messages"
        return db.query(sql) { response, _ ->
            Message(
                response.getString("id"),
                response.getString("text")
            )
        }
    }

    fun findMessageById(id: UUID): Message? {
        val sql = "SELECT * FROM messages WHERE id = ?"
        val results = db.query(sql, id ) { response, _ ->
            Message(
                response.getString("id"),
                response.getString("text")
            )
        }
        return results.firstOrNull()
    }

    fun saveMessage(message: Message) {
        val sql = "INSERT INTO messages (text) VALUES (?)"
        db.update(sql, message.text)
    }

    fun updateMessage(id: UUID, newText: String): Boolean {
        val sql = "UPDATE messages SET text = ? WHERE id = ?"
        val rowsUpdated = db.update(sql, newText, id)
        return rowsUpdated > 0
    }

    fun deleteMessage(id: UUID): Boolean {
        val sql = "DELETE FROM messages WHERE id = ?"
        val rowsDeleted = db.update(sql, id)
        return rowsDeleted > 0
    }

}
