- Case 1 建立簡單的CRUD JDBCTemplate 範例
  - application.yml (建立主要的資料庫設定,請事先架設好資料庫)
  - Test(/src/test/java/idv.lingerkptor.example.springboot.dao.ProductDaoTest.java)
  - DAO(資料存取物件/src/main/java/idv.lingerkptor.example.springboot.dao.ProductDao.java)
  - Bean (/src/main/java/idv.lingerkptor.example.springboot.bean.Product.java)
  - Bean Mapper(/src/main/java/idv.lingerkptor.example.springboot.dao.BeanMapper.ProductBeanMapper.java)
  - main Class (/src/main/java/idv.lingerkptor.example.springboot.APP.java)
  - mvn test
- Case 2 Transaction 
  - d