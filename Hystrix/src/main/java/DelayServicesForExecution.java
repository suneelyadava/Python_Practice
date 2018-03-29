import static org.testng.Assert.assertTrue;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class DelayServicesForExecution {
	
	
	
	//Without Exception handled
	@org.testng.annotations.Test()
	public void givenSvcTimeoutOf15000AndExecTimeoutOf5000_whenRemoteSvcExecuted_thenExpectHre()
	  throws InterruptedException {
	 
	    HystrixCommand.Setter config = HystrixCommand
	      .Setter
	      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupTest5"));
	 
	    HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
	    commandProperties.withExecutionTimeoutInMilliseconds(5000);
	    config.andCommandPropertiesDefaults(commandProperties);
	 
	    assertTrue((new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(15000)).execute()).equals("Success"));
	}

}
