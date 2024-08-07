package ar.lamansys.messages.infrastructure.output.repository;

import ar.lamansys.messages.infrastructure.output.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {

}
