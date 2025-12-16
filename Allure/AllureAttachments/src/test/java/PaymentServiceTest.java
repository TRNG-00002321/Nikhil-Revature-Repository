import com.revature.Allure.PaymentRequest;
import com.revature.Allure.PaymentResult;
import com.revature.Allure.PaymentService;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentServiceTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final PaymentService paymentService = new PaymentService();

    @Test
    @Description("Test payment processing with detailed logging")
    void processPayment_logsAllDetails() throws Exception {
        PaymentRequest request = new PaymentRequest("4111111111111111", 100.00);

        attachJson("Payment Request", mapper.writeValueAsString(request));

        PaymentResult result = paymentService.process(request);

        attachJson("Payment Response", mapper.writeValueAsString(result));
        attachText("Transaction Log", getTransactionLog());

        assertEquals("SUCCESS", result.getStatus());
    }

    @Attachment(value = "{name}", type = "application/json")
    String attachJson(String name, String json) {
        return json;
    }

    @Attachment(value = "{name}", type = "text/plain")
    String attachText(String name, String text) {
        return text;
    }

    private String getTransactionLog() {
        return """
            [INFO] Payment initiated
            [INFO] Card validated
            [INFO] Amount authorized
            [INFO] Payment completed
            """;
    }
}
