package com.tss.LibraryManagement.service;


import com.tss.LibraryManagement.Exceptions.*;
import com.tss.LibraryManagement.model.Member;

import java.util.HashMap;
import java.util.Map;

public class MemberService {

    private final Map<Integer, Member> membersById;
    private final Map<String, Integer> emailIndex;

    public MemberService() {
        membersById = new HashMap<>();
        emailIndex = new HashMap<>();
    }

    public Member addMember(String name, String email) throws InvalidEmailException {
        if (name == null || email == null) {
            throw new IllegalArgumentException("Member fields cannot be null");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new InvalidEmailException("Invalid email format");
        }
        if (emailIndex.containsKey(email)) {
            throw new InvalidEmailException("Email already exists");
        }

        Member member = new Member(name, email);
        membersById.put(member.getId(), member);
        emailIndex.put(email, member.getId());
        return member;
    }

    public Member findMemberById(int memberId) {
        return membersById.get(memberId);
    }

    public Map<Integer, Member> getMembersById() {
        return membersById;
    }

    public void viewAllMembers() {
        if (membersById.isEmpty()) {
            throw new MemberNotFoundException("No members found");
        }
        for (Member member : membersById.values()) {
            System.out.println(member);
        }
    }
}