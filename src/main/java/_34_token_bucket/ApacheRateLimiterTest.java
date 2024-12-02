package _34_token_bucket;

import org.apache.commons.lang3.concurrent.TimedSemaphore;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <dependency>
 *     <groupId>org.apache.commons</groupId>
 *     <artifactId>commons-lang3</artifactId>
 *     <version>3.12.0</version>
 * </dependency>
 */
public class ApacheRateLimiterTest {

    private static final int RATE_LIMIT = 60; // 60 requests per minute
    private final TimedSemaphore semaphore;

    public ApacheRateLimiterTest() {
        this.semaphore = new TimedSemaphore(1, TimeUnit.MINUTES, RATE_LIMIT);
    }

    public static void main(String[] args) {
        ApacheRateLimiterTest service = new ApacheRateLimiterTest();
        List<String> accountNumbers = Stream.iterate(1, i -> i + 1).map(i -> "Account-" + i).limit(100).collect(Collectors.toList());
        service.fetchAccountBalances(accountNumbers);
    }

    public void fetchAccountBalances(List<String> accountNumbers) {
        for (String accountNumber : accountNumbers) {
            try {
                semaphore.acquire(); // Acquire a permit before making the request
                // Call the remote API to get the account balance
                String balance = getAccountBalance(accountNumber);
                System.out.println("Account: " + accountNumber + ", Balance: " + balance);
            } catch (InterruptedException e) {
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