# Note web service assessment from Thermondo

## Package Structure
1. **configuration:** the package contains the configuration for swagger, which is used to document web service
2. **controllers:** this package contains the webservices
3. **models, models.dto, models.enum:** the contains the entity and dto and enums
4. **repository:** the page is used to house interfaces for JPA implementation, for pagination, filtering and excution
of sql operations on entities
5. **service:** this contains the business logics for user and note management
6. **utils:** this contains classes with methods that performs tasks repetetive tasks

---

## Prerequisites
Before you continue, there are some things you need to have done:
1. You need 8.x and above
2. Maven is needed 

---

## Documentation
### Web Service Documentation
When you run the code, open your browser and enter the link http://localhost:8080/swagger-ui/index.html
you
![image](https://user-images.githubusercontent.com/12773434/160385674-525e842b-2c6c-4a69-81ff-e95532978fa9.png)

### Code Documentation
This documentation contains call graphs, class hirarchy, class index and much more.
The documenation is located at Notebank\src\main\resources\documentation\index.html and should be opened in the browser
![image](https://user-images.githubusercontent.com/12773434/160386317-bd6f3cd3-9175-43f8-b6e9-22e48958246a.png)

