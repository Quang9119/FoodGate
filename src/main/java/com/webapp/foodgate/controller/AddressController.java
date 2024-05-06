package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.AuthoritiesConstants;
import com.webapp.foodgate.dto.ApiMessageDto;
import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.dto.ResponseListDto;
import com.webapp.foodgate.dto.address.AddressAdminDto;
import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.entities.Address;
import com.webapp.foodgate.entities.criteria.AddressCriteria;
import com.webapp.foodgate.entities.criteria.MemberCriteria;
import com.webapp.foodgate.exception.BadRequestException;
import com.webapp.foodgate.exception.NotFoundException;
import com.webapp.foodgate.form.address.CreateNewAddress;
import com.webapp.foodgate.form.address.UpdateAddressForm;
import com.webapp.foodgate.mapper.AddressMapper;
import com.webapp.foodgate.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;

    /**
     * @param createNewAddress
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CREATE_ADDRESS + "')")
    public ApiMessageDto<String> create(@Valid @RequestBody CreateNewAddress createNewAddress, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Address address = new Address();

        address = addressMapper.fromCreateAddressFormToAddress(createNewAddress);
        addressRepository.save(address);

        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Create new address success");
        return apiMessageDto;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_ADDRESS + "')")
    public ApiMessageDto<AddressAdminDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<AddressAdminDto> apiMessageDto = new ApiMessageDto<>();
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            throw new NotFoundException("Not found address", ErrorCode.ADDRESS_ERROR_NOT_FOUND);
        }
        apiMessageDto.setData(addressMapper.fromAddressToAddressAdminDto(address));
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Get address succes");
        return apiMessageDto;
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_LIST_ADDRESS + "')")
    public ApiMessageDto<ResponseListDto<List<AddressAdminDto>>> list(AddressCriteria addressCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<List<AddressAdminDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Address> addressPage = addressRepository.findAll(addressCriteria.getSpecification(), pageable);
        ResponseListDto<List<AddressAdminDto>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(addressMapper.fromEntityListToAddressAdminDtoList(addressPage.getContent()));
        responseListObj.setTotalPages(addressPage.getTotalPages());
        responseListObj.setTotalElements(addressPage.getTotalElements());
        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list address success");
        return responseListObjApiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.UPDATE_ADDRESS + "')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateAddressForm updateAddressForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Address address = addressRepository.findById(updateAddressForm.getId()).orElse(null);
        if (address == null) {
            throw new NotFoundException("Not found address", ErrorCode.ADDRESS_ERROR_NOT_FOUND);
        }
        addressMapper.fromUpdateAddressFromToEntity(updateAddressForm, address);
        addressRepository.save(address);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Update address success");
        return apiMessageDto;
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.DELETE_ADDRESS + "')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            throw new NotFoundException("Not found address", ErrorCode.ADDRESS_ERROR_NOT_FOUND);
        }
        addressRepository.deleteById(id);
        apiMessageDto.setMessage("Delete address success");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }
}
