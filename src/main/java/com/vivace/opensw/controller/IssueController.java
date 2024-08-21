package com.vivace.opensw.controller;

import com.vivace.opensw.dto.issue.AddIssueDto;
import com.vivace.opensw.dto.issue.IssueListDto;
import com.vivace.opensw.dto.todo.AddToDo;
import com.vivace.opensw.dto.todo.ToDoList;
import com.vivace.opensw.entity.Issue;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class IssueController {
  private final IssueService issueService;
  @PostMapping("/projects/{projectId}/issues")
  public ResponseEntity<Issue> addToDo(@RequestBody AddIssueDto addIssueDto, @PathVariable("projectId") Long id){
    Issue issue=issueService.save(addIssueDto);
    return ResponseEntity.ok().body(issue);
  }
//  @GetMapping("/projects/{projectId}/issues")
//  public ResponseEntity<List<IssueListDto>> getToDoList(@PathVariable("projectId") Long projectid){
//    List<IssueListDto> issueListDtos=issueService.getIssuesByProjectId(projectid);
//    return ResponseEntity.ok().body(issueListDtos);
//  }
}