package com.itcast.store.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092800617796";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCD4mOAhYb8A84kSQ1rVLaZoS/dgYrzeQufSbT79NgGG28UiJo7ERsiDGIWiRNdUHYoOcYrEnShNs/v7h1jZnR2Ew3jmCRqESiayFF/7DGP16og9G8MLjdsRoVZo78UfLuQh8Q3iYDTZdtWdCMGYKRc7wC0j8pz8rl7btYfZEFS+2mYxR0AH6k4PBgACBNaRt0fOn3XsC3qa+ekf4eTLZLo16DAskQKz891yWDzvT58z+bJn3S+HwygdT4lwOTeOi42LZ7/anM3g6DvxaWA9ZMFlurPhaGOsxWCUFQMGE0wY0TvvuoRjrkuSmWVRWuw0F/xFLwMNicXNFWxZOd1YIklAgMBAAECggEAc9RmXZ9ooXhaGYZLkeOyCBYIKvw2ZIYATiL3IH63KA36LkeXZZ8tfGbx0ycK6d7cF5I+WN/5rQmt8OTpzL5UNMuhXC3odXuidObQpZB1KwdX9V+ZFFKW/LKnNcZZ8Q1n0pVbIQfULFZ+IF0Z0eUZFkIXFX5gahtZCm6bArETqmO5lqG4QMMAdRoGYw6jxBh0aCwe0bCgzk2pTB/DGhrl7tbv3s0+Ut8HNDdQB+6qSoI/gbxc+70/ylq1l1JoalayE5xwLFzrkm4ZP3087It1iP9vA36Yw/hRQUwVtk5vCzMSmtqAvEtGF2PRTldx8YaPF4E4KXHCnEQjziThz7yigQKBgQDhjcdRXPLZpnWEXHhC1orYqfAPxpfi1k7MsONFxSr4tv4eC6OieOQj2AvNvL/PUcwFK/vYTLQaHTk0vLm/jk/vRqFynkZfGE2TGFNtD3JvRF8G/dq47o22pO28tKrty8K4SNBaxYCBBj/igKVRqeoLYnSO2Y7laPpUL0KAUYMdRQKBgQCVr8Yjeb6SRaKJGicZy2pkg84ueca8OLzY8O2e4WW1UB7j4Bd7N75ks8/M3jr5jyDjeHLiY+DR6dWPQs4eogtKLGkqzAepwXmLJVyYKC2pIvWKWMUbwLYE6eB/oAeFUuzV8Kw63DBwA/QcW5xA0taVQHUiUgyjvKa7Wtvi39LKYQKBgQCfuO+hbUnH2Pm1HmpHIng2T7jg4wghqRMqHkUC3km80JEJI8Lfxe4Ad/h726KUpF7udqQkGsUbDQC6mL6MqLFwRrsDWJOKucINXReOznaB1o/YeqGNfdi6svZrQCEevcEJEP8CmIOUWqGYuvLW7pT/NNxHYEaxq/JA9SqJha9sZQKBgQCE/GpLgFE4gRdbOVn9x8RZUuSuU9Clzr6w+rQSEOycvncDO0eBWFAWBc6bk+DPcULl9VAUJGpL9LSgl9iqTIs9NeGlfSldvx9t6R6vrBqNBxsg86cC9h6AlQy4NoQYBkmV9UH+9TsvecO6IgXbbbXpWh/3AvBp1CRJlfp/QR10YQKBgFtBJdVtjWP2VvDxHkVwzj/hGjE2Oi4fzLujbTq2BVy78TxNjNV/ySxNKFaYx0kCsQgBklCf0qZTlN6kynN+vXD6fT0hxzo7TwWAevfOI7p/7yOPeW5/1MPumpGrojy6rW3EUEcpnNOc7J2tovR0HDfiwqp3cuf1sh8uAh6xnldu";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4Jia+3faeXRSOC4T5+fO4D/f0cOmxSSqAoddHw1UpwlqPByhVbUdpRem4mxU/Ur0JTYSTmSYdiZeH75KzRuYIGhKvmTypTuY1SEVO2yHosQoPo+HfQRGSbDv6tIKbAyLLKwgLe6PQnKhQZA9aipgEk38WnBidlyQNu8YO8DQoDtklQ34CU3rJjL4M2WTfwSF7LENIlemZG7iis2KjEBsl3zzMGmRUo62+U19a4JKDZA4kWZweCNDfnOgwd3sVQC34qoztvieU4EQ82sGV7Bv76N3sZI+50gw5B72eQYZrKLnscNLa3h17KRLACpfbe8SqsscI6BpgdtSToxknCLqawIDAQAB";


	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/store/jsp/pay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关//正式环境下的网关：https://openapi.alipay.com/gateway.do
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";  
	
	// 支付宝网关
	public static String log_path = "C://";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

