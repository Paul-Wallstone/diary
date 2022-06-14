package by.school.diary.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import by.school.diary.dto.InfoDto;
import by.school.diary.entity.InfoEntity;
import by.school.diary.exception.IdIsNullException;
import by.school.diary.exception.InfoNotFoundException;
import by.school.diary.repository.InfoRepository;
import by.school.diary.service.InfoService;
import by.school.diary.utils.CustomModelMapper;

@Service
public class InfoServiceImpl implements InfoService {
	@Autowired
	private InfoRepository infoRepository;
	@Autowired
	private CustomModelMapper modelMapper;

	@Override
	public InfoDto getById(Long id) {
		InfoEntity infoEntity = infoRepository.findById(id).orElseThrow(() -> new InfoNotFoundException(id));
		return modelMapper.toDto(infoEntity);
	}

	@Override
	public List<InfoDto> getAll() {
		Stream<InfoEntity> infoEntities = StreamSupport.stream(infoRepository.findAll().spliterator(), false);
		return infoEntities.map(info -> modelMapper.toDto(info)).collect(Collectors.toList());
	}

	@Override
	public InfoDto save(InfoDto dto) {
        InfoEntity infoEntity = modelMapper.toEntity(dto);
        InfoEntity savedInfoEntity = infoRepository.save(infoEntity);
        return modelMapper.toDto(savedInfoEntity);
	}

	@Override
	public InfoDto update(InfoDto dto, Long id) {
        if (!ObjectUtils.isEmpty(id)) {
            InfoEntity infoEntity = infoRepository.findById(id).orElseThrow(() -> new InfoNotFoundException(id));
            infoEntity.setFirstName(dto.getFirstName());
            infoEntity.setLastName(dto.getLastName());
            infoEntity.setEmail(dto.getEmail());
            infoEntity.setSex(dto.getSex());
            infoEntity.setBirthday(dto.getBirthday());
            infoEntity.setBio(dto.getBio());
            InfoEntity updatedInfoEntity = infoRepository.save(infoEntity);
            return modelMapper.toDto(updatedInfoEntity);
        }
        throw new IdIsNullException("Id is null, please revise id of Info");
	}

	@Override
	public InfoDto update(InfoDto dto) {
        if (!ObjectUtils.isEmpty(dto.getId()))
            return update(dto, dto.getId());
        throw new IdIsNullException("Id is null, please revise id of Info");
	}

	@Override
	public void deleteById(Long id) {
        try {
            infoRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new InfoNotFoundException(id);
		}
	}

	@Override
	public void delete(InfoDto dto) {
        InfoEntity infoEntity = modelMapper.toEntity(dto);
        try {
            infoRepository.delete(infoEntity);
        } catch (IllegalArgumentException e) {
            throw new InfoNotFoundException(infoEntity.getId());
        }
	}

}
