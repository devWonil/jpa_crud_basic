package com.tencoding.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tencoding.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	// public abstract 생략
	@Query(value = "SELECT * FROM board WHERE id = :id", nativeQuery = true)
	Optional<Board> mFindById(int id);
	
	@Modifying // 수정, 삭제, 저장
	@Query(value = "DELETE FROM board WHERE id = :id", nativeQuery = true)
	int mDeleteById(int id);
}
