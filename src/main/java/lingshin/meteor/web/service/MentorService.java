
package lingshin.meteor.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lingshin.meteor.web.repository.MentorRepository;

@Service
public class MentorService {
  @Autowired
  MentorRepository mentorRepository;
}
