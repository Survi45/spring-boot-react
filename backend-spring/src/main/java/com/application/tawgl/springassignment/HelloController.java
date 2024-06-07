package com.application.tawgl.springassignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;


@RestController

public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/candidates")
    @ResponseBody
    @CrossOrigin
    public List<Map<String, Object>> getCandidates() {
        try {
            // Execute a query to retrieve data from the candidates table
            return jdbcTemplate.queryForList("SELECT * FROM candidates");
        } catch (Exception e) {
            // Handle the exception (log or throw a custom exception)
            e.printStackTrace();
            return null; // For simplicity, returning null; you may want to handle this differently
        }
    }

    @PutMapping("/reschedule/{sno}")
    public String rescheduleCandidate(@PathVariable int sno, @RequestBody Map<String, Object> requestBody) {
        try {
            String newDateTime = (String) requestBody.get("newDateTime");

            jdbcTemplate.update("UPDATE candidates SET datetime = ? WHERE sno = ?", newDateTime, sno);
            return "Candidate rescheduled successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error rescheduling candidate.";
        }
    }

    @DeleteMapping("/delete")
    public String deleteCandidate(@RequestBody Map<String, Object> requestBody) {
        try {
            // Extract necessary information from the request body
            int candidateSno = (int) requestBody.get("sno");

            // Execute a query to delete the candidate
            jdbcTemplate.update("DELETE FROM candidates WHERE sno = ?", candidateSno);
            return "Candidate deleted successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting candidate.";
        }
    }

    @GetMapping("/candidates/{name}")
    @ResponseBody
    @CrossOrigin
    public List<Map<String, Object>> getCandidatesByNameAndOlderThanToday(@PathVariable String name) {
        try {
            // Execute a query to retrieve data from the candidates table based on name and
            // older than today's date
            String sql = "SELECT * FROM candidates WHERE name = ? AND datetime < CURDATE()";
            return jdbcTemplate.queryForList(sql, name);
        } catch (Exception e) {
            // Handle the exception (log or throw a custom exception)
            e.printStackTrace();
            return null; // For simplicity, returning null; you may want to handle this differently
        }
    }

    @PostMapping("/candidates/add")
    public String addCandidate(@RequestBody Map<String, Object> requestBody) {
        try {
            String name = (String) requestBody.get("name");
            String position = (String) requestBody.get("position");
            String datetime = (String) requestBody.get("datetime"); // Assuming the date is provided as a string

            // Execute a query to insert a new candidate into the candidates table
            jdbcTemplate.update("INSERT INTO candidates (name, position, datetime) VALUES (?, ?, ?)", name, position,
                    datetime);

            return "Candidate added successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error adding candidate.";
        }
    }
}
