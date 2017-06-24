package com.lea.mobile.dao.api;

import com.lea.mobile.entity.Bill;

import java.util.Date;
import java.util.List;

public interface BillDao extends GenericDao<Bill> {
    List<Bill> selectByPeriod(Date startDate, Date endDate);
}
