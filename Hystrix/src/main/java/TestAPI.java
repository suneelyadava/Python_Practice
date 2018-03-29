
import java.util.GregorianCalendar;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import javax.xml.datatype.XMLGregorianCalendar;




public class TestAPI {
	
	
	public static void main(String[] args)  {
		
		XMLGregorianCalendar startDate=null;
		XMLGregorianCalendar endDate=null;
		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		try {
			endDate  = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		} catch (DatatypeConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
				 gcal.add(GregorianCalendar.DATE, -3);
			      XMLGregorianCalendar xgcal;
				try {
					startDate = DatatypeFactory.newInstance()
					        .newXMLGregorianCalendar(gcal);
					
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(startDate);
				System.out.println(endDate);
		
		
		
	}
	

}
