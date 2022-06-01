package by.school.diary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.school.diary.dto.request.ContactRequestDto;
import by.school.diary.dto.response.ContactResponseDto;
import by.school.diary.entity.ContactEntity;
import by.school.diary.exception.ContactNotFoundException;
import by.school.diary.repository.ContactRepository;
import by.school.diary.service.ContactService;
import by.school.diary.utils.CustomModelMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CustomModelMapper modelMapper;

    @Override
    public ContactResponseDto getById(Long id) {
        ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
        return modelMapper.toDto(contactEntity);
    }

    @Override
    public ContactResponseDto save(ContactRequestDto contactDto) {
        ContactEntity contactEntity = modelMapper.toEntity(contactDto);
        ContactEntity savedContactEntity = contactRepository.save(contactEntity);
        return modelMapper.toDto(savedContactEntity);
    }

    @Override
    public ContactResponseDto update(ContactRequestDto contactDto, Long id) {
        ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
        contactEntity.setAddress(contactDto.getAddress());
        contactEntity.setCity(contactDto.getCity());
        contactEntity.setPostcode(contactDto.getPostcode());
        contactEntity.setPhone(contactDto.getPhone());
        ContactEntity updatedContactEntity = contactRepository.save(contactEntity);
        return modelMapper.toDto(updatedContactEntity);
    }

    @Override
    public void delete(ContactRequestDto contactDto) {
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
