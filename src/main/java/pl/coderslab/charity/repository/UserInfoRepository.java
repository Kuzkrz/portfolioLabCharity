package pl.coderslab.charity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByName(String userName);
}
