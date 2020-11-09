#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
import java.io.Serializable;
import lombok.Data;
#parse("File Header.java")

## 确保NAME首字母大写
#set ($tableNmae = $NAME.substring(0, 1).toLowerCase() + $NAME.substring(1))

@Data
@ToString
@Entity( name = "$tableNmae" )
##@GenericGenerator( name = "jpa-uuid", strategy = "uuid" )
public class $NAME implements Serializable {
    private static final long serialVersionUID = 1L;

} 