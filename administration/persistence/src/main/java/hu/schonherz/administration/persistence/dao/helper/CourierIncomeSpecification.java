package hu.schonherz.administration.persistence.dao.helper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import hu.schonherz.administration.persistence.entities.CourierIncome;
import hu.schonherz.administration.persistence.entities.CourierIncome_;

public class CourierIncomeSpecification {

	public static Specification<CourierIncome> courierNameLike(String name) {
		return new Specification<CourierIncome>() {

			@Override
			public Predicate toPredicate(Root<CourierIncome> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get(CourierIncome_.courierName), "%" + name + "%");
			}

		};

	}
}
