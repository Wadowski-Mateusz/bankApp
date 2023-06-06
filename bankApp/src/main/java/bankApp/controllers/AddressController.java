package bankApp.controllers;

import bankApp.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /* *************************/
    /*  controller is set to   */
    /*     hasRole(ADMIN)      */
    /* *************************/

}
