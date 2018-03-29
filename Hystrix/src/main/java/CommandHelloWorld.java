import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;


//A sample program to wrap the call in the run method.
public class CommandHelloWorld extends HystrixCommand<String> {
 
    private String name;
 
    CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }
 
    @Override
    protected String run() {
        return "Hello " + name + "!";
    }
    
    @Test
    public void givenInputBobAndDefaultSettings_whenCommandExecuted_thenReturnHelloBob(){
        assertTrue((new CommandHelloWorld("Bob").execute().equals("Hello Bob!")));
    }
}