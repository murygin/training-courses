package org.dm.trainingcourses.repository;

import org.dm.trainingcourses.model.Date;
import org.dm.trainingcourses.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<Date, Long> {
    // This interface is intentionally left empty. It inherits all the necessary methods from JpaRepository.
}
