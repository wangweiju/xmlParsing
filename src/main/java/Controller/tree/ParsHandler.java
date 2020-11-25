package Controller.tree;

import Model.Tree;
import Service.TreeService;
import com.jfinal.aop.Inject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;
public class ParsHandler extends DefaultHandler {
    @Inject
    private TreeService treeService;
    private String tag;
    private List<Tree> trees;
    private Tree tree;
    /**
     *文档解析开始调用
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("startDocument------开始解析");
        trees = new ArrayList<>();
    }
    /**
     * 节点解析开始调用
     * @param uri ： 命名空间的url
     * @param localName : 标签的名称
     * @param qName ： 带命名空间的标签名称
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri,localName,qName,attributes);
        System.out.println("startElement-------元素名为：" + qName);
        if("tree".equals(qName)){//是一个树
            for(int i = 0;i<attributes.getLength();i++){
                System.out.println("attributes.name:"+attributes.getLocalName(i)+"attributes.value:"+attributes.getValue(i));
                tree = new Tree();
                if("id".equals(attributes.getLocalName(i))){
                    tree.setId(Integer.parseInt(attributes.getValue(i)));
                }
            }
        }
        tag = qName;
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch,start,length);
        String value = new String(ch,start,length);
        System.out.println("characters--------" + value);
        if ("name".equals(tag)) {
            tree.setName(value);
        }else if ("pid".equals(tag)){
            tree.setPid(Integer.parseInt(value));
        }
    }
    /**
     * 节点解析结束时调用
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri,localName,qName);
        System.out.println("endElement-------" + qName);
        if("tree".equals(qName)){
            trees.add(tree);
            tree = null;
        }
        tag = null;
    }
    /**
     *文档解析结束时调用
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("endDocument-------");
        System.out.println("文档解析结束！！！");
        //将数据保存到数据库中
        System.out.println("保存的数据为：" + trees);
        System.out.println("-----------将保存到List中的数据插入到数据库中----------");
    }
    /*
    返回解析的数据
     */
    public List<Tree> getTrees(){
        return trees;
    }
}