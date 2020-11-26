package tradeit;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tradeit.controller.ItemController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class TradeitApplicationTests {

    @Autowired
    ItemController itemController;

    @Test
    void contextLoads() {
        Assert.assertNotNull(itemController);
    }

}
