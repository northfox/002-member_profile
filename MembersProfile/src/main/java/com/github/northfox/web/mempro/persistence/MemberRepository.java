package com.github.northfox.web.mempro.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.northfox.web.mempro.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{
  List<Member> findByName(String name);
  List<Member> findByEmail(String email);
}
