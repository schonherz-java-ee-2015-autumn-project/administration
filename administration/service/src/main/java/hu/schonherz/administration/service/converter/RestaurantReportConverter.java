package hu.schonherz.administration.service.converter;

import java.util.LinkedList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.RestaurantReport;
import hu.schonherz.administration.serviceapi.dto.RestaurantReportDTO;

public class RestaurantReportConverter {
	static Mapper mapper = new DozerBeanMapper();
	
	public static RestaurantReport toEntity(RestaurantReportDTO restaurantReportDTO){
		return mapper.map(restaurantReportDTO, RestaurantReport.class);
	}
	
	public static RestaurantReportDTO toDTO(RestaurantReport restaurantReport){
		return mapper.map(restaurantReport, RestaurantReportDTO.class);
	}
	public static List<RestaurantReport> toEntityList(List<RestaurantReportDTO> restaurantReportsDTO){
		List<RestaurantReport> reports = new LinkedList<>();
		for(RestaurantReportDTO restaurantReportDTO : restaurantReportsDTO){
			reports.add(toEntity(restaurantReportDTO));
		}
		return reports;
	}
	public static List<RestaurantReportDTO> toDTOList(List<RestaurantReport> restaurantReports){
		List<RestaurantReportDTO> reports = new LinkedList<>();
		for(RestaurantReport restaurantReport : restaurantReports){
			reports.add(toDTO(restaurantReport));
		}
		return reports;
	}
}
