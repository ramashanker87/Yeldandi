package com.raghu.app.controller;

import com.raghu.app.model.Student;
import com.raghu.app.service.RabbitMqSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class ProduceStudentController {

    private static final Logger logger = LoggerFactory.getLogger(ProduceStudentController.class.getName());
    private final RabbitMqSender rabbitMqSender;

    public ProduceStudentController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @PostMapping("/save")
    public String saveStudent(@RequestBody Student student) {
        logger.info("save student {}", student);
        rabbitMqSender.sendStudent(student);
        return student.getName()+" saved to the queue";
    }

    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam String name) {
        logger.info("delete student {}", name);
        rabbitMqSender.deleteStudent(name);
        return name+" deleted to the queue";
    }

}
