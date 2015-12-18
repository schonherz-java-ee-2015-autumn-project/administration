package hu.schonherz.administration.persistence.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( Restaurant.class )
public class Restaurant_ {
    public static volatile SingularAttribute<Restaurant, Long> id;
    public static volatile SingularAttribute<Restaurant, String> name;
    public static volatile SingularAttribute<Restaurant, String> address;
    public static volatile SingularAttribute<Restaurant, String> phoneNumber;
    public static volatile SingularAttribute<Restaurant, Integer> price;

}