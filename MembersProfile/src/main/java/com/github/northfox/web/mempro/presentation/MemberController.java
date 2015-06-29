package com.github.northfox.web.mempro.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.northfox.web.mempro.config.MemberUriConstants;
import com.github.northfox.web.mempro.domain.Member;

@Controller
/**
 * Handles requests for the Member service.
 */
public class MemberController {

  private static final Logger logger = LoggerFactory
      .getLogger(MemberController.class);

  // Map to store member, ideally we should use database
  Map<Long, Member> memberData = new HashMap<Long, Member>();

  @RequestMapping(value = MemberUriConstants.DUMMY_PATH, method = RequestMethod.GET)
  public @ResponseBody Member getDummyMember() {
    logger.info("Start getDummyMember");
    Member member = new Member();
    long id = 9999;
    member.setId(id);
    member.setName("Dummy");
    member.setCreatedDate(new Date());
    memberData.put(id, member);
    return member;
  }

  @RequestMapping(value = MemberUriConstants.GET_PATH, method = RequestMethod.GET)
  public @ResponseBody Member getMember(@PathVariable("id") long id) {
    logger.info("Start get Member. [ID={}]", id);

    return memberData.get(id);
  }

  @RequestMapping(value = MemberUriConstants.GET_ALL_PATH, method = RequestMethod.GET)
  public @ResponseBody List<Member> getAllMembers() {
    logger.info("Start get all Members.");
    List<Member> members = new ArrayList<Member>();
    Set<Long> memberIdKeys = memberData.keySet();
    for (Long i : memberIdKeys) {
      members.add(memberData.get(i));
    }
    return members;
  }

  @RequestMapping(value = MemberUriConstants.CREATE_PATH, method = RequestMethod.POST)
  public @ResponseBody Member createMember(@RequestBody Member member) {
    logger.info("Start createMember.");
    member.setCreatedDate(new Date());
    memberData.put(member.getId(), member);
    return member;
  }

  @RequestMapping(value = MemberUriConstants.UPDATE_PATH, method = RequestMethod.PUT)
  public @ResponseBody Member updateMember(@RequestBody Member member,
      @PathVariable("id") long memberId) {
    logger.info("Start update Member.");
    memberData.put(memberId, member);
    return member;
  }

  @RequestMapping(value = MemberUriConstants.DELETE_PATH, method = RequestMethod.DELETE)
  public @ResponseBody Member deleteMember(@PathVariable("id") int memberId) {
    logger.info("Start delete Member.");
    Member member = memberData.get(memberId);
    memberData.remove(memberId);
    return member;
  }

}