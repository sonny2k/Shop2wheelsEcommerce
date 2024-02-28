package com.shop2wheels.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shop2wheels.common.entity.Role;
import com.shop2wheels.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userThanhTL = new User("thanhtl.work@gmail.com", "121000", "Trung Thanh", "Luu");
		userThanhTL.addRole(roleAdmin);

		User savedUser = repo.save(userThanhTL);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateNewUserWithMultipleRoles() {
		User userNgocKT = new User("casdoixanh@gmail.com", "254200", "Khanh Ngoc", "Tieu");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);

		userNgocKT.addRole(roleEditor);
		userNgocKT.addRole(roleAssistant);

		User savedUser = repo.save(userNgocKT);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> usersList = repo.findAll();
		usersList.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {
		User userThanh = repo.findById(1).get();
		System.out.println(userThanh);
		assertThat(userThanh).isNotNull();
	}

	@Test
	public void testUpdateUserDetails() {
		User userThanh = repo.findById(1).get();
		userThanh.setEnabled(true);
		userThanh.setEmail("thanhluu.business@gmail.com");

		repo.save(userThanh);
	}

	@Test
	public void testUpdateUserRoles() {
		User userNgoc = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);

		userNgoc.getRoles().remove(roleEditor);
		userNgoc.addRole(roleSalesperson);

		repo.save(userNgoc);
	}

	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}

	@Test
	public void testGetUserByEmail() {
		String email = "enactusbu.tieukhanhngoc@gmail.com";
		User user = repo.getUserByEmail(email);

		assertThat(user).isNotNull();
	}

	@Test
	public void testCountById() {
		Integer id = 1;
		Long countById = repo.countById(id);

		assertThat(countById).isNotNull().isGreaterThan(0);
	}
}
