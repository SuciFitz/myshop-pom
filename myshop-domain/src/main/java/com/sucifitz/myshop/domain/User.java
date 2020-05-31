 /**
  * FileName: User
  * author   Sucifitz
  * date     2020/1/5 17:29
  * Description: 
  * History:
  * <author>          <time>          <version>          <desc>
  * 作者姓名           修改时间           版本号              描述
  */
    package com.sucifitz.myshop.domain;

 import java.io.Serializable;

 /**
 *
 * @author Sucifitz
 * @date 2020/1/5
 * @since 1.0.0
 */
public class User implements Serializable {
    private String email;
    private String username;
    private String password;

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }
 }