package hello.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, String>{

}
