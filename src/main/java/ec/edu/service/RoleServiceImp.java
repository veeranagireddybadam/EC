package ec.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.model.Role;
import ec.edu.repository.RoleRepository;

@Service
public class RoleServiceImp implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role createRole(Role role) {
		
		return roleRepository.save(role);
	}

	@Override
	public List<Role> getallroles() {
		// TODO Auto-generated method stub
		
		return roleRepository.findAll();
	}

}
