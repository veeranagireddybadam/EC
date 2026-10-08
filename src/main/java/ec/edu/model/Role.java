package ec.edu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
public class Role {
	@Id
	@Column(name="role_id")
	private int roleId;
	@Column(name="role_name")
	private String roleName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<User> user;
	
	

}
