package com.wmt.smartparking;

import com.wmt.smartparking.dto.ParkingLotDto;
import com.wmt.smartparking.dto.VehicleDto;
import com.wmt.smartparking.model.ParkingLot;
import com.wmt.smartparking.model.Vehicle;
import com.wmt.smartparking.service.ParkingLotService;
import com.wmt.smartparking.service.VehicleService;
import com.wmt.smartparking.util.AssertUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartParkingApplicationTests {

	/*@Autowired
	private MockMvc mockMvc;*/

	@Autowired
	private ParkingLotService parkingLotService;

	@Autowired
	private VehicleService vehicleService;

	@Test
	public void testAddParkingLot() throws Exception {

		ParkingLotDto parkingLotDto = ParkingLotDto.builder().lotId("ABC-123").location("North").capacity(5).build();
		parkingLotService.addParkingLot(parkingLotDto);
		ParkingLot parkingLot = parkingLotService.getParkingLotList(ParkingLotDto.builder().lotId("ABC-123").build()).getList().get(0);
		AssertUtil.notNull(parkingLot, "parkingLot test failed");

		/*int mockResult = 1;
		when(parkingLotService.addParkingLot(any())).thenReturn(mockResult);

		mockMvc.perform(post("/smart/vehicle/v1/addParkingLot")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(parkingLotDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true));

		verify(parkingLotService, times(1)).addParkingLot(any());*/
	}

	@Test
	public void testAddVehicle() throws Exception {

		VehicleDto vehicleDto = VehicleDto.builder().plateId("QWERTY-789").ownerName("Kira").vehicleType(1).build();
		vehicleService.addVehicle(vehicleDto);
		Vehicle parkingLot = vehicleService.getVehicleList(VehicleDto.builder().plateId("QWERTY-789").ownerName("Kira").vehicleType(1).build()).getList().get(0);
		AssertUtil.notNull(parkingLot, "vehicle test failed");

		/*
		int mockResult = 1;
		when(vehicleService.addVehicle(any())).thenReturn(mockResult);

		mockMvc.perform(post("/smart/vehicle/v1/addVehicle")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(vehicleDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true));

		verify(vehicleService, times(1)).addVehicle(any());*/
	}

}
