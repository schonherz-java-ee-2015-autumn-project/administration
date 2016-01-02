package hu.schonherz.administration.persistence.dao.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import hu.schonherz.administration.persistence.entities.Cargo;
import hu.schonherz.administration.persistence.entities.Cargo_;

public class CargoSpecification {
	
	public static Specification<Date> lastModifiedAt(Date date) {
		return new Specification<Date>() {

			
			
			@Override
			public Predicate toPredicate(Root<Date> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/hh/mm/ss"); 
				Date today = new Date();
				today.setHours(0);
				today.setMinutes(0);
				today.setSeconds(0);
				Date tomorrow=new Date();
				tomorrow.setHours(23);
				tomorrow.setMinutes(59);
				tomorrow.setSeconds(59);
				Date todayWithZeroTime = formatter.parse(formatter.format(today));
				Date todayMidnight = formatter.parse(formatter.format(tomorrow));
				return cb.between(root.get(Cargo_.date), todayWithZeroTime, todayMidnight);
			}

		};
	}

}
