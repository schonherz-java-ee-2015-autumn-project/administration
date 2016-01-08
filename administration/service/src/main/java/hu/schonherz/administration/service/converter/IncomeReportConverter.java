package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import hu.schonherz.administration.persistence.entities.IncomeReport;
import hu.schonherz.administration.serviceapi.dto.IncomeReportDTO;

public class IncomeReportConverter {

	public static IncomeReportDTO toDTO(IncomeReport report){
		IncomeReportDTO result = new IncomeReportDTO();
		result.setId(report.getId());
		result.setDate(report.getDate());
		result.setCreditCard(report.getCreditCard());
		result.setSzepCard(report.getSzepCard());
		result.setVoucher(report.getVoucher());
		result.setCash(report.getCash());
		result.setTotalAmount(report.getTotalAmount());
		result.setRestaurantTransferAmount(report.getRestaurantTransferAmount());
		result.setCourierServiceAmount(report.getCourierServiceAmount());
		
		return result;
	}
	public static IncomeReport toEntity(IncomeReportDTO reportDTO){
		IncomeReport result = new IncomeReport();
		result.setId(reportDTO.getId());
		result.setDate(reportDTO.getDate());
		result.setCreditCard(reportDTO.getCreditCard());
		result.setSzepCard(reportDTO.getSzepCard());
		result.setVoucher(reportDTO.getVoucher());
		result.setCash(reportDTO.getCash());
		result.setTotalAmount(reportDTO.getTotalAmount());
		result.setRestaurantTransferAmount(reportDTO.getRestaurantTransferAmount());
		result.setCourierServiceAmount(reportDTO.getCourierServiceAmount());
		
		return result;
	}
	
	public static List<IncomeReport> toEntity(List<IncomeReportDTO> reportDTO){
		if(reportDTO == null)
			return null;
		List<IncomeReport> result = new ArrayList<>();
		for(IncomeReportDTO e : reportDTO){
			result.add(toEntity(e));
		}
		return result;
	}
	public static List<IncomeReportDTO> toDTO(List<IncomeReport> report){
		if(report == null)
			return null;
		List<IncomeReportDTO> result = new ArrayList<>();
		for(IncomeReport e : report){
			result.add(toDTO(e));
		}
		return result;
	}
}
