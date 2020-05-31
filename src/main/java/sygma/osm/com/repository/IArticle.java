package sygma.osm.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sygma.osm.com.entity.Article;

import java.util.List;

@Repository
public interface IArticle extends JpaRepository<Article,Long> {
	/*
	 * @Query(value = "select * from article where libelle =:libelle ", nativeQuery
	 * = true) List<Article> findByLibelle(@Param("libelle") String libelle);
	 */
}
