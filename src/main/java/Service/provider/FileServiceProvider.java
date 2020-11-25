package Service.provider;

import io.jboot.aop.annotation.Bean;
import Service.FileService;
import Model.File;
import io.jboot.service.JbootServiceBase;


@Bean
public class FileServiceProvider extends JbootServiceBase<File> implements FileService {

}