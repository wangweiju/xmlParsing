import io.jboot.web.directive.JbootPaginateDirective;
import io.jboot.web.directive.annotation.JFinalDirective;

@JFinalDirective("myPaginate")
public class MyPaginateDirective extends JbootPaginateDirective {

    protected String getPageAttrName() {
        return "pageData"; //这个值要和Controller里setAttr的第一个参数值相同
    }
}