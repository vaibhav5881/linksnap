package com.vaibhav.linksnap.repository;

import com.vaibhav.linksnap.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl , Long> {

    Optional<ShortUrl> findByCode(String code);

    boolean existsByCode(String code);

    Page<ShortUrl> findByUserId(Long userId , Pageable pageable);

}
