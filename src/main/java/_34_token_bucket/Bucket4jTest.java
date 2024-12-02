package _34_token_bucket;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.Duration.ofMinutes;

/**
 * <dependency>
 *     <groupId>com.github.vladimir-bukhtoyarov</groupId>
 *     <artifactId>bucket4j-core</artifactId>
 *     <version>7.0.0</version>
 * </dependency>
 */
public class Bucket4jTest {

    private static final int RATE_LIMIT = 60; // 60 requests per minute
    private final Bucket bucket;

    public Bucket4jTest() {
        Bandwidth limit = Bandwidth.classic(RATE_LIMIT, Refill.greedy(RATE_LIMIT, ofMinutes(1)));
        this.bucket = Bucket4j.builder().addLimit(limit).build();
    }

    public static void main(String[] args) {
        Bucket4jTest service = new Bucket4jTest();
        // 添加100个账户
        List<String> accountNumbers = Stream.iterate(1, i -> i + 1).map(i -> "Account-" + i).limit(100).collect(Collectors.toList());
        service.fetchAccountBalances(accountNumbers);
    }

    public void fetchAccountBalances(List<String> accountNumbers) {
        for (String accountNumber : accountNumbers) {
            if (bucket.tryConsume(1)) { // Try to consume a token before making the request
                try {
                    // Call the remote API to get the account balance
                    String balance = getAccountBalance(accountNumber);
                    System.out.println("Account: " + accountNumber + ", Balance: " + balance);
                } catch (Exception e) {
                    System.err.println("Failed to fetch balance for account: " + accountNumber);
                    e.printStackTrace();
                }
            } else {
                System.err.println("Rate limit exceeded. Skipping account: " + accountNumber);
            }
        }
    }

    private String getAccountBalance(String accountNumber) {
        // Simulate the remote API call
        // Replace this with the actual API call
        return "100.00";
    }
}