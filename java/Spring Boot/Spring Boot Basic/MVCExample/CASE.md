- Case 1 建立簡單MVC範例(使用Thymeleaf)
    - add dependencies
      ```xml
      <dependcy>
          <groupId>org.springframework.boot</groupId>
          <artiactId>spring-boot-starter-thymeleaf</artiactId>
      </dependcy>
      ```

    - thymeleaf config (application.properties)
      ```properties
      spring.thymeleaf.mod=HTML5
      spring.thymeleaf.encoding=UTF-8
      spring.thymeleaf.content-type=text/html
      #開發時建議關閉快取
      spring.thymeleaf.cache=false 
      ```
    - 建立View(resource/templates/HelloWorld.html)
    - 建立Controller(HelloWorldController.class)
  
- Case 2 參數讀取範例
  - Add Controller (org.example.springboot.Controller.ParaExampleController.java)
  - Add Service (org.example.springboot.Service.ParaExampleProductService.java)
  - Add View (resources/templates/product/show)
    
- Case 3 完整的例子
    -  Model (org.example.springboot.bean.Product.java)
    -  Service interface (org.example.springboot.Service.ProductService.java)
       想測試能不能自動地將Service的實體注入的介面，之後再研究能不能分別多實體，依規則注入．
    -  Service (org.example.springboot.Service.implements.ProductServiceIml.java)
       Service的實體
    -  Controller (org.example.springboot.Controller.ProductServiceController.java)
    
    
