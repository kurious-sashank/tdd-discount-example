package sas.tut.discount.second;

import sas.tut.discount.model.CustomerData;

public interface ICustomerDataService {
    public CustomerData findCustomerData(Long customerId);
}
