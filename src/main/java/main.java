import com.lea.mobile.app.AppSessionFactory;
import com.lea.mobile.dao.api.BillDao;
import com.lea.mobile.dao.api.CustomerDao;
import com.lea.mobile.dao.api.PaymentDao;
import com.lea.mobile.dao.daoimpl.BillDaoImpl;
import com.lea.mobile.dao.daoimpl.CustomerDaoImpl;
import com.lea.mobile.dao.daoimpl.PaymentDaoImpl;
import com.lea.mobile.entity.*;

import java.util.GregorianCalendar;
import java.util.List;

public class main {
    public static void main(String[] args){
        CustomerDao customerDao = new CustomerDaoImpl();
        Customer customer = customerDao.read(5);
        AppSessionFactory.close();
    }

}