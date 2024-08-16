package com.vivace.opensw.service;

import com.vivace.opensw.dto.project.ProjectAddRequestDto;
import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.entity.Position;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ProjectService {


  private final ArtifactRepository artifactRepository;
  private final ProjectRepository projectRepository;
  private final ArtifactTypeRepository artifactTypeRepository;
  private final ImgRepository imgRepository;
  private final CreatorRepository creatorRepository;
  private final MemberRepository memberRepository;
  private final ParticipateRepository participateRepository;
  private final PositionRepository positionRepository;
  @Transactional
  public Project save(ProjectAddRequestDto addProject) {//
   Project project=projectRepository.save(addProject.toEntity());
   List<Position> positionList=new ArrayList<>();
  for(String positionName: addProject.getPositionName()){
    Position position= Position.builder().position(positionName)
        .build();
    positionRepository.save(position);
    positionList.add(position);
  }




   Participate participate= Participate.builder().
       project(project).
       positionList(positionList)
       .build();
    participate = participateRepository.save(participate);


    project.getParticipateList().add(participate);

    return project;

  }
  public Project findById(Long id){
    return projectRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found: "+id));
  }

  public List<Project> findAll(){
    return projectRepository.findAll();
  }

  public void deleteById(long id){
    projectRepository.deleteById(id);
  }
  public List<Participate> getProjectParticipants(Long id){
    //매개변수 프로젝트 아이디
    Project project=findById(id);
    if(project!=null){
      return project.getParticipateList();
    }
    else
      return null;
  }


}
