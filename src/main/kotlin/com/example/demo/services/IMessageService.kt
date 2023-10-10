package com.example.demo.services

import com.example.demo.models.Message
import java.util.UUID

interface IMessageService {

    fun findMessages(): List<Message>

    fun findMessageById(id: UUID): Message?

    fun saveMessage(message: Message)

    fun updateMessage(id: UUID, text: String)

    fun deleteMessage(id: UUID)
}