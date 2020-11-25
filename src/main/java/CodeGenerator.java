import com.jfinal.kit.PathKit;
import io.jboot.app.JbootApplication;
import io.jboot.codegen.model.JbootBaseModelGenerator;
import io.jboot.codegen.model.JbootModelGenerator;
import io.jboot.codegen.service.JbootServiceImplGenerator;
import io.jboot.codegen.service.JbootServiceInterfaceGenerator;

public class CodeGenerator {
    public static void main(String[] args){

        String modelPackage = "Model"; //生成的Model的包名uploadFile2
        String baseModelPackage = "Modelbase"; //生成的BaseModel的包名

        //Model存放的路径，一般情况下是 /src/main/java 下，如下是放在 test 目录下
        String modelDir = PathKit.getWebRootPath() + "/src/main/java/" + modelPackage.replace(".", "/");
        String baseModelDir = PathKit.getWebRootPath() + "/src/main/java/" + baseModelPackage.replace(".", "/");

        System.out.println("start generate...");
        System.out.println("generate dir:" + modelDir);

        //开始生成 Model 和 BaseModel 的代码
        new JbootBaseModelGenerator(baseModelPackage, baseModelDir).setGenerateRemarks(true).generate();
        new JbootModelGenerator(modelPackage, baseModelPackage, modelDir).generate();

        String servicePackage = "Service"; // service 层的接口包名
        String serviceImplPackage = "Service.provider"; // service 层的接口实现类包名


        //设置 service 层代码的存放目录
        String serviceOutputDir = PathKit.getWebRootPath() + "/src/main/java/" + servicePackage.replace(".", "/");
        String serviceImplOutputDir = PathKit.getWebRootPath() + "/src/main/java/" + serviceImplPackage.replace(".", "/");


        //开始生成代码
        new JbootServiceInterfaceGenerator(servicePackage, serviceOutputDir, modelPackage).generate();
        new JbootServiceImplGenerator(servicePackage, serviceImplOutputDir, modelPackage).setImplName("provider").generate();

    }
}
