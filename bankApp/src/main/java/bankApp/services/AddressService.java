package bankApp.services;

import bankApp.DTOs.AddressDTO;
import bankApp.entities.Address;
import bankApp.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(UUID addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(UUID addressId, Address addressDetails) {
        Address address = addressRepository.findById(addressId).orElse(null);

        if (address != null) {
            address.setCountry(addressDetails.getCountry());
            address.setSector(addressDetails.getSector());
            address.setCity(addressDetails.getCity());
            address.setStreet(addressDetails.getStreet());
            address.setNumber(addressDetails.getNumber());
            address.setZip(addressDetails.getZip());

            return addressRepository.save(address);
        } else {
            return null;
        }
    }

    public boolean deleteAddress(UUID addressId) {
        Address address = addressRepository.findById(addressId).orElse(null);

        if (address != null) {
            addressRepository.delete(address);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Address> getAddressByUserDetailsId(UUID userDetailsId) {
        return addressRepository.getByUserDetailsId(userDetailsId);
    }

    public static AddressDTO convertAddressToDTO(Address address) {
        return new AddressDTO(
                address.getCountry(),
                address.getSector(),
                address.getCity(),
                address.getStreet(),
                address.getNumber(),
                address.getZip()
        );
    }
}
