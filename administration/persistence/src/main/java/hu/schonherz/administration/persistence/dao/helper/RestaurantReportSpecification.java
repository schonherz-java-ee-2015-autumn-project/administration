package hu.schonherz.administration.persistence.dao.helper;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

import hu.schonherz.administration.persistence.entities.CourierIncome;
import hu.schonherz.administration.persistence.entities.CourierIncome_;
import hu.schonherz.administration.persistence.entities.RestaurantReport;
import hu.schonherz.administration.persistence.entities.RestaurantReport_;

public class RestaurantReportSpecification {

	public static Specification<RestaurantReport> lastModifiedAt(Date date) {
		return new Specification<RestaurantReport>() {

			@Override
			public Predicate toPredicate(Root<RestaurantReport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				LocalDate jodaDate = LocalDate.fromDateFields(date);
				LocalDate nextDay = jodaDate.plusDays(1);

				Date nextUtilDate = nextDay.toDate();
				return cb.between(root.<Date> get(RestaurantReport_.date), jodaDate.toDate(), nextUtilDate);
			}

		};
	}
	public static Specification<RestaurantReport> restaurantName(String name) {
		return new Specification<RestaurantReport>() {

			@Override
			public Predicate toPredicate(Root<RestaurantReport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(RestaurantReport_.restaurant), name);
			}

		};

	}
}
