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
		RestaurantReport restaurantReport = new RestaurantReport();
		restaurantReport.setCash(restaurantReportDTO.getCash());
		restaurantReport.setCreditCard(restaurantReportDTO.getCreditCard());
		restaurantReport.setDate(restaurantReportDTO.getDate());
		restaurantReport.setId(restaurantReportDTO.getId());
		restaurantReport.setRestaurant(restaurantReportDTO.getRestaurant());
		restaurantReport.setRestaurantPrice(restaurantReportDTO.getRestaurantPrice());
		restaurantReport.setRestaurantTransferAmount(restaurantReportDTO.getRestaurantTransferAmount());
		restaurantReport.setSzepCard(restaurantReportDTO.getSzepCard());
		restaurantReport.setTotalAmount(restaurantReportDTO.getTotalAmount());
		restaurantReport.setVoucher(restaurantReportDTO.getVoucher());
		restaurantReport.setServicesPrize(restaurantReportDTO.getServicesPrize());
		return restaurantReport;
	}
	
	public static RestaurantReportDTO toDTO(RestaurantReport restaurantReport){
		RestaurantReportDTO restaurantReportDTO = new RestaurantReportDTO();
		restaurantReportDTO.setCash(restaurantReport.getCash());
		restaurantReportDTO.setCreditCard(restaurantReport.getCreditCard());
		restaurantReportDTO.setDate(restaurantReport.getDate());
		restaurantReportDTO.setId(restaurantReport.getId());
		restaurantReportDTO.setRestaurant(restaurantReport.getRestaurant());
		restaurantReportDTO.setRestaurantPrice(restaurantReport.getRestaurantPrice());
		restaurantReportDTO.setRestaurantTransferAmount(restaurantReport.getRestaurantTransferAmount());
		restaurantReportDTO.setSzepCard(restaurantReport.getSzepCard());
		restaurantReportDTO.setTotalAmount(restaurantReport.getTotalAmount());
		restaurantReportDTO.setVoucher(restaurantReport.getVoucher());
		restaurantReportDTO.setServicesPrize(restaurantReport.getServicesPrize());
		return restaurantReportDTO;
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
