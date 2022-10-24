package com.example.pr2.repo;

import com.example.pr2.Models.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {
    List<Address> findByAddreses(String addreses);
}
