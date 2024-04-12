package _31_Javap;

import java.util.HashMap;
import java.util.Map;

/**
 * 在Java中，枚举类型的变量是存储在堆内存中的。枚举类型的变量实际上是枚举类型的一个实例，
 * 而在Java中，所有的对象都是在堆内存中存储的。枚举类型的每一个特定值都是该枚举类型的一个实例，
 * 这些实例在枚举类被加载时创建，并且在整个程序运行期间都存在，因此它们是存储在堆内存中的。
 */
public enum ApproveStateEnum {

    /**
     * 未设置
     */
    NOT_SET(0, "未设置"),
    /**
     * 待审批
     */
    PUSHING(1, "待审批"),
    /**
     * 审批通过
     */
    SUCCESS(2, "审批通过"),
    /**
     * 审批驳回
     */
    FAIL(3, "审批驳回"),

    /**
     * 待配置
     */
    WAITING_CONFIGURATION(4, "待配置"),

    /**
     * 已废弃
     */
    WASTE(5, "已废弃");

    /**
     * 获取Code值
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取描述
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    private final Integer code;
    private final String description;

    ApproveStateEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private static final Map<Integer, ApproveStateEnum> MAPPINGS = new HashMap<>(16);

    static {
        for (ApproveStateEnum enums : values()) {
            MAPPINGS.put(enums.getCode(), enums);
        }
    }

    public static ApproveStateEnum getEnum(Integer code) {
        return (code != null ? MAPPINGS.get(code) : null);
    }

}




