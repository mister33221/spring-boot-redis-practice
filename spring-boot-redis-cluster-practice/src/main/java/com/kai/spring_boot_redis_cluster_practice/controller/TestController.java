package com.kai.spring_boot_redis_cluster_practice.controller;

import com.kai.spring_boot_redis_cluster_practice.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sharding")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Operation(
            summary = "Test sharding",
            description = """
            數據分片 (Data Sharding)
            
            Redis Cluster 使用 16384 個哈希槽來分配數據。我們可以測試數據是否確實分佈在不同的節點上。
            
            測試案例：
            
            寫入大量數據
            檢查每個節點的數據分佈
            """)
    @RequestMapping("/test-sharding")
    public Map<String, Long> testSharding(){
        return testService.testSharding();
    }

    @Operation(
            summary = "Test availability",
            description = """
            高可用性 (High Availability)
                        
            Redis Cluster 通過主從複制確保高可用性。我們可以測試當一個主節點故障時，系統是否仍然可用。
            
            測試案例：
                        
            寫入數據
            手動關閉一個主節點
            嘗試讀取數據
            """)
    @RequestMapping("/test-availability")
    public String test() {
        return testService.testAvailability();
    }

    @Operation(
            summary = "Automatic Failover",
            description = """
            自動故障轉移 (Automatic Failover)
            
            當一個主節點失效時，Redis Cluster 會自動將其中一個從節點提升為新的主節點。
            
            測試案例：
            
            寫入數據
            關閉一個主節點
            等待一段時間
            嘗試寫入新數據
            """)
    @RequestMapping("/test-automatic-failover")
    public String testAutomaticFailover() throws InterruptedException {
        return testService.testAutomaticFailover();
    }

    @Operation(
            summary = "Consistent Hashing",
            description = """
            一致性哈希 (Consistent Hashing)
            
            Redis Cluster 使用一致性哈希來確定每個鍵應該存儲在哪個節點上。
            測試案例：
            
            寫入特定的鍵
            檢查這些鍵在哪些節點上
            """)
    @RequestMapping("/test-consistent-hashing")
    public Map<String, String> testConsistentHashing() {
        return testService.testConsistentHashing();
    }

    @Operation(
            summary = "Read/Write Splitting",
            description = """
            讀寫分離 (Read/Write Splitting)
            
            Redis Cluster 支持從從節點讀取數據，這可以提高讀取性能。
            測試案例：
            
            寫入數據到主節點
            從從節點讀取數據
            
            注意：這需要在 Spring Boot 配置中啟用讀寫分離。
            """)
    @RequestMapping("/test-read-write-splitting")
    public String testReadWriteSplitting() {
        return testService.testReadWriteSplitting();
    }


}