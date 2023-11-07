
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.time.format.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;



/** TODO
 * Implement all the unit test in its corresponding class.
 * Make sure to be as exhaustive as possible. Coverage is checked ;)
 */

@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_get_all_doctors() throws Exception {
        Doctor doctor = new Doctor("jose","lopez",35,"jose@email.com");
        Doctor doctor2 = new Doctor("maria","valencia",28,"maria@email.com");
        Doctor doctor3 = new Doctor("maria","valencia",28,"maria@email.com");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        doctors.add(doctor2);

        when(doctorRepository.findAll()).thenReturn(doctors);

        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    void should_not_find_doctors() throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        when(doctorRepository.findAll()).thenReturn(doctors);
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_get_doctor_by_id() throws Exception {
        Doctor doctor = new Doctor("jose","lopez",35,"jose@email.com");
        doctor.setId(10);
        Optional<Doctor> optionalDoctor = Optional.of(doctor);

        when(doctorRepository.findById(doctor.getId())).thenReturn(optionalDoctor);

        assertThat(optionalDoctor).isPresent();
        assertThat(optionalDoctor.get().getId()).isEqualTo(doctor.getId());
        assertThat(doctor.getId()).isEqualTo(10);
        mockMvc.perform(get("/api/doctors/" + doctor.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void should_not_get_doctor_by_id() throws Exception {
        long id = 10;
        mockMvc.perform(get("/api/doctors/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_doctor() throws Exception {
        Doctor doctor = new Doctor("jose","lopez",35,"jose@email.com");

        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_create_doctor_with_null_attributes() throws Exception {
        Doctor doctor = new Doctor();

        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_create_doctor_send_other_class() throws Exception {
        //TODO: This class must implement validations, this test should fail or throw exception
        Room room = new Room();

        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_delete_doctor() throws Exception {
        Doctor doctor = new Doctor("jose","lopez",35,"jose@email.com");
        doctor.setId(10);
        Optional<Doctor> optionalDoctor = Optional.of(doctor);

        when(doctorRepository.findById(doctor.getId())).thenReturn(optionalDoctor);

        assertThat(optionalDoctor).isPresent();
        assertThat(optionalDoctor.get().getId()).isEqualTo(doctor.getId());
        assertThat(doctor.getId()).isEqualTo(10);

        mockMvc.perform(delete("/api/doctors/" + doctor.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void should_not_delete_doctor() throws Exception {
        long id = 20;

        mockMvc.perform(delete("/api/doctors/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_delete_all_doctors() throws Exception {
        mockMvc.perform(delete("/api/doctors"))
                .andExpect(status().isOk());
    }

}


@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @MockBean
    private PatientRepository patientRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void this_is_a_test(){
        // DELETE ME
        assertThat(true).isEqualTo(false);
    }

}

@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void this_is_a_test(){
        // DELETE ME
        assertThat(true).isEqualTo(false);
    }

}
