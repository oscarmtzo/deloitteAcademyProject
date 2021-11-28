package com.example.deloitteAcademy.deloitteAcademy.repository;

import com.example.deloitteAcademy.deloitteAcademy.entity.TourPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "packages", path =
        "packages")
public interface TourPackageRepository extends
        CrudRepository<TourPackage, String> {
    Optional<TourPackage> findByCode(String code);
    List<TourPackage> findAll();
    Page<TourPackage> findAll(Pageable pageable);
    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends TourPackage> S save(S s);
    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends TourPackage> Iterable<S> saveAll(Iterable<S> iterable);
    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteById(String s);
    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void delete(TourPackage tourPackage);
    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends TourPackage> iterable);
    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteAll();
}