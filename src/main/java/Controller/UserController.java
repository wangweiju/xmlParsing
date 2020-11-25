package Controller;

import Model.User;
import Service.UserService;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/user")
public class UserController extends JbootController {
    @Inject
    private UserService userService;
    public void index(){
        int page = getParaToInt("page",1);
        Page<User> userPage = userService.paginate(page,10);
        setAttr("pageData", userPage);
    render("/user/user.html");
    }
    public void doSave() {
        String name = getPara("name");
        String password = getPara("password");

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        user.save();

        redirect("/user");
    }
    public void toadd(){

        render("/user/add.html");
    }
    public void del(){
        long id = getParaToLong("id");
        userService.deleteById(id);
        redirect("/user");
    }
    public void toupdate(){
        int id = getParaToInt("id");
        User user =  userService.findById(id);
        System.out.println("查询出的信息："+user);
        setAttr("user", user);
        render("/user/update.html");
    }
    public void dosave2(){
        String name = getPara("name");
        String password = getPara("password");

        int id = getParaToInt("id");
        System.out.println("需要修改的ID："+id);

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setId(id);
        user.update();
        redirect("/user");
    }
}
