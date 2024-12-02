package _34_token_bucket;

import com.google.common.util.concurrent.RateLimiter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <dependency>
 *     <groupId>com.google.guava</groupId>
 *     <artifactId>guava</artifactId>
 *     <version>32.0.1-jre</version>
 * </dependency>
 */
public class GuavaRateLimiterTest {

    private static final int RATE_LIMIT = 60; // 60 requests per minute
    private static final RateLimiter rateLimiter = RateLimiter.create(RATE_LIMIT / 6.0); // 1 request per second

    public static void main(String[] args) {
        // 添加100个账户
        List<String> accountNumbers = Stream.iterate(1, i -> i + 1).map(i -> "Account-" + i).limit(100).collect(Collectors.toList());
        fetchAccountBalances(accountNumbers);
    }

    public static void fetchAccountBalances(List<String> accountNumbers) {
        for (String accountNumber : accountNumbers) {
            double acquire = rateLimiter.acquire();// Acquire a permit before making the request
            try {
                // Call the remote API to get the account balance
                String balance = getAccountBalance(accountNumber);
                System.out.println("Account: " + accountNumber + ", Balance: " + balance);
            } catch (Exception e) {
                System.err.println("Failed to fetch balance for account: " + accountNumber);
                e.printStackTrace();
            }
        }
    }

    private static String getAccountBalance(String accountNumber) {
        // Simulate the remote API call
        // Replace this with the actual API call
        return "100.00";
    }
}
