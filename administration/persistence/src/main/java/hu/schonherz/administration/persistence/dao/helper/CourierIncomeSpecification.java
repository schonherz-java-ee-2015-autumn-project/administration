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
import hu.schonherz.administration.persistence.entities.CourierIncome;
import hu.schonherz.administration.persistence.entities.CourierIncome_;
import hu.schonherz.administration.persistence.entities.User;

public class CourierIncomeSpecification {

	public static Specification<CourierIncome> courierNameLike(String name) {
		return new Specification<CourierIncome>() {

			@Override
			public Predicate toPredicate(Root<CourierIncome> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get(CourierIncome_.courierName), "%" + name + "%");
			}

		};

	}
	

	public static Specification<CourierIncome> lastModifiedAt(Date date) {
		return new Specification<CourierIncome>() {

			@Override
			public Predicate toPredicate(Root<CourierIncome> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				LocalDate jodaDate = LocalDate.fromDateFields(date);
				LocalDate nextDay = jodaDate.plusDays(1);

				Date nextUtilDate = nextDay.toDate();
				return cb.between(root.<Date> get(CourierIncome_.date), jodaDate.toDate(), nextUtilDate);
			}

		};
	}

	public static Specification<CourierIncome> takenBy(User user) {
		return new Specification<CourierIncome>() {
			@Override
			public Predicate toPredicate(Root<CourierIncome> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<User> get(CourierIncome_.courier), user);
			}

		};
	}

}
