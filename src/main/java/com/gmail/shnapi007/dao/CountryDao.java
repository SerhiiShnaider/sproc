package com.gmail.shnapi007.dao;

import com.gmail.shnapi007.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao extends JpaRepository<Country, Long> {

}
