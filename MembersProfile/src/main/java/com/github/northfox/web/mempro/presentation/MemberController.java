package com.github.northfox.web.mempro.presentation;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.northfox.web.mempro.config.MemberUriConstants;
import com.github.northfox.web.mempro.domain.Member;
import com.github.northfox.web.mempro.persistence.MemberRepository;

/**
 * Handles requests for the Member service.
 */
@RestController
@Transactional
public class MemberController {

  @Autowired
  private MemberRepository repo;

  private static final Logger logger = LoggerFactory
      .getLogger(MemberController.class);

  @RequestMapping(value = MemberUriConstants.DUMMY_PATH, method = RequestMethod.GET)
  public @ResponseBody Member getDummyMember() {
    logger.info("Start get dummy member.");

    long dummyId = 9999;
    if (!repo.exists(dummyId)) {
      Member member = new Member();
      member.setId(dummyId);
      member.setName("Dummy");
      member.setEmail("dummy@example.com");
      member.setCreatedDate(new Date());
      member.setUpdatedDate(new Date());
      repo.save(member);
    }

    return repo.findOne(dummyId);
  }

  @RequestMapping(value = MemberUriConstants.GET_PATH, method = RequestMethod.GET)
  public @ResponseBody Member getMember(@PathVariable("id") long id) {
    logger.info("Start get member. [ID={}]", id);
    return repo.findOne(id);
  }

  @RequestMapping(value = MemberUriConstants.GET_ALL_PATH, method = RequestMethod.GET)
  public @ResponseBody Iterable<Member> getAllMembers() {
    logger.info("Start get all members.");
    return repo.findAll();
  }

  @RequestMapping(value = MemberUriConstants.CREATE_PATH, method = RequestMethod.POST)
  public @ResponseBody Member createMember(@RequestBody Member member) {
    logger.info("Start create member.");
    member.setCreatedDate(new Date());
    member.setUpdatedDate(new Date());
    repo.save(member);
    logger.info("End create member. [ID={}]", member.getId());

    return member;
  }

  @RequestMapping(value = MemberUriConstants.UPDATE_PATH, method = RequestMethod.PUT)
  public @ResponseBody Member updateMember(@RequestBody Member member,
      @PathVariable("id") long id) {
    logger.info("Start update member. [ID={}]", id);
    member.setId(id);
    repo.save(member);
    return member;
  }

  @RequestMapping(value = MemberUriConstants.DELETE_PATH, method = RequestMethod.DELETE)
  public @ResponseBody Member deleteMember(@PathVariable("id") long id) {
    logger.info("Start delete member. [ID={}]", id);
    Member member = repo.findOne(id);
    repo.delete(id);
    return member;
  }

}