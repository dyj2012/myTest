package com.duyj.selenium;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/10/22
 */
import java.io.IOException;

import com.duyj.selenium.jdpage.GoodsDetailsPage;
import com.duyj.selenium.jdpage.HomePage;
import com.duyj.selenium.jdpage.SearchResultListPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;



public class TestCheckGoodsPrice {

    WebDriver driver;

    @Before
    public void setUp() throws IOException{
        BrowserEngine browserEngine = new BrowserEngine();
        browserEngine.initConfigData("http://re.jd.com/");
        driver = browserEngine.getBrowser();
    }


    @Test
    public void checkPrice() throws InterruptedException{

        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        homepage.searchWithKeyword("iPhone");

        //进入搜索结果列表页
        SearchResultListPage srlp = PageFactory.initElements(driver, SearchResultListPage.class);
        Thread.sleep(1000);
        String price1 = srlp.getGoodsPriceOnListPage(); // 获取列表页商品的价格
        System.out.println(price1);
        srlp.clickItemImg();
        srlp.switchWindow(); // 调用窗口切换方法

        //进入商品详情页
        GoodsDetailsPage gdp = PageFactory.initElements(driver, GoodsDetailsPage.class);
        Thread.sleep(1000);
        String price2 = gdp.getPriceOnDetailsPage(); //获取商品详情页价格
        System.out.println(price2);

        //判断 同一个商品在列表页和详情页价格是否显示一致
//		if(price1 == price2){
//			System.out.println("Pass");
//		}else
//			System.out.println("Failed");
//

        // 第二种断言
        Assert.assertEquals(price2, price1);
        gdp.addGoodToCart(); // 添加到购物车
    }

    //如果需要看清楚结果，把@AfterClass注销就好
    @After
    public void tearDown(){
        driver.quit();
    }

}
