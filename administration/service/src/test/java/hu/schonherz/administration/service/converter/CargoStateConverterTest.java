package hu.schonherz.administration.service.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import hu.schonherz.administration.persistence.entities.helper.State;
import hu.schonherz.administration.serviceapi.dto.CargoState;

@RunWith(MockitoJUnitRunner.class)
public class CargoStateConverterTest {

	private static final CargoState CARGO_DTO_STATE = CargoState.Free;

	private static final State CARGO_ENTITY_STATE = State.Free;
	
	@InjectMocks
	private CargoStateConverter sut;
	
	@Test(expected = NullPointerException.class)
	public void testToDTOShouldThrowNPEWhenStateIsNull() {
		this.sut.toDTO((State)null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testToEntityShouldThrowNPEWhenStateIsNull() {
		this.sut.toEntity((CargoState)null);
	}
	
	@Test
	public void testToDTOShouldReturnDTOForState() {
		CargoState result = this.sut.toDTO(CARGO_ENTITY_STATE);
		assertNotNull(result);
		assertEquals(CARGO_DTO_STATE, result);
	}
	
	@Test
	public void testToEntityShouldReturnEntityForState() {
		State result = this.sut.toEntity(CARGO_DTO_STATE);
		assertNotNull(result);
		assertEquals(CARGO_ENTITY_STATE, result);
	}

}
