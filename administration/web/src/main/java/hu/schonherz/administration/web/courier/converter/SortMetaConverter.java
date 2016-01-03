package hu.schonherz.administration.web.courier.converter;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;

public class SortMetaConverter {

	public static SortMetaDTO toDTO(SortMeta sortMeta) {
		SortMetaDTO result = new SortMetaDTO();
		result.setColumnName(sortMeta.getSortField());
		if (sortMeta.getSortOrder().equals(SortOrder.ASCENDING))
			result.setOrder(CustomSortOrder.ASC);
		else
			result.setOrder(CustomSortOrder.DESC);
		return result;
	}

	public static List<SortMetaDTO> toDTO(List<SortMeta> sortMeta) {
		if (sortMeta == null) {
			return null;
		}
		List<SortMetaDTO> result = new ArrayList<>();
		for (SortMeta sm : sortMeta) {
			result.add(toDTO(sm));
		}
		return result;
	}

}
