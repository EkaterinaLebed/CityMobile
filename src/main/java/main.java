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
        System.out.println("*OK*"+customer);

        System.out.println("------------------");
        Customer customer1 = new Customer();
        customer1.setName("000");
        customerDao.create(customer1);
        System.out.println(customerDao.selectAll());

        System.out.println("------------------");
        BillDao billDao= new BillDaoImpl();
        List<Bill> list = billDao.selectByPeriod(new GregorianCalendar(2017, 0, 1).getTime(), new GregorianCalendar(2017, 11, 31).getTime());
        System.out.println(list);

        System.out.println("------------------");
        PaymentDao paymentDao= new PaymentDaoImpl();
        List<Payment> list2 = paymentDao.selectByPeriod(new GregorianCalendar(2017, 0, 1).getTime(), new GregorianCalendar(2017, 11, 31).getTime());
        System.out.println(list2);
        System.out.println("------------------");

        AppSessionFactory.close();
    }

}