package com.kiet.hostelmanagementmaven.repository;

import com.kiet.hostelmanagementmaven.entity.HostelStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelRepository extends JpaRepository<HostelStudent, Integer> {
}