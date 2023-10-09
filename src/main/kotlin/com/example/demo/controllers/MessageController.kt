package com.example.demo.controllers

import com.example.demo.models.Message
import com.example.demo.services.IMessageService
import com.example.demo.util.calcExecutionTime
import com.example.demo.util.getStartTime
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/message")
class MessageController(val service: IMessageService) {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    @GetMapping()
    fun findMessages(): ResponseEntity<List<Message>> {
        val startTime = getStartTime()

        val messages = service.findMessages()

        calcExecutionTime(startTime)
        return ResponseEntity.ok(messages)
    }

    @GetMapping("/{id}")
    fun findMessageById(@PathVariable id: String): ResponseEntity<Message> {
        val startTime = getStartTime()

        val message = service.findMessageById(id)
        val res: ResponseEntity<Message> =
            if (message != null) ResponseEntity.ok(message)
            else ResponseEntity(HttpStatus.NOT_FOUND)

        calcExecutionTime(startTime)
        return res
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveMessage(@RequestBody message: Message) {
        val startTime = getStartTime()

        service.saveMessage(message)
        calcExecutionTime(startTime)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateMessage(@PathVariable id: String, @RequestBody message: Message) {
        try {
            val startTime = getStartTime()

            service.updateMessage(id, message.text)
            calcExecutionTime(startTime)
        } catch (e: ResponseStatusException) {
            throw e
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMessage(@PathVariable id: String) {
        try {
            val startTime = getStartTime()

            service.deleteMessage(id)
            calcExecutionTime(startTime)
        } catch (e: ResponseStatusException) {
            throw e
        }
    }
}
