#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
##import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
#parse("File Header.java")

## 确保NAME首字母大写
#set ($validName = $NAME.substring(0, 1).toUpperCase() + $NAME.substring(1))
## 
## 确保以NAME以Controller结尾
#if (!$validName.endsWith("Controller"))
    #set ($validName = $validName + "Controller")
#else
    #set ($validName = $validName)
#end
## Model名称
#set ($modelName = $validName.replace("Controller", ""))
#set ($lowerModelName = $modelName.substring(0, 1).toLowerCase() + $modelName.substring(1))
## Model名称转化为snake分割（将所有大写字符前面加下划线，然后字符串转小写，最后删除第一个下划线）
#set ($regex = "([A-Z])") ## 匹配大写字母正则
#set ($replacement = "_$1") ## "_"，在匹配到的大写字母前加_
#set ($replacement1 = "/$1") ## "/"，在匹配到的大写字母前加/
#set ($requestMapping = $modelName.replaceAll($regex, $replacement1).toLowerCase().substring(1))
#set ($ServiceName = $modelName+"Service")
#set ($serviceName = $lowerModelName+"Service")

@RestController
@RequestMapping("/$requestMapping")
public class $validName {

    @Autowired
    private $ServiceName $serviceName;

    @PostMapping( "/" )
    public ResponseResult create(@RequestBody @Valid Void input) {
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping( "/{id}" )
    public ResponseResult get(@PathVariable @Valid String id) {
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @PutMapping( "/{id}" )
    public ResponseResult update(@PathVariable String id, @RequestBody @Valid Void input) {
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @DeleteMapping( "/{id}" )
    public ResponseResult delete(@PathVariable String id, @RequestBody @Valid Void input) {
        return new ResponseResult(CommonCode.SUCCESS);
    }

}