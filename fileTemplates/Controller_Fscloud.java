#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
##import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


## 确保NAME首字母大写
#set ($validName = $NAME.substring(0, 1).toUpperCase() + $NAME.substring(1))
## 确保以NAME以Controller结尾
#if (!$validName.endsWith("Controller"))
    #set ($validName = $validName + "Controller")
#end

## Model名称
#set ($modelName = $validName.replace("Controller", ""))
#set ($lowerModelName = $modelName.substring(0, 1).toLowerCase() + $modelName.substring(1))

## Model名称转化为snake分割（将所有大写字符前面加下划线，然后字符串转小写，最后删除第一个下划线）
#set ($regex = "([A-Z])") ## 匹配大写字母正则
#set ($underscore = "_$1") ## "_"，在匹配到的大写字母前加_
#set ($slash = "/$1") ## "/"，在匹配到的大写字母前加/
#set ($requestMapping = $modelName.replaceAll($regex, $slash).toLowerCase().substring(1))
#set ($serviceName = $modelName+"Service")
#set ($lowerServiceName = $lowerModelName+"Service")


#parse("File Header.java")
@Api(tags = "")
@RestController
@RequestMapping("/$requestMapping")
public class $validName {

    @Autowired
    private $serviceName $lowerServiceName;

   @PostMapping("addOrUpdate")
    @ApiOperation(value = "添加或者更新字段", notes = "添加或者更新字段")
    public OpenResponse addOrUpdateField(@RequestBody FieldDTO dto) {
        fieldService.addOrUpdateField(dto);
        return OpenResponse.ok();
    }

    @GetMapping("getField/{fieldId}")
    @ApiOperation(value = "获取字段", notes = "获取字段")
    public OpenResponse<FieldVO> getField(@PathVariable int fieldId) {
        FieldVO fieldVO = fieldService.getField(fieldId);
        return OpenResponse.ok(fieldVO);
    }

    @DeleteMapping( "/{id}" )
    public ResponseResult delete(@PathVariable String id, @RequestBody @Valid Void input) {
        return new ResponseResult(CommonCode.SUCCESS);
    }

}