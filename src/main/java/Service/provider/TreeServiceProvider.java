package Service.provider;

import io.jboot.aop.annotation.Bean;
import Service.TreeService;
import Model.Tree;
import io.jboot.service.JbootServiceBase;


@Bean
public class TreeServiceProvider extends JbootServiceBase<Tree> implements TreeService {

}