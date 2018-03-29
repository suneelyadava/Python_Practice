import static org.testng.Assert.assertTrue;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.exception.HystrixRuntimeException;

public class ShortCircuitBreakerPattern {
	
	
	
	@org.testng.annotations.Test()
	public void givenCircuitBreakerSetup_whenRemoteSvcCmdExecuted_thenReturnSuccess()
	  throws InterruptedException {
	 
	    HystrixCommand.Setter config = HystrixCommand
	      .Setter
	      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupCircuitBreaker"));
	 
	    HystrixCommandProperties.Setter properties = HystrixCommandProperties.Setter();
	    properties.withExecutionTimeoutInMilliseconds(1000);
	    properties.withCircuitBreakerSleepWindowInMilliseconds(4000);
	    properties.withExecutionIsolationStrategy
	     (HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);
	    properties.withCircuitBreakerEnabled(true);
	    properties.withCircuitBreakerRequestVolumeThreshold(1);
	 
	    config.andCommandPropertiesDefaults(properties);
	    config.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
	      .withMaxQueueSize(1)
	      .withCoreSize(1)
	      .withQueueSizeRejectionThreshold(1));
	 
	    assertTrue((this.invokeRemoteService(config, 10000)).equals(null));
	    assertTrue((this.invokeRemoteService(config, 10000)).equals(null));
	    assertTrue((this.invokeRemoteService(config, 10000)).equals(null));
	 
	    Thread.sleep(5000);
	 
	    assertTrue((new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute()).equals("Success"));
	 
	    assertTrue((new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute()).equals("Success"));
	 
	    assertTrue((new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute()).equals("Success"));
	}
	
	public String invokeRemoteService(HystrixCommand.Setter config, int timeout)
			  throws InterruptedException {
			 
			    String response = null;
			 
			    try {
			        response = new RemoteServiceTestCommand(config,
			          new RemoteServiceTestSimulator(timeout)).execute();
			    } catch (HystrixRuntimeException ex) {
			        System.out.println("ex = " + ex);
			    }
			 
			    return response;
			}

}
