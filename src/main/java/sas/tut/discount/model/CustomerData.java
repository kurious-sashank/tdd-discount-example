package sas.tut.discount.model;

import java.math.BigDecimal;

public class CustomerData {
    private Long id;
    private Integer numberMonthsSinceRegistration;
    private BigDecimal totalPurchaseValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberMonthsSinceRegistration() {
        return numberMonthsSinceRegistration;
    }

    public void setNumberMonthsSinceRegistration(Integer numberMonthsSinceRegistration) {
        this.numberMonthsSinceRegistration = numberMonthsSinceRegistration;
    }

    public BigDecimal getTotalPurchaseValue() {
        return totalPurchaseValue;
    }

    public void setTotalPurchaseValue(BigDecimal totalPurchaseValue) {
        this.totalPurchaseValue = totalPurchaseValue;
    }
}
