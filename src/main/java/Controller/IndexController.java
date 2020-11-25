package Controller;

import Model.User;
import Service.UserService;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.app.JbootApplication;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/")
public class IndexController extends JbootController {
    @Inject
    private UserService userService;
    public void index() {
        renderText("你好我们又见面了！");
    }
    //测试数据库
    public void dbtest() {
        List<Record> records = Db.find("select * from user");
        renderText(Arrays.toString(records.toArray()));
    }
    public void users() {
        //这里用到了userService的查询方法
        List<User> users = userService.findAll();
        renderText(Arrays.toString(users.toArray()));
    }
    public static void main(String[] args) {
        JbootApplication.run(args);
    }
}