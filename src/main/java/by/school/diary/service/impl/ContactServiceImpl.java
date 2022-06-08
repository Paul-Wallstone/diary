package by.school.diary.service.impl;

import by.school.diary.exception.IdIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.school.diary.dto.ContactDto;
import by.school.diary.entity.ContactEntity;
import by.school.diary.exception.ContactNotFoundException;
import by.school.diary.repository.ContactRepository;
import by.school.diary.service.ContactService;
import by.school.diary.utils.CustomModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
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
    public List<ContactDto> getAll() {
        Stream<ContactEntity> contactEntities = StreamSupport.stream(contactRepository.findAll().spliterator(), false);
        return contactEntities.map(contact -> modelMapper.toDto(contact)).collect(Collectors.toList());
    }

    @Override
    public ContactDto save(ContactDto contactDto) {
        ContactEntity contactEntity = modelMapper.toEntity(contactDto);
        ContactEntity savedContactEntity = contactRepository.save(contactEntity);
        return modelMapper.toDto(savedContactEntity);
    }

    @Override
    public ContactDto update(ContactDto contactDto, Long id) {
        if (!ObjectUtils.isEmpty(id)) {
            ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
            contactEntity.setAddress(contactDto.getAddress());
            contactEntity.setCity(contactDto.getCity());
            contactEntity.setPostcode(contactDto.getPostcode());
            contactEntity.setPhone(contactDto.getPhone());
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
    public void delete(ContactDto contactDto) {
        ContactEntity contactEntity = modelMapper.toEntity(contactDto);
        if (contactRepository.findById(contactEntity.getId()).isPresent()) {
            contactRepository.delete(contactEntity);
        } else {
            throw new ContactNotFoundException(contactEntity.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
        } else {
            throw new ContactNotFoundException(id);
        }
    }

}
