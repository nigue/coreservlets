package courses.hibernate.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import courses.hibernate.service.AccountOwnerService;
import courses.hibernate.service.AccountService;
import courses.hibernate.service.AccountTransactionService;
import courses.hibernate.service.EBillService;
import courses.hibernate.service.EBillerService;

/**
 * Spring utility class to look up beans
 */
public class SpringUtil {

	private static BeanFactory beanFactory;
	static {
		try{
			ClassPathResource resource = new ClassPathResource(
				"applicationContext.xml");
			beanFactory = new XmlBeanFactory(resource);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Get account service
	 * 
	 * @return account service
	 */
	public static AccountService getAccountService() {
		return (AccountService) beanFactory.getBean("accountService");
	}

	/**
	 * Get account owner service
	 * 
	 * @return account owner service
	 */
	public static AccountOwnerService getAccountOwnerService() {
		return (AccountOwnerService) beanFactory.getBean("accountOwnerService");
	}

	/**
	 * Get account transaction service
	 * 
	 * @return account transaction service
	 */
	public static AccountTransactionService getAccountTransactionService() {
		return (AccountTransactionService) beanFactory
				.getBean("accountTransactionService");
	}

	/**
	 * Get ebill service
	 * 
	 * @return ebill service
	 */
	public static EBillService getEBillService() {
		return (EBillService) beanFactory.getBean("ebillService");
	}

	/**
	 * Get ebiller service
	 * 
	 * @return ebiller service
	 */
	public static EBillerService getEBillerService() {
		return (EBillerService) beanFactory.getBean("ebillerService");
	}
}
