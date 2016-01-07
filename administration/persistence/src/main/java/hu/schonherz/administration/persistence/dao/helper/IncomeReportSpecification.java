package hu.schonherz.administration.persistence.dao.helper;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

import hu.schonherz.administration.persistence.entities.IncomeReport;
import hu.schonherz.administration.persistence.entities.IncomeReport_;

public class IncomeReportSpecification {

	public static Specification<IncomeReport> lastModifiedAt(Date date) {
		return new Specification<IncomeReport>() {

			@Override
			public Predicate toPredicate(Root<IncomeReport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				LocalDate jodaDate = LocalDate.fromDateFields(date);
				LocalDate nextDay = jodaDate.plusDays(1);

				Date nextUtilDate = nextDay.toDate();
				return cb.between(root.<Date> get(IncomeReport_.date), jodaDate.toDate(), nextUtilDate);
			}

		};
	}
}
