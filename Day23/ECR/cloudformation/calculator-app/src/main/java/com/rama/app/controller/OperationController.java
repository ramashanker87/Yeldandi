package com.rama.app.controller;

import com.rama.app.model.Data;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.web.bind.annotation.*;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Calculator App")})
@RestController
public class OperationController {

  @GetMapping("/addition")
  public long addition(@RequestParam("a") long a, @RequestParam("b") long b) {
    return a + b;
  }

  @GetMapping("/substraction")
  public long substraction(@RequestParam("a") long a, @RequestParam("b") long b) {
    return a - b;
  }

  @PostMapping("/multiplication")
  public int multiplication(@RequestBody Data data) {
    return data.getA() * data.getB();
  }

  @PutMapping("/division")
  public long division(@RequestParam("a") long a, @RequestParam("b") long b) {
    return a / b;
  }

}
