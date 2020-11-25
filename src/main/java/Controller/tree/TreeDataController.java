package Controller.tree;

import Model.Tree;
import Service.TreeService;
import com.jfinal.aop.Inject;
import io.jboot.db.model.Columns;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/tree/treeData")
public class TreeDataController  extends JbootController {
    @Inject
    private TreeService treeService;
    public Object index(){
        List<Tree> trees = treeService.findAll();//查询所有树节点
        List<HashMap<String,Object>> TreeData = new ArrayList<>();//定义一个map处理json
        System.out.println("查询出的所有节点为："+trees);
        return fun(trees,TreeData);
    }
    private Object fun(List<Tree> trees, List<HashMap<String,Object>> TreeData){
        for(Tree t : trees){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",t.getId());
            map.put("title",t.getName());
            List<HashMap<String,Object>> result1 = new ArrayList<>();
            int id = t.getId();
            List<Tree> children = treeService.findListByColumns(Columns.create("pid",id));
            if(children!=null){
                System.out.println("查出的children为"+children);
                map.put("children",fun(children,result1));
            }
            TreeData.add(map);
        }
        System.out.println("最后的数据为："+TreeData);
        System.out.println("最后的TreeData的大小为："+TreeData.size());
        return TreeData;
    }
}
