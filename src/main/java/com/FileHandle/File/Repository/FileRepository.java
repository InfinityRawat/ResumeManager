package com.FileHandle.File.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FileHandle.File.Entity.ApplicantEntity;

public interface FileRepository extends JpaRepository<ApplicantEntity, Integer> {

}
