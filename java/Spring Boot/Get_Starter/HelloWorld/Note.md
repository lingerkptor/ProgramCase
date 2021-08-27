1. 注入其他語言或參考
<img src="https://resources.jetbrains.com/help/img/idea/2020.3/inject-html.animated.gif" keyword="inject language or reference">
2. 快捷鍵
<table>
<tr><th>快捷鍵</th><th>說明</th></tr>
<tr><td>Alt + enter</td><td>多功能選項(注入其他語言或參考)</td></tr>
<tr><td>ctrl + alt + L</td><td>格式化程式碼</td></tr>
<tr><td>ctrl + alt + O</td><td>整理 import</td></tr>
<tr><td>ctrl + O</td><td>implements(Override) methods </td></tr>
<tr><td>ctrl + Numpad-</td><td>摺疊單組註解</td></tr>
<tr><td>ctrl + Numpad+</td><td>摺疊單組展開</td></tr>
<tr><td>ctrl + shift + Numpad-</td><td>全部摺疊註解</td></tr>
<tr><td>ctrl + shift + Numpad+</td><td>全部摺疊展開</td></tr>
</table>

3. 註釋標籤
<table>
<tr><th>標籤名稱</th><th>標記位址</th><th>說明</th></tr>
<tr><td>@SpringBootApplication</td><td>class、類別名稱上</td><td>指定入口的類別</td></tr>
<tr><td>@Value</td><td>Field</td><td>取得設定檔的值</td></tr>
<tr><td>@Controller</td><td>class</td><td>用於標記為控制層(MVC內的C)，交由容器管理</td></tr>
<tr><td>@Component</td><td>class</td><td>用來將普通的POJO實體化到Spring容器中</td></tr>
<tr><td>@RequestBody</td><td>emthod</td><td>用來處理請求內容標籤</td></tr>
<tr><td>@ResponseBody</td><td>method</td><td>用來將指定的物件轉換為指定的格式(Content-Type)</td></tr>
<tr><td>@RestController</td><td>class</td><td>作用相當於@Controller + @ResponseBody</td></tr>
<tr><td>@Configuration</td><td>class</td><td>宣告成是一種設定類別，常與@Bean配合使用</td></tr>
<tr><td>@Bean</td><td>method</td><td>宣告這個方法為一個BeanFactory(參考Factory method)</td></tr>
<tr><td>@Autowired</td><td>class、method、Field</td><td>藉由類別型態自動注入(dependency inject)</td></tr>
<tr><td>@Qualifier</td><td>class、method</td><td>用於指定參考的Bean(name)</td></tr>
<tr><td>@Resource</td><td>class、method、Field</td><td>相當同時使用@Autowired @Qualifier(name)</td></tr>
<tr><td>@RequestMapping</td><td>class、method</td><td>如果用在類別上代表，底下的所有回應方法都是以該位址為父路徑<br/>
Params=必需包含指定參數<br/>Headers=必須包含指定標頭值<br/>Value=指定請求實際的位址(可以是URI Template模式)<br/>Method=指定請求的方法(GET、POST...etc)<br/>
Consumes=指定請求的Content-type<br/>Produces=指定回應的Content-Type</td></tr>
<tr><td>@SpringBootTest</td><td>class</td><td>標記為Spring測試類別</td></tr>

</table>
4. 其他標籤
<table>
<tr><th>來源函式庫</th><th>標籤名稱</th><th>標記位址</th><th>說明</th></tr>
<tr><td>lombok</td><td>@Data</td><td>class</td><td>自動產生Setter、Getter、toString、equals、hashCode等方法</td></tr>
<tr><td>Junit4、5</td><td>@Test</td><td>method</td><td>標記該方法為一個單元測試方法</td></tr>
</table>
4. 設定檔
<table>
<tr><td>application.yml</td><td>application.properties</td></tr>
<tr><td>
spring:<br/>
　profiles:<br/>
　　active: dev #引用application-dev.yml設定<br/>
server:<br/>
　port: 8080<br/>
　Servlet:<br/>
　　session:<br/>
　　　　timeout: 30<br/>
　tomcat:<br/>
　　uri-encoding: UTF-8<br/>
</td><td>
spring.profiles.active=dev#引用application-dev.properties設定<br/>
server.port=8080<br/>
server.Servlet.session.timeout=30<br/>
server.tomcat.uri-encoding=UTF-8<br/>
</td></tr>
<tr><td>application-dev.yml</td><td>application-dev.properties</td></tr>
<tr><td></td><td></td></tr>
</table>