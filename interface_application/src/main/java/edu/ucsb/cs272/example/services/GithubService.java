package edu.ucsb.cs272.example.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service("Github")
public class GithubService {

    @Value("${app.github.api.access_token}")
    private String token;

    public static final String UPLOAD_ENDPOINT = "https://api.github.com/repos/{owner}/{repo}/contents/{path}";

    public static final String NEW_REPO_ENDPOINT = "https://api.github.com/user/repos";

    private final RestTemplate restTemplate;

    public GithubService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public String uploadFile(String owner, String repo, String path, String message, String content) throws JsonProcessingException {
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/vnd.github+json");
        headers.add("X-GitHub-Api-Version", "2022-11-28");
        headers.add("Authorization", "Bearer "+token);
        
        Map<String, Object> uriVariables = Map.of("owner", owner, "repo", repo, "path", path);
        
        Map<String, String> bodyMap = new HashMap<String, String>();
        bodyMap.put("message", message);
        bodyMap.put("content", content);

        String body = new ObjectMapper().writeValueAsString(bodyMap);
        
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> re =
            restTemplate.exchange(UPLOAD_ENDPOINT, HttpMethod.POST, entity, String.class, uriVariables);
        return re.getBody();
    }

    public String createRepo(String name) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/vnd.github+json");
        headers.add("X-GitHub-Api-Version", "2022-11-28");
        headers.add("Authorization", "Bearer "+token);
        
        Map<String, String> bodyMap = new HashMap<String, String>();
        bodyMap.put("name", name);

        String body = new ObjectMapper().writeValueAsString(bodyMap);
        
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
    
        ResponseEntity<String> re =
            restTemplate.exchange(NEW_REPO_ENDPOINT, HttpMethod.POST, entity, String.class);
        return re.getBody();
    }
}
