package Controller.tree;

import Model.Tree;
import Service.TreeService;
import com.jfinal.aop.Inject;
import io.jboot.db.model.Columns;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/tree/tableData")
public class TreeTableController extends JbootController {
    @Inject
    private TreeService treeService;
    private int id;
    public void index(){
        id = getParaToInt("id");
        if(id==0){
            List<Tree> trees = treeService.findAll();//查询所有树节点
            renderJson(trees);
        }else{
            //System.out.println("收到的id为："+id);
            List<Tree>  treesList = treeService.findListByColumns(Columns.create("pid",id));
            renderJson(treesList);
        }
    }
}