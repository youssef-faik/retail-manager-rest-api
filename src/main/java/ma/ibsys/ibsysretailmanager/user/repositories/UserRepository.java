package ma.ibsys.ibsysretailmanager.user.repositories;

import ma.ibsys.ibsysretailmanager.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {}
