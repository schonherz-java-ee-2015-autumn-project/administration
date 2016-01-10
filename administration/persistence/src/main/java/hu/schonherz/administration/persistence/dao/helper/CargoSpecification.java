package hu.schonherz.administration.persistence.dao.helper;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

import hu.schonherz.administration.persistence.entities.Cargo;
import hu.schonherz.administration.persistence.entities.Cargo_;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.persistence.entities.helper.State;

public class CargoSpecification {

	public static Specification<Cargo> lastModifiedAt(Date date) {
		return new Specification<Cargo>() {

			@Override
			public Predicate toPredicate(Root<Cargo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				LocalDate jodaDate = LocalDate.fromDateFields(date);
				LocalDate nextDay = jodaDate.plusDays(1);

				Date nextUtilDate = nextDay.toDate();
				return cb.between(root.<Date> get(Cargo_.date), jodaDate.toDate(), nextUtilDate);
			}

		};
	}

	public static Specification<Cargo> takenBy(User user) {
		return new Specification<Cargo>() {
			@Override
			public Predicate toPredicate(Root<Cargo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<User> get(Cargo_.courier), user);
			}

		};
	}
	
	public static Specification<Cargo> isActive() {
		return new Specification<Cargo>() {
			@Override
			public Predicate toPredicate(Root<Cargo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<State> get(Cargo_.state), State.Delivering);
			}

		};
	}
	
	public static Specification<Cargo> notDeleted() {
		return new Specification<Cargo>() {
			@Override
			public Predicate toPredicate(Root<Cargo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Boolean> get(Cargo_.isDeleted), false);
			}

		};
	}
		
}
