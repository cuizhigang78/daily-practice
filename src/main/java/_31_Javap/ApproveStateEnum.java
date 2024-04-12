package _31_Javap;

import java.util.HashMap;
import java.util.Map;

/**
 * ��Java�У�ö�����͵ı����Ǵ洢�ڶ��ڴ��еġ�ö�����͵ı���ʵ������ö�����͵�һ��ʵ����
 * ����Java�У����еĶ������ڶ��ڴ��д洢�ġ�ö�����͵�ÿһ���ض�ֵ���Ǹ�ö�����͵�һ��ʵ����
 * ��Щʵ����ö���౻����ʱ�������������������������ڼ䶼���ڣ���������Ǵ洢�ڶ��ڴ��еġ�
 */
public enum ApproveStateEnum {

    /**
     * δ����
     */
    NOT_SET(0, "δ����"),
    /**
     * ������
     */
    PUSHING(1, "������"),
    /**
     * ����ͨ��
     */
    SUCCESS(2, "����ͨ��"),
    /**
     * ��������
     */
    FAIL(3, "��������"),

    /**
     * ������
     */
    WAITING_CONFIGURATION(4, "������"),

    /**
     * �ѷ���
     */
    WASTE(5, "�ѷ���");

    /**
     * ��ȡCodeֵ
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * ��ȡ����
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




