#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
import lombok.Getter;
#parse("File Header.java")
#set ($modelName=${NAME}+"Enum")
#set ($lowerModelName = $modelName.substring(0, 1).toLowerCase() + $modelName.substring(1))
@Getter
@AllArgsConstructor
public enum $modelName {
##UNKNOW(0, "未知"),
    MALE(1, "男"),
    FEMALE(2,"女"),
    ;
    #*private static final Map<Integer, TEnum> map = new HashMap<>();

    static {
        //Arrays.stream(values()).forEach(v -> map.put(v.getCode(), v));
        for (TEnum genderEnum : values()) {
            map.put(genderEnum.getCode(), genderEnum);
        }
    }
    public static TEnum codeOf(int code) {
        return map.get(code);
    }*#

    private final int code;
    private final String desc;

    public static String getDesc(Integer code) {
        return valueOf(code)==null ? null : valueOf(code).desc;
    }

    public static $modelName valueOf(Integer code) {
        return Arrays.stream(values())
                .filter(v -> v.getCode() == code)
                .findFirst()
                .orElse(null);//.orElseThrow(() -> new IllegalArgumentException("No such enum object for the code : " + code));
    }

    public static boolean containsValue(Integer code) {
##        return !ObjectUtils.isEmpty(valueOf(code));
        return valueOf(code)!=null;
##        return Arrays.stream(values())
##                .filter(v -> v.getCode() == code)
##                .findFirst()
##                .isPresent();
    }

##    public static String fromString(Integer code) {
##        $modelName $lowerModelName = Arrays.stream(values()).filter(v -> v.getCode() == code).findFirst().orElse(null);
##        return $lowerModelName == null ? null : $lowerModelName .getDesc();
##    }
}
