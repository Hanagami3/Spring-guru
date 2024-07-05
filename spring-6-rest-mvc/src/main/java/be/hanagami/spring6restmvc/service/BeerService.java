package be.hanagami.spring6restmvc.service;

import be.hanagami.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);
}
