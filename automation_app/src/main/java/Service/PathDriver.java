package Service;

import Bean.SystemConfig;
import java.io.File;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ConstantVariable.Constant;
import Views.JFrame;
import org.springframework.stereotype.Component;

@Service
public class PathDriver {

    @Autowired
    SystemConfig SystemCofig;
    public static String webDriverFirefox;
    public static String dirDriverFirefox;

    public static String webDriverGoogle;
    public static String dirDriverGoogle;

    public PathDriver() {

    }

    public String getPath() {
        try {
//            String realpath = System.getProperty("user.dir");
            String realpath = JFrame.path_driver;
            String[] temp;
            switch (SystemCofig.os) {
                case "Linux":
                    temp = realpath.split("apache-tomcat-8.0.46", 2);

                    return temp[0];

                case "Windows":
                    temp = realpath.split("target", 2);
                    return temp[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setPathGoogle() {
        String realpath = getPath();
        webDriverGoogle = "webdriver.chrome.driver";
        switch (SystemCofig.os) {
            case "Linux":

                dirDriverGoogle = Constant.dirDriverGoogleHeroku;

                break;
            case "Windows":
                dirDriverGoogle = realpath;
                break;
        }

    }

    public void setPathFireFox() {
        String realpath = getPath();
        webDriverFirefox = "webdriver.gecko.driver";
        switch (SystemCofig.os) {
            case "Linux":
                dirDriverFirefox = Constant.dirDriverGoogleHeroku;
                break;
            case "Windows":
                dirDriverFirefox = realpath + File.separator + "geckodriver.exe";

                break;
        }

    }

}
