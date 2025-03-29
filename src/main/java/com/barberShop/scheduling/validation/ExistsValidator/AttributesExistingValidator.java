package com.barberShop.scheduling.validation.ExistsValidator;

public interface AttributesExistingValidator <T>{
    void validatePreSave(T request);

}
