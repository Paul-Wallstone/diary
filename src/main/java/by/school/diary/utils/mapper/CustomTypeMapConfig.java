package by.school.diary.utils.mapper;

import by.school.diary.dto.LessonDto;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.entity.LessonEntity;
import by.school.diary.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomTypeMapConfig {

    private ModelMapper mapper;


    public CustomTypeMapConfig(ModelMapper mapper) {
        this.mapper = mapper;
        signUpRequestDtoToUserEntityType();
        lessonEntityToLessonDtoType();
    }

    private void signUpRequestDtoToUserEntityType() {
        mapper.createTypeMap(SignUpRequestDto.class, UserEntity.class)
                .addMappings(mappers -> mappers.map(src -> src, UserEntity::setInfo));
    }

    private void lessonEntityToLessonDtoType() {
        mapper.createTypeMap(LessonEntity.class, LessonDto.class)
                .addMappings(mappers -> mappers.map(src -> src, LessonDto::setEmployee))
                .addMappings(mappers -> mappers.map(src -> src, LessonDto::setGroup))
                .addMappings(mappers -> mappers.map(src -> src, LessonDto::setSubject));
    }

}
