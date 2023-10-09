package com.example.demo.services

import com.example.demo.models.Message
import com.example.demo.repositories.MessageRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class MessageService(val repository: MessageRepository): IMessageService {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    override fun findMessages(): List<Message> {
        logger.info("getting all messages")
        return repository.findMessages()
    }

    override fun findMessageById(id: String): Message? {
        logger.info("getting message by id: $id")
        return repository.findMessageById(id)
    }

    override fun saveMessage(message: Message) {
        logger.info("persisting new message: ${message.text}")
        repository.saveMessage(message)
    }

    override fun updateMessage(id: String, text: String) {
        logger.info("updating message $id with text: $text")
        val wasMessageUpdated = repository.updateMessage(id, text)
        if (!wasMessageUpdated) throw ResponseStatusException(HttpStatus.NOT_FOUND, "Message does not exist")
    }

    override fun deleteMessage(id: String) {
        logger.info("deleting message: $id")
        val wasMessageDeleted = repository.deleteMessage(id)
        if (!wasMessageDeleted) throw ResponseStatusException(HttpStatus.NOT_FOUND, "Message does not exist")
    }
}