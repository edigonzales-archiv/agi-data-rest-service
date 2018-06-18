package ch.so.agi.gdi.datarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.so.agi.gdi.datarestservice.model.DataSetView;

@Repository
public interface DataSetViewRepository extends JpaRepository<DataSetView, Long> {

}
