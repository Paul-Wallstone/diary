package by.school.diary.domain;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum Role {
    @FieldNameConstants.Include ROLE_ADMIN,
    @FieldNameConstants.Include ROLE_STUDENT,
    @FieldNameConstants.Include ROLE_TEACHER,
    @FieldNameConstants.Include ROLE_DIRECTOR,
    @FieldNameConstants.Include ROLE_PARENT,
    @FieldNameConstants.Include ROLE_GUEST

}
