package edu.ucsb.cs272.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.ucsb.cs272.example.services.GithubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "API to handle github operations")
@RequestMapping("/api/github")
@RestController
public class GithubController {
    @Autowired GithubService githubService;

    @Operation(summary = "Create a github repository")
    @PostMapping("/create")
    public ResponseEntity<String> createRepo(
        @Parameter(name="repoName", description="name for new repo") @RequestParam String repoName
    ) throws JsonProcessingException {
        String result = githubService.createRepo(repoName);
        return ResponseEntity.ok().body(result);
        // return genericMessage("Github repo %s created".formatted(repoName));
    }
}
