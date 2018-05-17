# tenement
tenement是一个用于爬取豆瓣租房信息，根据条件过滤相关信息的小型爬虫项目

使用tenement你可以：  
 
- 实时获取豆瓣租房相关信息
- 根据title信息找到想要的租房信息
- 通过html文件操作类将结果生成html文件

## TODO
由于微信公众号接口开放权限问题，自动生成微信信息暂时未完成

## Getting Started
能自行运行和编辑代码的同学导入代码，配置配置文件然后运行即可：   
导入src文件，配置 resources/application.properties：


	douban.pages     //每次爬取页数，豆瓣网页每页显示25条信息，可以根据需要配置一次请求的页数   

	douban.urls      //需要爬取的豆瓣的url的group后缀。比如北京租房豆瓣小组url:https://www.douban.com/group/beijingzufang 
					 //那么group后缀就为beijingzufang,多个网页用分号隔开    

	douban.keyword   //需要获取的关键字，比如西小口，那么每个title中包含西小口的字段都会被保存。多个用分号隔开。
 				     //如果需要多个字段，比如A同学要看西二旗那一块的地段，可以设置为上地;西二旗
                     //B同学要看西小口附近的地段，可以设置为西小口;清河。需要同时为两个同学保存结果，则设置为：
 				     //上地;西二旗&西小口;清河
    
  	crawler.interval //每隔多少分钟检测一次，建议不小于30.否则可能会被封禁ip
    
	writer.dirpath   //生成的文件保存的位置。如果希望通过网页发布，建议部署在tomcat的webapp目录下，能够实时更新信息


配置完成后，运行Main.mian()即可实时获取信息。


没有条件运行代码的同学，百度配置jdk 1.8，然后下载本项目文件，打开out文件夹，使用好压打开tenement-1.0-RELEASE.jar，参照上面的方法使用记事本编辑里面的application.properties，然后运行java -jar tenement-1.0-RELEASE.jar即可


