package repository;

import model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    @Query(value = "select * from favorites q where q.idclient = :clientReference", nativeQuery = true)
    public List<Favorites> findAll(@Param("clientReference") Long clientReference);

}