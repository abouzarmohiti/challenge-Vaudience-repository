package com.Vaudience.challenge.api;


import com.Vaudience.challenge.exception.ContactIsExistException;
import com.Vaudience.challenge.exception.VersionNotSupportedException;
import com.Vaudience.challenge.model.Contact;
import com.Vaudience.challenge.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contact")
@Api(value = "Endpoints for contact operations.", tags = {"Contact Rest Endpoints"})
public class ContactController {

    private final ContactService contactService;

    @ApiOperation(value = "API to GET Contact list ", notes = "Get all contact", tags = {"Fetch Contact"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List retrieve success", response = List.class),
            @ApiResponse(code = 401, message = "Bad Request.", response = List.class)
    })
    @GetMapping
    public List<Contact> fetchAllContact(@RequestHeader("x-resource-version") String version)
            throws VersionNotSupportedException {
        if (!(version.equals("1.1") || version.equals("1.0"))) {
            throw new VersionNotSupportedException("version " + version);
        }
        return contactService.findAll();
    }


    @ApiOperation(value = "API to create Contact", notes = "Create a contact", tags = {"Create Contact"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Contact create success", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Bad Request.", response = ResponseEntity.class)
    })
    @PostMapping
    public ResponseEntity<Void> createNewContact(@RequestHeader("x-resource-version") String version,
                                                 @RequestBody @Valid Contact contact)
            throws VersionNotSupportedException, ContactIsExistException {
        if (!(version.equals("1.1") || version.equals("1.0"))) {
            throw new VersionNotSupportedException("version " + version);
        }
        contactService.save(contact);
        log.info("******************************* New contact create : " + contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "API to get Contact list by postal code", notes = "filter contacts by postal code", tags = {"Filter contact by postal code"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List retrieve success", response = List.class),
            @ApiResponse(code = 401, message = "Bad Request.", response = List.class)
    })
    @GetMapping(value = "/{postalCode}")
    public List<Contact> fetchByPostalCode(@RequestHeader("x-resource-version") String version,
                                           @PathVariable String postalCode)
            throws VersionNotSupportedException {
        if (!(version.equals("1.1") || version.equals("1.0"))) {
            throw new VersionNotSupportedException("version " + version);
        }
        return contactService.findByAddressPostalCode(postalCode);
    }

}

