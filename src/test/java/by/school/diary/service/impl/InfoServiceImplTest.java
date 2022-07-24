package by.school.diary.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import by.school.diary.domain.Sex;
import by.school.diary.dto.InfoDto;
import by.school.diary.entity.InfoEntity;
import by.school.diary.exception.InfoNotFoundException;
import by.school.diary.repository.InfoRepository;
import by.school.diary.utils.mapper.CustomModelMapper;

@ExtendWith(SpringExtension.class)
public class InfoServiceImplTest {
	
	@Spy
	private CustomModelMapper modelMapper = new CustomModelMapper(new ModelMapper());

	@Mock
	private InfoRepository infoRepository;
	
	@InjectMocks
	private InfoServiceImpl infoServiceImpl;
	
	private static InfoEntity INFO_ENTITY;
	private static InfoDto INFO_DTO;
	
	@BeforeEach
	void setUp() {
		INFO_ENTITY = InfoEntity.builder()
				.firstName("Alex")
				.lastName("Dark")
				.email("ADark@gmail.com")
				.birthday(LocalDate.of(1999, 10, 25))
				.sex(Sex.MALE)
				.bio("Cleaner")
				.build();
		INFO_ENTITY.setId(1L);
		INFO_DTO = InfoDto.builder()
				.firstName("Alex")
				.lastName("Dark")
				.email("ADark@gmail.com")
				.birthday(LocalDate.of(1999, 10, 25))
				.sex(Sex.MALE)
				.bio("Cleaner")
				.build();
		INFO_DTO.setId(1L);
	}
	
	@Test
	void testGetByIdSucsess() {
		when(modelMapper.toDto(any(InfoEntity.class))).thenReturn(INFO_DTO);
		when(infoRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(INFO_ENTITY));
		InfoDto actualInfoDto = infoServiceImpl.getById(1L);
		assertEquals(INFO_DTO, actualInfoDto);
		verify(infoRepository).findById(isNotNull());
		verify(infoRepository).findById(1L);
		verify(infoRepository, times(0)).findById(isNull());
	}
	
	@Test
	void testGetByIdWithMapperSucsess() {
		when(infoRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(INFO_ENTITY));
		InfoDto actualInfoDto = infoServiceImpl.getById(1L);
		assertEquals(INFO_DTO, actualInfoDto);
		verify(infoRepository).findById(isNotNull());
		verify(infoRepository).findById(1L);
		verify(infoRepository, times(0)).findById(isNull());
	}
	
	@Test
	void testGetByIdFailed() {
		when(infoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		doThrow(InfoNotFoundException.class).when(infoRepository).findById(anyLong());
		assertThrows(InfoNotFoundException.class, () -> infoRepository.findById(anyLong()));
		verify(infoRepository).findById(anyLong());
		verify(infoRepository, times(0)).findById(isNull());
	}
}
