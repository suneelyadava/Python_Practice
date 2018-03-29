
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

//Demonstration of Interupted services while running remote services 

public class RemoteServiceTestCommand extends HystrixCommand<String> {
 
    private RemoteServiceTestSimulator remoteService;
 
     RemoteServiceTestCommand(Setter config, RemoteServiceTestSimulator remoteService) {
        super(config);
        this.remoteService = remoteService;
    }
 
    @Override
    protected String run() throws Exception {
        return remoteService.execute();
    }
    
    @Test
    public void givenSvcTimeoutOf100AndDefaultSettings_whenRemoteSvcExecuted_thenReturnSuccess()
      throws InterruptedException {
    	
     
        HystrixCommand.Setter config = HystrixCommand
          .Setter
          .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroup2"));
        
         
        assertTrue((new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(10)).execute()).equals("Success"));
    }
}