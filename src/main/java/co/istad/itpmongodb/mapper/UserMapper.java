package co.istad.itpmongodb.mapper;


import co.istad.itpmongodb.domain.User;
import co.istad.itpmongodb.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
}
