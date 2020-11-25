import com.jfinal.config.Constants;
import com.jfinal.ext.handler.ContextPathHandler;
import io.jboot.aop.jfinal.JfinalHandlers;
import io.jboot.core.listener.JbootAppListenerBase;

public class AppListen extends JbootAppListenerBase {
    @Override
    public void onConstantConfig(Constants me){
     me.setBaseUploadPath("E:/avatar");//设置文件上传存储路径
     me.setMaxPostSize(10485760);//设置上传文件大小的最大值,10M
    }
}
