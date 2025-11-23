package com.nekospace.nekospace.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekospace.nekospace.model.usuario.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

}
