package fm.douban.app.control;

import fm.douban.model.UserLoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "app")
public class UserControl {
    private static final Logger LOG = LoggerFactory.getLogger(UserControl.class);

    @Value("${loginmock.userName}")
    private String mockedName;

    @Value("${loginmock.password}")
    private String mockedPassword;

    @PostConstruct
    public void init() {
        LOG.info("UserControl 启动啦");
    }

    @GetMapping(path = "/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping(path = "/authenticate")
    @ResponseBody
    public Map login(@RequestParam String name, @RequestParam String password,
                         HttpServletRequest request, HttpServletResponse response) {
        Map returnData = new HashMap();
        if (mockedName.equals(name) && mockedPassword.equals(password)) {
            UserLoginInfo userLoginInfo = new UserLoginInfo();
            userLoginInfo.setUserId("123456789abcd");
            userLoginInfo.setUserName(name);
            // 取得 HttpSession 对象
            HttpSession session = request.getSession();
            // 写入登录信息
            session.setAttribute("userLoginInfo", userLoginInfo);
            returnData.put("result", true);
            returnData.put("message", "login successfule");
        } else {
            returnData.put("result", false);
            returnData.put("message", "userName or password not correct");
        }

        return returnData;
    }
}
