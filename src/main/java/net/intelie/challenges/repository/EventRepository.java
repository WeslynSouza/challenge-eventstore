package net.intelie.challenges.repository;

import net.intelie.challenges.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM Event WHERE id = :id", nativeQuery = true)
    Event findEventById(@Param("id") long id);

    @Modifying
    @Query(value = "DELETE FROM Event WHERE type = :type", nativeQuery = true)
    void removeAllByType(@Param("type") String type);

    @Query(value = "SELECT * FROM Event WHERE type = :type AND timestamp > :startTime AND timestamp < :endTime ORDER BY timestamp", nativeQuery = true)
    ArrayList<Event> findEvents(@Param("type") String type, @Param("startTime") long startTime, @Param("endTime") long endTime);

    @Query(value = "SELECT * FROM Event WHERE type = :type", nativeQuery = true)
    ArrayList<Event> findEventsByType(@Param("type") String type);
}
