package com.lysenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lysenko.dao.RoleDAO;
import com.lysenko.domain.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

	@Transactional
	public Role getRole(int id) {
		return roleDAO.getRole(id);
	}

}
