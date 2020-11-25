package Controller.tree;
import Model.Tree;
import Service.TreeService;
import com.jfinal.aop.Inject;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("/tree")
public class TreeController extends JbootController {
    @Inject
    TreeService treeService;
    public  void index() throws ParserConfigurationException, SAXException, IOException {
        //1、创建解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2、从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        //3、加载文档Document注册处理器
        ParsHandler parsHandler = new ParsHandler();
        //4、解析
        parser.parse(new File("E:\\IDEA\\Jboot02\\src\\main\\resources\\tree.xml"), parsHandler);
        //5、将数据保存到数据库
        for (int m = 0; m < parsHandler.getTrees().size(); m++) {
            int id = parsHandler.getTrees().get(m).getId();
            String name = parsHandler.getTrees().get(m).getName();
            int pid = parsHandler.getTrees().get(m).getPid();
            //取出数据库中所有的数据
            List<Tree> allTree = treeService.findAll();
            //判断xml中的取到的数据是否在数据库中已存在
            boolean flag =  allTree.contains(parsHandler.getTrees().get(m));
            if(flag==false){
                //将不存在数据库中的数据插入到数据库中
                Tree t = new Tree().set("id",id).set("name",name).set("pid",pid);
                treeService.save(t);
            }
        }
        render("/tree/tree.html");
    }
}
