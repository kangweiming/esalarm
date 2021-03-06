USE db_kwm;

DROP TABLE IF EXISTS `sys_profile_relation`; 
DROP TABLE IF EXISTS `sys_user`; 
DROP TABLE IF EXISTS `sys_user_profile`; 


/* 创建系统用户表及权限表等*/

/* 系统用户信息表 */
create table sys_user (  
   ID BIGINT NOT NULL AUTO_INCREMENT,  
   USER_NAME VARCHAR(30) NOT NULL,  
   PASSWORD VARCHAR(100) NOT NULL,  
   FIRST_NAME VARCHAR(30) ,  
   LAST_NAME  VARCHAR(30) ,  
   NICK_NAME VARCHAR(30) ,
   EMAIL VARCHAR(30),  
   STATE VARCHAR(30) NOT NULL, 
   GMT_CREATE DATETIME NOT NULL, 
   GMT_MODIFIED DATETIME NOT NULL, 
   GMT_LAST_LOGIN DATETIME, 
   PRIMARY KEY (ID),  
   UNIQUE (USER_NAME)  
);  
    
/* 权限表 */
create table sys_user_profile(  
   ID BIGINT NOT NULL AUTO_INCREMENT,  
   TYPE VARCHAR(30) NOT NULL,  
   GMT_CREATE DATETIME NOT NULL, 
   GMT_MODIFIED DATETIME NOT NULL, 
   PRIMARY KEY (id),  
   UNIQUE (type)  
);  
     
/* 用户与权限对应关系表 多对多 */
CREATE TABLE sys_profile_relation (  
		USER_ID BIGINT NOT NULL,  
    USER_PROFILE_ID BIGINT NOT NULL,  
   	GMT_CREATE DATETIME NOT NULL, 
   	GMT_MODIFIED DATETIME NOT NULL,     
    PRIMARY KEY (USER_ID, USER_PROFILE_ID),  
    CONSTRAINT FK_SYS_USER FOREIGN KEY (USER_ID) REFERENCES sys_user (ID),  
    CONSTRAINT FK_SYS_USER_PROFILE FOREIGN KEY (USER_PROFILE_ID) REFERENCES sys_user_profile (ID)  
);  
   
/* Populate USER_PROFILE Table */  
INSERT INTO sys_user_profile(TYPE,GMT_CREATE,GMT_MODIFIED)  
VALUES ('USER',NOW(),NOW());
   
INSERT INTO sys_user_profile(TYPE,GMT_CREATE,GMT_MODIFIED)  
VALUES ('ADMIN',NOW(),NOW());

INSERT INTO sys_user_profile(TYPE,GMT_CREATE,GMT_MODIFIED)  
VALUES ('DBA',NOW(),NOW());
 
   
/* Populate APP_USER Table */  
INSERT INTO sys_user(USER_NAME, PASSWORD, FIRST_NAME, LAST_NAME,NICK_NAME,EMAIL, STATE,GMT_CREATE,GMT_MODIFIED)  
VALUES ('kwm','123456', 'Weiming','Kang','kk','kwm@xyz.com', 'Active',NOW(),NOW());  
   
INSERT INTO sys_user(USER_NAME, PASSWORD, FIRST_NAME, LAST_NAME,NICK_NAME,EMAIL, STATE,GMT_CREATE,GMT_MODIFIED)  
VALUES ('admin','123456', 'admin','admin','admin','admin@xyz.com', 'Active',NOW(),NOW());  
   
INSERT INTO sys_user(USER_NAME, PASSWORD, FIRST_NAME, LAST_NAME,NICK_NAME,EMAIL, STATE,GMT_CREATE,GMT_MODIFIED)  
VALUES ('dba','123456', 'dba','dba','dba','dba@xyz.com', 'Active',NOW(),NOW());  
   
   
/* Populate JOIN Table */  
INSERT INTO sys_profile_relation (USER_ID, USER_PROFILE_ID,GMT_CREATE,GMT_MODIFIED)  
  SELECT user.ID, profile.ID,NOW(),NOW() FROM sys_user user, sys_user_profile profile    
  where user.USER_NAME='kwm' and profile.TYPE='USER';  
   
INSERT INTO sys_profile_relation (USER_ID, USER_PROFILE_ID,GMT_CREATE,GMT_MODIFIED)  
  SELECT user.ID, profile.ID,NOW(),NOW() FROM sys_user user, sys_user_profile profile    
  where user.USER_NAME='admin' and profile.TYPE='ADMIN';  

INSERT INTO sys_profile_relation (USER_ID, USER_PROFILE_ID,GMT_CREATE,GMT_MODIFIED)  
  SELECT user.ID, profile.ID,NOW(),NOW() FROM sys_user user, sys_user_profile profile    
  where user.USER_NAME='admin' and profile.TYPE='DBA';  

INSERT INTO sys_profile_relation (USER_ID, USER_PROFILE_ID,GMT_CREATE,GMT_MODIFIED)  
  SELECT user.ID, profile.ID,NOW(),NOW() FROM sys_user user, sys_user_profile profile    
  where user.USER_NAME='dba' and profile.TYPE='DBA';  
