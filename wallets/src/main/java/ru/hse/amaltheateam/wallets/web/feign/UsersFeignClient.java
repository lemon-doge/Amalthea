package ru.hse.amaltheateam.wallets.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.hse.amaltheateam.wallets.dto.user.response.UserResponseDTO;

@FeignClient(name = "amalthea-users", url = "http://localhost:8082")
public interface UsersFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/{id}", produces = "application/json")
    @ResponseBody
    UserResponseDTO getUserById(@PathVariable Long id);

}
