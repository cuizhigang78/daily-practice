package _102_ai_cloud;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

/**
 * @ClassName Main
 * @Author Maxwell
 * @Date 2022/8/21 17:06
 * @Description Main
 * @Version 1.0
 */
public class AuthorizationGenerator {

    // 对接方：credit-finance-001
    //     对接密钥：vcredit-qwen25-bdde57e73d3a57b4258d1d6511025f6b
    //
    // 对接方：credit-finance-002
    //     对接密钥：vcredit-ds-81cee8b389ad33c40db38077cb09f6ba
    private static final Map<String, String> CLIENT_SECRET_MAP = Map.of(
            "credit-finance-001", "vcredit-qwen25-bdde57e73d3a57b4258d1d6511025f6b",
            "credit-finance-002", "vcredit-ds-81cee8b389ad33c40db38077cb09f6ba"
    );

    public static String generateAuthorization(String clientId) {
        String clientSecret = CLIENT_SECRET_MAP.get(clientId);
        if (clientSecret == null) {
            throw new IllegalArgumentException("Invalid clientId: " + clientId);
        }
        String time = System.currentTimeMillis() + "";
        String digest = clientId + time + clientSecret;
        String md5 = DigestUtils.md5Hex(digest);
        return time + "|" + clientId + " " + md5;
    }

    public static void main(String[] args) {
        String clientId = "credit-finance-001";
        String authorization = generateAuthorization(clientId);
        System.out.println("Authorization: " + authorization);

        // 测试另一个clientId
        clientId = "credit-finance-002";
        authorization = generateAuthorization(clientId);
        System.out.println("Authorization: " + authorization);
    }
}