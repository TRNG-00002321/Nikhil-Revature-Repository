package com.revature.Allure;
public class PaymentService
{

    public PaymentResult process(PaymentRequest request)
    {
        return new PaymentResult("SUCCESS", "TXN-12345");
    }
}
