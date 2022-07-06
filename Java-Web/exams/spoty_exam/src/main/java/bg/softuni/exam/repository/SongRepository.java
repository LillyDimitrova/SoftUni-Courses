package bg.softuni.exam.repository;

import bg.softuni.exam.model.entity.BaseEntity;
import bg.softuni.exam.model.entity.SongEntity;
import bg.softuni.exam.model.entity.StyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {
    List<SongEntity> findAllByStyle(StyleEntity style);


    List<SongEntity> findAllByUserId(Long id);

    @Query("SELECT SUM(s.duration) FROM SongEntity s")
    Integer totalMinutesSum();


}
