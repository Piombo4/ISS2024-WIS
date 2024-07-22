package unibo.sprint3_progetto_ssgui;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.utils.CommUtils;

 

@Controller
public class SSGUIController {
    protected String sysName = "unknown";
    String protocol="tcp";
 
    @Value("${spring.application.name}")
    String appName;  //vedi application.properties

    protected String mainPage     = "SSGUI";
    protected ApplguiCore guiCore ;

    public SSGUIController(){
        CommUtils.outgreen (" --- SSGUIController | STARTS " );
        new FacadeBuilder( ) ;
        guiCore = FacadeBuilder.guiCore;
    }

    @GetMapping("/")
    public String homePage(Model viewmodel) {
        viewmodel.addAttribute("appname", ApplSystemInfo.appName);
        String dir = System.getProperty("user.dir");
        CommUtils.outgreen (" --- Robotfacade24Controller | entry dir= "+dir  );
        return mainPage;
    }

    @ExceptionHandler
    public ResponseEntity handle(Exception ex) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity(
             "BaseController ERROR " + ex.getMessage(),
             responseHeaders, HttpStatus.CREATED);
    }

}
/*
 * curl --location --request POST 'http://localhost:8080/move' --header 'Content-Type: text/plain' --form 'move=l'
 * curl -d move=r localhost:8080/move
 */