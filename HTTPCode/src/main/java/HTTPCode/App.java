package HTTPCode;

/**
 * Hello world!
 *
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v117.network.Network;
import org.openqa.selenium.devtools.v117.network.model.Request;
import org.openqa.selenium.devtools.v117.network.model.Response;

import io.github.bonigarcia.wdm.WebDriverManager;
public class App 
{
    public static void main( String[] args )
    {	
        WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().clearResolutionCache();
        WebDriver driver = new ChromeDriver();
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.addListener(Network.requestWillBeSent(),
        		request ->{
        			Request req = request.getRequest();
        			System.out.println(req.getUrl());
//        			System.out.println(req.getHeaders());
        		}
        		);
        devTools.addListener(Network.responseReceived(),
                response -> {
                    Response res = response.getResponse();
//                    System.out.println(res.getHeaders());
//                    System.out.println(res.getUrl());
                    System.out.println(res.getStatus());
                });       
        driver.get("https://telugu.timesnownews.com/");

    }
}
