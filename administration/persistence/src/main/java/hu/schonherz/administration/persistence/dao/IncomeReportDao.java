package hu.schonherz.administration.persistence.dao;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import hu.schonherz.administration.persistence.entities.IncomeReport;

@Repository
public interface IncomeReportDao extends JpaRepository<IncomeReport, Long>, JpaSpecificationExecutor<IncomeReport> {

	IncomeReport findById(long id);

}
