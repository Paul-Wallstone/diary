package by.school.diary.service.impl;

import by.school.diary.exception.IdIsNullException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.school.diary.dto.ContactDto;
import by.school.diary.entity.ContactEntity;
import by.school.diary.exception.ContactNotFoundException;
import by.school.diary.repository.ContactRepository;
import by.school.diary.service.ContactService;
import by.school.diary.utils.mapper.CustomModelMapper;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CustomModelMapper modelMapper;

    @Override
    public ContactDto getById(Long id) {
        ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
        return modelMapper.toDto(contactEntity);
    }

    @Override
    public List<ContactDto> all() {
        Stream<ContactEntity> contactEntities = StreamSupport.stream(contactRepository.findAll().spliterator(), false);
        return contactEntities.map(contact -> modelMapper.toDto(contact)).collect(Collectors.toList());
    }

    @Override
    public ContactDto save(ContactDto dto) {
        ContactEntity contactEntity = modelMapper.toEntity(dto);
        ContactEntity savedContactEntity = contactRepository.save(contactEntity);
        return modelMapper.toDto(savedContactEntity);
    }

    @Override
    public ContactDto update(ContactDto dto, Long id) {
        if (!ObjectUtils.isEmpty(id)) {
            ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
            contactEntity.setAddress(dto.getAddress());
            contactEntity.setCity(dto.getCity());
            contactEntity.setPostcode(dto.getPostcode());
            contactEntity.setPhone(dto.getPhone());
            ContactEntity updatedContactEntity = contactRepository.save(contactEntity);
            return modelMapper.toDto(updatedContactEntity);
        }
        throw new IdIsNullException("Id is null, please revise id of contact");
    }

    @Override
    public ContactDto update(ContactDto dto) {
        if (!ObjectUtils.isEmpty(dto.getId()))
            return update(dto, dto.getId());
        throw new IdIsNullException("Id is null, please revise id of contact");
    }

    @Override
    public void deleteById(Long id) {
        try {
            contactRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new ContactNotFoundException(id);
		}
    }

    @Override
    public void delete(ContactDto dto) {
        ContactEntity contactEntity = modelMapper.toEntity(dto);
        try {
            contactRepository.delete(contactEntity);
        } catch (IllegalArgumentException e) {
            throw new ContactNotFoundException(contactEntity.getId());
        }
    }
}
