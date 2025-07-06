
package lingshin.meteor.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lingshin.meteor.web.entity.Mentor;
import lingshin.meteor.web.repository.MentorRepository;

@Service
public class MentorService {
  @Autowired
  MentorRepository mentorRepository;

  public MentorRepository getRepository() {
    return mentorRepository;
  }

  public List<Mentor> search(String name, String campus) {
    return mentorRepository
        .findAllByCollege(campus)
        .stream()
        .filter(mentor -> mentor.getName().contains(campus))
        .collect(Collectors.toList());
  }
}
