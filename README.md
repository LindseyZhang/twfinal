
### 默认添加商品（可通过修改h2数据库表文件修改。（src\main\resources\data-h2.sql））
```
ITEM (BARCODE, NAME, UNIT, PRICE) VALUES ('ITEM000001', '可口可乐', '瓶', 3.00);
ITEM (BARCODE, NAME, UNIT, PRICE) VALUES ('ITEM000003', '羽毛球', '个', 1.00);
ITEM (BARCODE, NAME, UNIT, PRICE) VALUES ('ITEM000005', '苹果', '斤', 5.50);
```
### 默认优惠方式（可通过修改h2数据库表文件修改。（src\main\resources\data-h2.sql））
```
PROMOTION (PROMOTIONNAME, BARCODES) VALUES ('BUY_TWO_GET_ONE_FREE', 'ITEM000001,ITEM000003');
PROMOTION (PROMOTIONNAME, BARCODES) VALUES ('SELL_BY_95', 'ITEM000005');
```

### 启动application
gradlew clean bootRun

### 手动测试application
可使用postman发送请求:

* Url
```
http://localhost:8080/pos-api/receipts
```

* Method
```
POST
```

* Header: 
```
Content-Type :text/html
```

* Body:
```
['ITEM000001', 'ITEM000001',  'ITEM000001',  'ITEM000001',  'ITEM000001',  'ITEM000003-2',  'ITEM000005',  'ITEM000005',  'ITEM000005']
````

###Response Body
```
***<没钱赚商店>购物清单***                                                                                                                                                                                                  
名称：羽毛球，数量：3个，单价：1.00(元)，小计：2.00(元)                                                                                                                                                                 
名称：可口可乐，数量：7瓶，单价：3.00(元)，小计：15.00(元)                                                                                                                                                             
名称：苹果，数量：3斤，单价：5.50(元)，小计：15.67(元)，节省：0.83(元)                                                                                                                                                   
---------------------------------------
买二赠一商品：                                                                                                                                                                                                              
名称：羽毛球，数量：1个                                                                                                                                                                                                 
名称：可口可乐，数量：2瓶                                                                                                                                                                                              
---------------------------------------
总计：32.67(元)                                                                                                                                                                                                             
节省：7.83(元)                                                                                                                                                                                                              
***************************************
```

###项目结构简单描述
项目共分为四个模块，并分别对应四个接口和四个单元测试，分别是：

1、商品管理模块，对应接口（\src\main\java\com\tw\service\item\ItemService.java），主要功能：获取数据库中存储的商品列表和优惠列表。

2、输入模块，对应接口（\src\main\java\com\tw\service\input\InputService.java），主要功能：将用户输入条码转化为List，并去重复，检查是否是商品库中的条码、并检查条码的格式是否正确

3、处理模块，对应接口（\src\main\java\com\tw\service\compute\ComputeService.java），主要功能：将输入模块传入的List按照不同的优惠和策略，输出一个computedItem类，其中包涵要输出的所有信息

4、输出模块，对应接口（\src\main\java\com\tw\service\ouput\OutputEachItem.java），主要功能：将处理模块传入的ComputedItem处理得到输出字符串
