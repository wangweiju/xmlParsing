package Service.provider;

        import com.jfinal.plugin.activerecord.Page;
        import io.jboot.aop.annotation.Bean;
        import Service.UserService;
        import Model.User;
        import io.jboot.service.JbootServiceBase;


@Bean
public class UserServiceProvider extends JbootServiceBase<User> implements UserService {
}