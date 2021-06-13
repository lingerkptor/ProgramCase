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
    - View 錯誤時的頁面 (resource/templates/product/error.html)
    - View 展示商品清單頁面 (resource/templates/product/list.html)
    - View 展示商品頁面(resource/templates/product/show.html 同Case 2)
      
- Case 4 參數驗證(使用Hibernate-validator)
  - add dependencies (Springboot version > 2.3,需額外匯入)
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    ```
  - Model (org.example.springboot.bean.UserForm.java)
    
  - Controller (org.example.springboot.Controller.UserRegisterController.java)
  - View (resource/templates/UserRegister.html)
  - View (resource/templates/User.html)

- Case 5 自訂驗證(簡單版)
  - 沿用Case4 程式碼
  - 修改Model(org.example.springboot.bean.UserForm.java)
    ```java
      @NotBlank(message = "請輸入密碼")
      @PasswordConstraint(message = "密碼規則必須包含大小寫及數字,最少8~20字元")
      private String password;
    ```
    
  - 建立標註介面[@interface]{org.example.springboot.validator.PasswordConstraint}
  - 建立驗證器{org.example.springboot.validator.PasswordConstraintValidator}
  
