package com.example.demo.services

import com.example.demo.models.Message

interface IMessageService {

    fun findMessages(): List<Message>

    fun findMessageById(id: String): Message?

    fun saveMessage(message: Message)

    fun updateMessage(id: String, text: String)

    fun deleteMessage(id: String)
}