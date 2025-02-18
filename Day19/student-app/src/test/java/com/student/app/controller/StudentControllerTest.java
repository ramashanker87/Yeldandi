package com.student.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.app.modle.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testgetStudent() throws Exception {
    ResultActions resultActions = mockMvc.perform(get("/get/student").param("name", "student1"));
    resultActions.andExpect(status().isOk());
    String result = resultActions.andReturn().getResponse().getContentAsString();
//    assertEquals("", result);
  }

  @Test
  public void testsaveStudent() throws Exception {
    Student student = new Student();
    student.setName("student1");
    student.setId(1);
    ResultActions resultActions = mockMvc.perform(post("/save/student")
            .contentType(MediaType.APPLICATION_JSON).content(asJsonString(student))
            .accept(MediaType.APPLICATION_JSON));
    resultActions.andExpect(status().isOk());
    String result = resultActions.andReturn().getResponse().getContentAsString();
    Student expected = asStringToJson(result);
    assertEquals(expected.getName(), student.getName());
    assertEquals(expected.getId(), student.getId());

    ResultActions resultGetActions = mockMvc.perform(get("/get/student").param("name", "student1"));
    resultGetActions.andExpect(status().isOk());
    String getResult = resultActions.andReturn().getResponse().getContentAsString();
    Student getexpected = asStringToJson(getResult);
    assertEquals(getexpected.getName(), student.getName());
    assertEquals(getexpected.getId(), student.getId());
  }

  @Test
  public void testgetAllStudent() throws Exception {
    Student student1 = new Student();
    student1.setName("student1");
    student1.setId(1);
    Student student2 = new Student();
    student2.setName("student2");
    student2.setId(2);
    ResultActions resultActions1 = mockMvc.perform(post("/save/student")
            .contentType(MediaType.APPLICATION_JSON).content(asJsonString(student1))
            .accept(MediaType.APPLICATION_JSON));
    resultActions1.andExpect(status().isOk());
    ResultActions resultActions2 = mockMvc.perform(post("/save/student")
            .contentType(MediaType.APPLICATION_JSON).content(asJsonString(student2))
            .accept(MediaType.APPLICATION_JSON));
    resultActions2.andExpect(status().isOk());

    ResultActions resultGetActions = mockMvc.perform(get("/get/all/student"));
    resultGetActions.andExpect(status().isOk());
    String getResult = resultGetActions.andReturn().getResponse().getContentAsString();
    Map<String, Student> getexpected = asStringToMap(getResult);
    assertEquals(getexpected.size(), 2);

  }

  @Test
  public void testupdateStudent() throws Exception {
    Student student1 = new Student();
    student1.setName("student1");
    student1.setId(1);
    ResultActions resultActions1 = mockMvc.perform(post("/save/student")
            .contentType(MediaType.APPLICATION_JSON).content(asJsonString(student1))
            .accept(MediaType.APPLICATION_JSON));
    resultActions1.andExpect(status().isOk());

    ResultActions resultActionsUpdate =mockMvc.perform(put("/update/student")
            .param("name", "student1").param("id", "2"));
    resultActionsUpdate.andExpect(status().isOk());
    String getResult = resultActionsUpdate.andReturn().getResponse().getContentAsString();
    Student getexpected = asStringToJson(getResult);
    assertEquals(getexpected.getName(), student1.getName());
    assertEquals(getexpected.getId(), 2);

  }

  @Test
  public void testdeleteStudent() throws Exception {
    Student student = new Student();
    student.setName("student1");
    student.setId(1);
    ResultActions resultActions1 = mockMvc.perform(delete("/delete/student")
            .contentType(MediaType.APPLICATION_JSON).content(asJsonString(1))
            .accept(MediaType.APPLICATION_JSON));
    resultActions1.andExpect(status().isOk());

    ResultActions resultActionsUpdate =mockMvc.perform(delete("/delete/student")
            .param("name", "student1").param("id", "2"));
    resultActionsUpdate.andExpect(status().isOk());
    String getResult = resultActionsUpdate.andReturn().getResponse().getContentAsString();
    Student getexpected = asStringToJson(getResult);
//    assertEquals(getexpected.getId(), 1.getId());
    assertEquals(getexpected.getId(), 2);

  }

//  @Test
//  public void testdeleteStudent() {
//  }

  private String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String jsonStudent = mapper.writeValueAsString(obj);
      return jsonStudent;
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  private Student asStringToJson(final String json) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      Student student = mapper.readValue(json, Student.class);
      return student;
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  private Map<String, Student> asStringToMap(final String json) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      Map<String, Student> students = mapper.readValue(json, Map.class);
      return students;
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

}
