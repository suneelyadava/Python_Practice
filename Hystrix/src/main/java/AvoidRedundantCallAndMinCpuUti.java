import static org.testng.Assert.assertTrue;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class AvoidRedundantCallAndMinCpuUti {
	
/*The application doesn’t know if the remote service is healthy or not and new threads are spawned every time a request comes in. 
 * This will cause threads on an already struggling server to be used. We don’t want this to happen as we need 
 * these threads for other remote calls or processes running on our server and we also want to avoid CPU utilization spiking up.
 * */
	
	
	@org.testng.annotations.Test()
	public void givenSvcTimeoutOf500AndExecTimeoutOf10000AndThreadPool_whenRemoteSvcExecuted_thenReturnSuccess() throws InterruptedException {
	 
	    HystrixCommand.Setter config = HystrixCommand
	      .Setter
	      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupThreadPool"));
	 
	    HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
	    commandProperties.withExecutionTimeoutInMilliseconds(10000);
	    config.andCommandPropertiesDefaults(commandProperties);
	    config.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
	      .withMaxQueueSize(10)
	      .withCoreSize(3)
	      .withQueueSizeRejectionThreshold(10));
	 
	    assertTrue((new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute()).equals("Success"));
	}

}



