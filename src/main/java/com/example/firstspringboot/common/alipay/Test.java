package com.example.firstspringboot.common.alipay;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

@Slf4j
public class Test {


    public static void main(String[] args) throws Exception{
        String pubkey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsYfjWGvbFQ89IplTye/+rVa8oZBvH6x+hQQYWF2ncoduvZSL0FezLN+hk9h9TnHAq5sOHomnr8Hz77peeqDUJPjJJLz+Nad+bRc5QcAPEREBMso8FvZOUYmb1ZoPE3heKTgzFW16cx0XPVTZc/fkOVc9zU4rSeDjbdpPc5bVIZ7Uk5uyjATi3UdmlhUEjZQb0LDGKauYbcJ8tPYgnWh+/1QWUV/0AiWvZSAo9chPLda2DctaEcFou/W2kA/HK60TNL1HzgMZSfShoXfomxJIaSOTwT0BeL7Gkw4zjD/AqfyiYCiwzOpNTLT6oX+FqmXwvJxar15kqggLL4Ke8tNI/QIDAQAB";
        String prikey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUbscpo605LV8J7EQ4b68aebURF0wJB8c5qwyEGYGFZi8NbaXXInbS4CU+V5d0OqMGYUOJkZWatuxpuYg2Y7bo6PLbWJfoBnYs5egt2Xisilkt9kAPWd2kIHeEMbYk30hUcBxI3syvdKiB24kRw45p3CRsy10AUMJdxjiKxErSrJw3uURcjN7Utwt7EsGb/shk5TI0X3XNI8yLvPaO/tIN3IStUDXHc/4VzkNN3Pt0Di4q62mIMDmQu4U5dO/9qJR3rtipOh7iR459SGMm6fvbYnirog831A5yTqRPe2EXaRiI+oKQ+48ER12hQk7FlGXBHGWbMKsXDDxU6jN2jnoFAgMBAAECggEAa1Ywb8wM1+wXbfWM38LjvSGF7nYKZf1Z9DndMym+n9jk7bD83DXv905foFV3EHXWriZn0HOW2+qry0h/V9WDz6mk9Tl7oC4MByu6Wm2I9jyVgMcyS+eQf796rf3E2aUdiPIJgiibm8WglUdMN6xSr/qh3IqzFidQZr+Q48hO00kk1AkFORHjIwWDCW1ftzTVCAyGMigt7mukSt0SBjJ23XBbcP9A3M1HUvImO8/jwYXp+8FSekDhHsMru6DSXYlmHJXRDETwG61BkBKauCi6q9V+QON4zWAncHgNJ6ULJ8O63CxylhmbVgnlwMLeVUHCVgprwAiWjLtAslw6nX5YAQKBgQDN7qxSYOpPqttFOj+uZfjRK4uO7yaM6wMnl5sHLfw9kyGpwdaUdjnAxokGwFpe7lUu3CfpAsl4RPUzB0IjwO4GwwqDq0cfd8tV1X1WH1L0tjV1F6wfMX8jXobTfQyD1SKX7AcN55K1BJT6pzAbqcd0X3d/Lu05zYqSzdRM7JCK1QKBgQC4hU10/yPgSwAKn6JHvAx32IrTKJ+V9o8r1S8WxcLP2yUG/adXLuzzU3RKTkxWP6nliweHzhG8HLwQ3Omaoujb+ibwCF/qq35PfKSeV/MO/Eth1ifBFvbFponF7wdlDf4PvRAR8nY9er39oCOpuwAnW1o3b5xXaplFXtOgZLFqcQKBgQCq4Kpy7OJg2wh5qDzhTNIrVvPF1iHp+rSybLWCPqE4wtbDEwooRPXlRZi8Vm5mqWNbr+kaC2r79lR0VEiIfHXGfJ9D5KLutKuv/Ke1B+cBEhTHpa34cEzsH2rHINN6rSe4Ymz0F7z/od6xInvj6HyNzw3MJjdIqIqdCFhqwD2sRQKBgQCZs7iMr4bz66WSM90awgLkxcUGFb2xTlt+WKtL3dnkCJi0VRBSqC0Kl0a0Krqd0ZQ1N/VC452UQ59IsKwKeo9bxekvYtp7APVLQvlhSMOXhQNJ8PjWd8mBZLM45F4ViG1MMOG1AXIKzjZ6kS2D0dhT8F2a9eaWTp5z0YSZA6dhkQKBgAsI02IC3BygZbTiFdbgWvqiham8qJ5jH5QNI1RHCVYsHQBYrgmafhmo3iQkBqItWp3df/vnZFXcc5fULXNBj8UAZ6KNNHzNPn2tbWtlTaS2GCuCN7vPxVjGnm7pT8S52c3nZlBj79GJQrhM+i5+vlPOmxQH/dcUdEUvGmza28ht";

        String ceshi = rsaSign("ceshi", prikey, pubkey, "RSA");

        System.out.println(ceshi);
    }


    private static String rsaSign(String content, String privKey, String pubKey, String signAlgorithm) throws Exception {

        try {
            RSAHelper rsaHelper = new RSAHelper();
            rsaHelper.initKey(privKey, pubKey, 2048);
            String reqSign = new String(Base64.encodeBase64(rsaHelper.encryptRSA(content.getBytes(), false, "UTF-8")));
            return reqSign;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("aliPay签名失败");
        }
    }
}

