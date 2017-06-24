package com.lea.mobile.dao.api;

import com.lea.mobile.entity.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentDao extends GenericDao<Payment> {
    List<Payment> selectByPeriod(Date startDate, Date endDate);
}
