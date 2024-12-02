package _34_token_bucket;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * <dependency>
 *     <groupId>io.github.resilience4j</groupId>
 *     <artifactId>resilience4j-ratelimiter</artifactId>
 *     <version>1.7.1</version>
 * </dependency>
 */
public class Resilience4jTest {

    private static final int RATE_LIMIT = 60; // 60 requests per minute
    private final RateLimiter rateLimiter;

    public Resilience4jTest() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(RATE_LIMIT)
                .limitRefreshPeriod(Duration.ofMinutes(1))
                .timeoutDuration(Duration.ofSeconds(1))
                .build();
        RateLimiterRegistry registry = RateLimiterRegistry.of(config);
        rateLimiter = registry.rateLimiter("resilience4jTest");
    }

    public static void main(String[] args) {
        Resilience4jTest service = new Resilience4jTest();
        // 添加100个账户
        List<String> accountNumbers = Stream.iterate(1, i -> i + 1).map(i -> "Account-" + i).limit(100).collect(Collectors.toList());
        service.fetchAccountBalances(accountNumbers);
    }

    public void fetchAccountBalances(List<String> accountNumbers) {
        for (String accountNumber : accountNumbers) {
            rateLimiter.acquirePermission(); // Acquire a permit before making the request
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

    private String getAccountBalance(String accountNumber) {
        // Simulate the remote API call
        // Replace this with the actual API call
        return "100.00";
    }
}