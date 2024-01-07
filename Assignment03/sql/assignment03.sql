DROP DATABASE IF EXISTS Assignment_3;
CREATE DATABASE Assignment_3;
USE Assignment_3;

DROP TABLE IF EXISTS Roles;
CREATE TABLE Roles (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  ROLE_NAME VARCHAR(255)
);

DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  `NAME` VARCHAR(255),
  EMAIL VARCHAR(255),
  `PASSWORD` VARCHAR(255),
  ADDRESS VARCHAR(255),
  PHONE VARCHAR(255),
  AVATAR VARCHAR(255),
  GENDER VARCHAR(255),
  GENERALINTRODUCTION VARCHAR(255),  -- GIỚI THIỆU CHUNG
  TRAININGPROCESS VARCHAR(255) ,     -- QUÁ TRÌNH ĐÀO TẠO
  ACHIEVEMENTSACHIEVED VARCHAR(255),-- CÁC THÀNH TỰU ĐẠT ĐƯỢC
  SPECIALTIES VARCHAR(255),         -- CÁC CHUYÊN KHOA PHỤ TRÁCH
  `DESCRIPTION` VARCHAR(255),
  ROLE_ID INT,
  IS_ACTIVE INT,
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_AT DATETIME DEFAULT NULL,
  DELETE_AT DATETIME DEFAULT NULL
);

ALTER TABLE `Users`
ADD FOREIGN KEY (ROLE_ID) REFERENCES Roles(ID);

DROP TABLE IF EXISTS Schedules;  -- lịch trình
CREATE TABLE Schedules ( 
  ID INT PRIMARY KEY AUTO_INCREMENT,
  DOCTOR_ID INT NOT NULL,
  `DATE` VARCHAR(255) NOT NULL,
  `TIME` VARCHAR(255) NOT NULL,
  MAX_BOOKING INT,
  SUM_BOOKING INT,
  CREATE_AT DATETIME,
  UPDATE_AT DATETIME,
  DELETE_AT DATETIME DEFAULT NULL,
  FOREIGN KEY (DOCTOR_ID) REFERENCES  `Users`(ID)
);


DROP TABLE IF EXISTS Specializations; -- Chuyên ngành của từng bac si
CREATE TABLE Specializations (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  `NAME` VARCHAR(255),
  `DESCRIPTION` VARCHAR(255),
  IMAGE VARCHAR(255),
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_AT DATETIME DEFAULT NULL,
  DELETE_AT DATETIME DEFAULT NULL
);

DROP TABLE IF EXISTS Patients; -- Người bệnh
CREATE TABLE Patients (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  DOCTOR_ID INT,
  `STATUS` INT,
 `NAME` VARCHAR(255),
  GENDER VARCHAR(255),
  PHONE VARCHAR(255),
  ADDRESS VARCHAR(255),
  DATE_OF_BIRTH VARCHAR(255),
  PATHOLOGICAL VARCHAR(255), -- LÝ DO PATHOLOGICAL
  `DESCRIBE` VARCHAR(255), -- mô tả bệnh lý
FOREIGN KEY (DOCTOR_ID) REFERENCES  `Users`(ID)
);


DROP TABLE IF EXISTS Places;  -- Địa điểm
CREATE TABLE Places (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  NAME_PALACES VARCHAR(255),
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_AT DATETIME DEFAULT NULL,
  DELETE_AT DATETIME DEFAULT NULL
);


DROP TABLE IF EXISTS Appointments; -- Lịch sử khám bệnh
CREATE TABLE Appointments (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  PATIENT_ID INT NOT NULL,
  PLACE_ID INT,
  DOCTOR_ID INT,
  PATHOLOGICAL VARCHAR(255) NOT NULL, -- Lý do khám
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_At DATETIME DEFAULT NULL,
  FOREIGN KEY (PATIENT_ID) REFERENCES  PATIENTS(ID),
  FOREIGN KEY (PLACE_ID) REFERENCES  PLACES(ID),
  FOREIGN KEY (DOCTOR_ID) REFERENCES  `Users`(ID)
);

DROP TABLE IF EXISTS Statuses; -- trạng thái
CREATE TABLE Statuses (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  `NAME` VARCHAR(255),
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_AT DATETIME DEFAULT NULL,
  DELETE_AT DATETIME DEFAULT NULL
);

DROP TABLE IF EXISTS Clinics; -- Phòng khám
CREATE TABLE Clinics (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  `NAME` VARCHAR(255),
  ADDRESS VARCHAR(255),
  PHONE VARCHAR(255),
  INTRODUCTIONHTML VARCHAR(255),
  INTRODUCTIONMARKDOWN VARCHAR(255),
  `DESCRIPTION` VARCHAR(255),
  IMAGE VARCHAR(255),
  COST_OF_EXAMINATION INT,
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_AT DATETIME DEFAULT NULL,
  DELETE_AT DATETIME DEFAULT NULL
);

DROP TABLE IF EXISTS Posts; -- bài viết
CREATE TABLE Posts (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  TITLE VARCHAR(255),
  CONTENMARKDOWN VARCHAR(255),
  CONTENHTML VARCHAR(255),
  DATE_TIME_BOOKING VARCHAR(255),
  FOR_DOCTOR_ID INT,
  FOR_SPECIALIZATION_ID INT,
  FOR_CLINIC_ID INT,
  FOR_PATIENT_ID INT,
  CONFIRM_BY_DOCTOR INT,
  IMAGE VARCHAR(255),
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_AT DATETIME DEFAULT NULL,
  DELETE_AT DATETIME DEFAULT NULL,
  FOREIGN KEY (FOR_DOCTOR_ID) REFERENCES `Users`(ID),
  FOREIGN KEY (FOR_SPECIALIZATION_ID) REFERENCES  Specializations(ID),
  FOREIGN KEY (FOR_CLINIC_ID) REFERENCES  Clinics(ID)
);

DROP TABLE IF EXISTS Comments; -- Bình luận
CREATE TABLE Comments (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  DOCTOR_ID INT,
  TIMEBOOKING VARCHAR(255),
  DATEBOOKING VARCHAR(255),
  `NAME` VARCHAR(255),
  PHONE VARCHAR(255),
  CONTENT VARCHAR(255),
  `STATUS` INT,
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_AT DATETIME DEFAULT NULL,
  DELETE_AT DATETIME DEFAULT NULL,
  FOREIGN KEY (DOCTOR_ID) REFERENCES `Users`(ID)
);

DROP TABLE IF EXISTS Doctor_User;
CREATE TABLE Doctor_User (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  DOCTOR_ID INT,
  CLINIC_ID INT,
  SPECIALIZATION_ID INT,
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_AT DATETIME DEFAULT NULL,
  DELETE_AT DATETIME DEFAULT NULL,
  FOREIGN KEY (DOCTOR_ID) REFERENCES `Users`(ID),
  FOREIGN KEY (CLINIC_ID) REFERENCES Clinics(ID),
  FOREIGN KEY (SPECIALIZATION_ID) REFERENCES Specializations(ID)
);


DROP TABLE IF EXISTS SupporterLogs; -- Hỗ trợ
CREATE TABLE SupporterLogs (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  PATIENT_ID INT,
  CONTENT VARCHAR(255),
  CREATE_AT DATETIME DEFAULT NULL,
  UPDATE_AT DATETIME DEFAULT NULL,
  DELETE_AT DATETIME DEFAULT NULL,
  FOREIGN KEY (PATIENT_ID) REFERENCES Patients(ID)
);


 INSERT INTO Roles (ROLE_NAME  )
	    VALUES       ( 'Admin'), 
                     ( 'Doctor'),
	                 ( 'User' ); 



INSERT INTO
      Statuses (        `NAME`     ,  CREATE_AT   ,   UPDATE_AT)
VALUES         (  'Đang Chờ'       ,'2023-10-10' , '2023-12-25'),
	           (  'Đang Khám'      ,'2023-08-30' , '2023-10-05'),
			   (  'Kết Thúc'       ,'2023-09-20' , '2023-11-02');
			 


INSERT INTO
`Users`(        `NAME`     ,    EMAIL                    ,                         `PASSWORD`                              , ADDRESS     ,    PHONE     ,       AVATAR        ,  GENDER  ,GENERALINTRODUCTION ,       TRAININGPROCESS     , ACHIEVEMENTSACHIEVED ,      SPECIALTIES    , `DESCRIPTION` , ROLE_ID , IS_ACTIVE , CREATE_AT   ,   UPDATE_AT   , DELETE_AT)
VALUES ('Hồ Diên Vinh'     , 'vinhhdfx21678@funix.edu.vn', '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Hà Nội'    ,'0914453588'  , 'anh_dai_dien.jpg ' ,  'MALE'  ,        NULL        ,              NULL         ,          NULL        ,         NULL        ,  'No Coment'  ,     1   ,     1    ,'2023-10-10' , '2023-12-25'  ,'2023-12-30'),
	   ('Bùi Xuân Thường'  , 'xuanthuong@gmail.com'      , '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Hà Giang'  ,'0948157159'  , 'anh_dai_dien.jpg ' ,  'MALE'  ,'Bác Sĩ Chuyên Khoa','Chứng chỉ cấp cứu đột quỵ',        'Tiến Sĩ'     ,        'Da Liễu'    ,  'No Coment'  ,     2   ,     1     ,'2023-08-30' , '2023-10-05'  ,'2023-12-25'),
	   ('Phạm Thanh Bình'  , 'thanhbinh@gmail.com'       , '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Cao Bằng'  , '0912300247' , 'anh_dai_dien.jpg ' ,  'MALE'  ,'Bác Sĩ Chuyên Khoa',  'Chuyên khoa Da liễu'    ,'P.Giáo Sư, Tiến Sĩ'  ,      'Thần Kinh'    ,  'No Coment'  ,     2   ,     1     ,'2023-09-20' , '2023-11-02'  ,'2023-11-30'),
	   ('Nguyễn Trí Thủy'  , 'trithuy@gmail.com'         , '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Bắc Kạn'   ,'0915133607'  , 'anh_dai_dien.jpg ' ,  'MALE'  ,        NULL        ,            NULL           ,          NULL        ,         NULL        ,  'No Coment'  ,     3   ,     1     ,'2023-10-09' , '2023-12-08'  ,'2023-11-30'),
	   ('Nguyễn Quang Vũ'  , 'quangvu@gmail.com'         , '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Điện Biên' ,'0913602103'  , 'anh_dai_dien.jpg ' ,  'MALE'  ,        NULL        ,            NULL           ,          NULL        ,         NULL        ,  'No Coment'  ,     3   ,     1     ,'2023-12-10' , '2023-12-09'  ,'2023-12-30'),
	   ('Lý Quang Huy'     , 'quanghuy@gmail.com'        , '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Lào Cai'   ,'0905372666'  , 'anh_dai_dien.jpg ' ,  'MALE'  ,'Bác Sĩ Chuyên Khoa','Trường ĐH Y dược TP.HCM'  ,        'Thạc Sĩ'     , 'Vô Sinh-Hiếm Muộn' ,  'No Coment'  ,     2   ,     1     ,'2023-11-12' , '2023-12-11'  ,'2023-12-30'),
	   ('Nguyễn Văn Thành' , 'vanthanh@gmail.com'        , '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Lai Châu'  ,'0911375199'  , 'anh_dai_dien.jpg ' ,  'MALE'  ,        NULL        ,            NULL           ,          NULL        ,          NULL       ,  'No Coment'  ,     3   ,     1     ,'2023-10-13' , '2023-12-10'  ,'2023-12-25'),
	   ('Phạm Ngọc Thắng'  , 'ngocthang@gmail.com'       , '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Sơn La'    ,'0949522905'  , 'anh_dai_dien.jpg ' ,  'MALE'  ,        NULL        ,            NULL           ,          NULL        ,          NULL       ,  'No Coment'  ,     3   ,     1     ,'2023-08-17' , '2023-10-18'  ,'2023-11-30'),
	   ('Lê Thị Thu Cúc '  , 'thucuc@gmail.com'          , '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Yên Bái'   ,'0949234388'  , 'anh_dai_dien.jpg ' , 'FEMALE' ,        NULL        ,            NULL           ,          NULL        ,          NULL       ,  'No Coment'  ,     3   ,     1     ,'2023-09-20' , '2023-10-19'  ,'2023-12-30'),
	   ('Trần Thị Như Ý '  , 'tranthinhuy@gmail.com'     , '$2a$12$05jIkU5Nd0zbMvRi/7vlfOpIJuAIlq3htfGXk9sR5Y47MxozEHmd6'  , 'Hòa Bình'  ,'0914500150'  , 'anh_dai_dien.jpg ' , 'FEMALE' ,'Bác Sĩ Chuyên Khoa','Đại học Y dược TP. HCM'   ,'Chuyên Viên Cao Cấp' ,   'Tai-Mũi-Họng'    ,  'No Coment'  ,     2   ,     1     ,'2023-08-25' , '2023-09-20'  ,'2023-10-15');
 

INSERT INTO -- Lịch Trình
Schedules(   DOCTOR_ID ,     `DATE`    ,   `TIME`  , MAX_BOOKING , SUM_BOOKING , CREATE_AT   ,   UPDATE_AT   , DELETE_AT)
VALUES   (      2      , '2023-10-10'  , '10-00-00',      10     ,      3      ,'2023-10-10' , '2023-12-25' ,'2023-12-30'),
	     (      3      , '2023-08-30'  , '08-30-00',      10     ,      2      ,'2023-08-30' , '2023-10-05' ,'2023-12-25'),
	     (      6      , '2023-09-20'  , '09-20-00',      10     ,      1      ,'2023-09-20' , '2023-11-02' ,'2023-11-30'),
	     (      3      , '2023-10-09'  , '10-30-00',      10     ,      3      ,'2023-10-09' , '2023-12-08'  ,'2023-11-30'),
	     (      10     , '2023-12-10'  , '10-20-00',      10     ,      2      ,'2023-12-10' , '2023-12-09'  ,'2023-12-30'),
	     (      2      , '2023-11-12'  , '11-00-00',      10     ,      1      ,'2023-11-12' , '2023-12-11'  ,'2023-12-30'),
	     (      10     , '2023-10-13'  , '13-30-00',      10     ,      3      ,'2023-10-13' , '2023-12-10'  ,'2023-12-25'),
	     (      6      , '2023-08-17'  , '14-00-00',      10     ,      4      ,'2023-08-17' , '2023-10-18'  ,'2023-11-30'),
	     (      3      , '2023-09-20'  , '15-30-00',      10     ,      2      ,'2023-09-20' , '2023-10-19'  ,'2023-12-30'),
	     (      2      , '2023-08-25'  , '16-00-00',      10     ,      1      ,'2023-08-25' , '2023-09-20'  ,'2023-10-15');
 

INSERT INTO -- Chuyên Ngành
Specializations(        `NAME`     ,        `DESCRIPTION`        ,        IMAGE      , CREATE_AT   ,   UPDATE_AT   , DELETE_AT)
VALUES         (  'Cơ Xương Khớp'  ,    'Chuyên Cơ Xương Khớp'   , 'anh_dai_dien.jpg','2023-10-10' , '2023-12-25'  ,'2023-12-30'),
	           (  'Thần Kinh'      ,    'Chuyên Thần Kinh'       , 'anh_dai_dien.jpg','2023-08-30' , '2023-10-05'  ,'2023-12-25'),
			   (  'Tiêu Hóa'       ,    'Chuyên Tiêu Hóa'        , 'anh_dai_dien.jpg','2023-09-20' , '2023-11-02'  ,'2023-11-30'),
			   (  'Tim Mạch'       ,    'Chuyên Tim Mạch'        , 'anh_dai_dien.jpg','2023-10-09' , '2023-12-08'  ,'2023-11-30'),
	           (  'Tai-Mũi-Họng'   ,    'Chuyên Tai-Mũi-Họng'    , 'anh_dai_dien.jpg','2023-12-10' , '2023-12-09'  ,'2023-12-30'),
	           (  'Y Học Cổ Truyền',    'Chuyên Y Học Cổ Truyền' , 'anh_dai_dien.jpg','2023-11-12' , '2023-12-11'  ,'2023-12-30'),
	           (  'Da liểu'        ,    'Chuyên Da Liễu'         , 'anh_dai_dien.jpg','2023-10-13' , '2023-12-10'  ,'2023-12-25'),
	           (  'Sản Phụ Khoa'   ,    'Chuyên Sản Phụ Khoa'    , 'anh_dai_dien.jpg','2023-08-17' , '2023-10-18'  ,'2023-11-30'),
	           (  'Châm Cứu'       ,    'Chuyên Châm Cứu'        , 'anh_dai_dien.jpg','2023-09-20' , '2023-10-19'  ,'2023-12-30'),
	           (  'Cột Sống'       ,    'Chuyên Cột Sống'        , 'anh_dai_dien.jpg','2023-08-25' , '2023-09-20'  ,'2023-10-15');          

INSERT INTO -- Người bệnh
 Patients(   DOCTOR_ID ,  `STATUS`  , GENDER  ,     PHONE     ,  ADDRESS   , DATE_OF_BIRTH , PATHOLOGICAL , `DESCRIBE`, `NAME`       )
VALUES   (      2      ,     1      , 'Male'  , '0971.422.888', 'Hà Giang' , '1963-12-30'  ,'No Reason'   ,     'OK'  ,'Trịnh Mạnh Linh'   ),
	     (      3      ,     1      , 'Male'  , '0912.131.417', 'Cao Bằng' , '1959-09-25'  ,'No Reason'   ,     'OK'  ,'Đỗ Đức Toàn'       ),
	     (      10     ,     1      , 'Male'  , '0988.087.969', 'Hà Nội'   , '1953-10-29'  ,'No Reason'   ,     'OK'  ,'Nguyễn Đăng Trương'),
	     (      6      ,     1      , 'Male'  , '0913.885.799', 'Điện Biên', '1948-12-14'  ,'No Reason'   ,     'OK'  ,'Hồ Sỹ Nguyệt'      ),
	     (      3      ,     1      , 'Male'  , '0913.529.255', 'Hòa Bình' , '1986-11-15'  ,'No Reason'   ,     'OK'  ,'Nguyễn Văn Dinh'  ),
	     (      2      ,     1      , 'Male'  , '0915.001.179', 'Lai Châu' , '1973-05-26'  ,'No Reason'   ,     'OK'  ,'Hồ Hữu Trình'      ),
	     (     10      ,     1      , 'Male'  , '0915.051.617', 'Hà Nội'   , '1965-06-08'  ,'No Reason'   ,     'OK'  ,'Hoàng Văn Bộ'      ),
	     (      6      ,     1      , 'Male'  , '0889.888.989', 'Cao Bằng' , '1969-08-09'  ,'No Reason'   ,     'OK'  ,'Hồ Văn Thanh'      ),
	     (      3      ,     1      , 'Male'  , '0917.303.444', 'Nghệ An'  , '1989-10-06'  ,'No Reason'   ,     'OK'  ,'Nguyễn Văn Thưởng' ),
	     (      2      ,     1      , 'FeMale', '0917.303.567', 'Nghệ An'  , '1996-10-06'  ,'No Reason'   ,     'OK'  ,'Bùi Thảo Ly' );


INSERT INTO  -- Địa Điểm
	  Places   (   NAME_PALACES  , CREATE_AT   ,   UPDATE_AT   , DELETE_AT  )
VALUES         (    'Hà Nội'     ,'2023-10-10' , '2023-12-25'  ,'2023-12-30'),
	           (    'Hà Nội'     ,'2023-08-30' , '2023-10-05'  ,'2023-12-25'),
			   (    'Đà Nẵng'    ,'2023-09-20' , '2023-11-02'  ,'2023-11-30'),
			   (    'TP.HCM'     ,'2023-10-09' , '2023-12-08'  ,'2023-11-30'),
	           (    'Đà Nẵng'    ,'2023-12-10' , '2023-12-09'  ,'2023-12-30'),
	           (    'TP.HCM'     ,'2023-11-12' , '2023-12-11'  ,'2023-12-30'),
	           (    'Ninh Bình'  ,'2023-10-13' , '2023-12-10'  ,'2023-12-25'),
	           (    'Nghệ An'    ,'2023-08-17' , '2023-10-18'  ,'2023-11-30'),
	           (    'Hà Nội'     ,'2023-09-20' , '2023-10-19'  ,'2023-12-30'),
	           (    'Đà Nẵng'    ,'2023-08-25' , '2023-09-20'  ,'2023-10-15');

INSERT INTO  
   Appointments( PATIENT_ID , PLACE_ID ,DOCTOR_ID ,  PATHOLOGICAL  , CREATE_AT   ,   UPDATE_AT )
VALUES         (     2      ,    2     ,    2     ,   'Cảm Cúm'    ,'2023-10-10' , '2023-12-25'  ),
	           (     3      ,    4     ,    3     ,   'Đau Lưng'   ,'2023-08-30' , '2023-10-05'  ),
			   (     8      ,    5     ,    10    ,   'Uốn Ván'    ,'2023-10-09' , '2023-12-08' ),
	           (     9      ,    6     ,    2     ,   'Thấp Khớp'  ,'2023-12-10' , '2023-12-09'  ),
	           (     10     ,    7     ,    6     ,   'Thân Kinh'  ,'2023-11-12' , '2023-12-11' ),
	           (     1      ,    9     ,    6     ,   'Đau Đầu'    ,'2023-10-13' , '2023-12-10'  ),
	           (     5      ,    10    ,    3     ,   'Cảm Cúm'    ,'2023-08-17' , '2023-10-18'  ),
	           (     3      ,    3     ,    2     ,'Sốt Xuất Huyết','2023-09-20' , '2023-10-19' ),
	           (     2      ,    5     ,    3     ,   'Đau Lưng'   ,'2023-08-25' , '2023-09-20'  );



INSERT INTO  
	   Clinics (                `NAME`             ,        ADDRESS        ,       PHONE     ,  INTRODUCTIONHTML , INTRODUCTIONMARKDOWN ,`DESCRIPTION` ,       IMAGE        ,COST_OF_EXAMINATION, CREATE_AT    )
VALUES         ( 'Bệnh viện Hữu nghị Việt Đức'     , 'Hoàn Kiếm,Hà Nội'    ,  '02438253531'  ,   'Writed'        , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       100000      ,'2023-10-10' ),
	           ( 'Bệnh viện Chợ Rẫy'               , 'Quận 5, TP.HCM'      ,  '028 3855 4138', 'Writed'          , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       200000      ,'2023-08-30' ),
			   ( 'Bệnh viện Đại học Y Dược'        , 'Quận 10, TP.HCM'     ,  '028 3855 8411', 'Writed'          , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       100000      ,'2023-09-20' ),
			   ( 'Bệnh Viện Quân Đội 108'          , 'Hai Bà Trưng, Hà Nội',  '0967 751 616' , 'Writed'          , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       100000      ,'2023-10-09' ),
	           ( 'Bệnh Viện Ung Bướu Hưng Việt'    , 'Hai Bà Trưng, Hà Nội',  '024 6250 0707', 'Writed'          , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       100000      ,'2023-12-10' ),
	           ( 'Hệ thống y tế MEDLATEC'          , 'Ba Đình, Hà Nội'     ,  '1900565656'   , 'Writed'          , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       120000      ,'2023-11-12' ),
	           ( 'TT xét nghiệm Diag Laboratories' , 'Quận 10, TP.HCM'     ,  '19001717'     , 'Writed'          , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       1500000     ,'2023-10-13' ),
	           ( 'Hệ thống Y tế Thu Cúc TCI'       , 'Tây Hồ, Hà Nội'      ,  ' 0936 388 288', 'Writed'          , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       100000      ,'2023-08-17' ),
	           ( 'Bệnh viện Đa khoa Hồng Phát'     , 'Hai Bà Trưng, Hà Nội',  '0962 279 115' , 'Writed'          , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       200000      ,'2023-09-20' ),
	           ( 'Phòng khám Quốc tế EXSON'        , 'Quận 10, TP.HCM'     ,  '028 3857 0670', 'Writed'          , 'Writed'             , 'Writed'     , 'anh_dai_dien.jpg ',       150000      ,'2023-08-25' );



INSERT INTO  
		Posts  (         TITLE        , CONTENMARKDOWN ,CONTENHTML , FOR_DOCTOR_ID , FOR_SPECIALIZATION_ID , FOR_CLINIC_ID ,FOR_PATIENT_ID,CONFIRM_BY_DOCTOR,   DATE_TIME_BOOKING  ,   IMAGE     , CREATE_AT   ,   UPDATE_AT   , DELETE_AT  )
VALUES         (  'Da liễu'           ,   'Breathed'   , 'Writed'  ,       2       ,           7           ,       3       ,     2        ,         1       , '2023-11-20 08:30:00','image1.jpg' ,'2023-10-10' , '2023-12-25'  ,'2023-12-30'),
	           (  'Sức Khỏe Tâm Thần' ,   'Breathed'   , 'Writed'  ,       3       ,           9           ,       4       ,     4        ,         1       , '2023-12-05 09:30:00','image2.jpg' ,'2023-08-30' , '2023-10-05'  ,'2023-12-25'),
			   (  'Thần Kinh'         ,   'Breathed'   , 'Writed'  ,       10      ,           8           ,       2       ,     2        ,         1       , '2023-10-15 10:30:00','image3.jpg' ,'2023-09-20' , '2023-11-02'  , '2023-11-30'),
			   (  'Vô Sinh- Hiếm Muộn',   'Breathed'   , 'Writed'  ,       6       ,           4           ,       8       ,     1        ,         1       , '2023-11-17 11:00:00','image4.jpg' ,'2023-10-09' , '2023-12-08'  ,'2023-11-30'),
	           (  'Thần Kinh'         ,   'Breathed'   , 'Writed'  ,       10      ,           2           ,       9       ,     8        ,         1       , '2023-09-18 11:30:00','image5.jpg' ,'2023-12-10' , '2023-12-09'  ,'2023-12-30'),
	           (  'Xương Khớp'        ,   'Breathed'   , 'Writed'  ,       10      ,           1           ,      10       ,     6        ,         1       , '2023-11-16 09:30:00','image6.jpg' ,'2023-11-12' , '2023-12-11'  ,'2023-12-30'),
	           (  'Tiêu Chảy'         ,   'Breathed'   , 'Writed'  ,       3       ,           3           ,       2       ,     7        ,         1       , '2023-12-25 10:30:00','image7.jpg' ,'2023-10-13' , '2023-12-10'  ,'2023-12-25'),
	           (  'Bện Viêm Gan'      ,   'Breathed'   , 'Writed'  ,       3       ,           2           ,       5       ,    10        ,         1       , '2023-11-30 15:30:00','image8.jpg' ,'2023-08-17' , '2023-10-18'  ,'2023-11-30'),
	           (  'Tai-Mũi-Họng'      ,   'Breathed'   , 'Writed'  ,       3       ,           6           ,       6       ,     5        ,         1       , '2023-11-30 15:30:00','image9.jpg' ,'2023-09-20' , '2023-10-19'  ,'2023-12-30'),
	           (  'Tim Mạch'          ,   'Breathed'   , 'Writed'  ,       6       ,           4           ,       7       ,     3        ,         1       , '2023-09-08 13:30:00','image10.jpg','2023-08-25' , '2023-09-20'  ,'2023-10-15');



INSERT INTO 
	 Comments  ( DOCTOR_ID ,   TIMEBOOKING  ,DATEBOOKING   ,        `NAME`       ,      PHONE    , CONTENT   ,   `STATUS`  , CREATE_AT   ,  UPDATE_AT   , DELETE_AT  )
VALUES         (     2     ,   '08:30:00'   , '2023-11-20' , 'Trịnh Mạnh Linh'   , '0903719999'  , 'Writed'  ,      1      ,'2023-10-10' , '2023-12-25'  ,'2023-12-30'),
	           (     3     ,   '09:30:00'   , '2023-12-05' , 'Đỗ Đức Toàn'       , '09037720068' , 'Writed'  ,      1      ,'2023-08-30' , '2023-10-05'  ,'2023-12-25'),
			   (     6     ,   '10:30:00'   , '2023-10-15' , 'Nguyễn Đăng Trương', '0903720078'  , 'Writed'  ,      1      ,'2023-09-20' , '2023-11-02'  ,'2023-11-30'),
			   (     10    ,   '11:00:00'   , '2023-11-17' , 'Nguyễn Văn Thắng'  , '0903720239'  , 'Writed'  ,      1      ,'2023-10-09' , '2023-12-08'  ,'2023-11-30'),
	           (     2     ,   '11:30:00'   , '2023-09-18' , 'Hồ Sỹ Nguyệt'      , '0903720343'  , 'Writed'  ,      1      ,'2023-12-10' , '2023-12-09'  ,'2023-12-30'),
	           (     10    ,   '09:30:00'   , '2023-11-16' , 'Nguyễn Văn Dinh'   , '0903720501'  , 'Writed'  ,      1      ,'2023-11-12' , '2023-12-11'  ,'2023-12-30'),
	           (     3     ,   '10:30:00'   , '2023-12-25' , 'Hồ Hữu Trình'      , '0903720724'  , 'Writed'  ,      1      ,'2023-10-13' , '2023-12-10'  ,'2023-12-25'),
	           (     6     ,   '15:30:00'   , '2023-11-30' , 'Hoàng Văn Bộ'      , '0903720747'  , 'Writed'  ,      1      ,'2023-08-17' , '2023-10-18'  ,'2023-11-30'),
	           (     10    ,   '13:00:00'   , '2023-10-10' , 'Hồ Văn Thanh'      , '0309720821'  , 'Writed'  ,      1      ,'2023-09-20' , '2023-10-19'  ,'2023-12-30'),
	           (     2     ,   '13:30:00'   , '2023-09-08' , 'Nguyễn Văn Thưởng' , '0903721184'  , 'Writed'  ,      1      ,'2023-08-25' , '2023-09-20'  ,'2023-10-15');



INSERT INTO  -- Địa Điểm
	Doctor_User( DOCTOR_ID ,CLINIC_ID,SPECIALIZATION_ID, CREATE_AT   ,   UPDATE_AT   , DELETE_AT  )
VALUES         (     2     ,    4    ,        2        ,'2023-10-10' , '2023-12-25'  ,'2023-12-30'),
	           (     3     ,    1    ,        1        ,'2023-08-30' , '2023-10-05'  ,'2023-12-25'),
			   (     6     ,    5    ,        6        ,'2023-09-20' , '2023-11-02'  ,'2023-11-30'),
			   (     10    ,    6    ,        4        ,'2023-10-09' , '2023-12-08'  ,'2023-11-30'),
	           (     10    ,    9    ,        2        ,'2023-12-10' , '2023-12-09'  ,'2023-12-30'),
	           (     2     ,    10   ,        9        ,'2023-11-12' , '2023-12-11'  ,'2023-12-30'),
	           (     3     ,    8    ,        1        ,'2023-10-13' , '2023-12-10'  ,'2023-12-25'),
	           (    10     ,    7    ,        8        ,'2023-08-17' , '2023-10-18'  ,'2023-11-30'),
	           (     6     ,    1    ,        4        ,'2023-09-20' , '2023-10-19'  ,'2023-12-30'),
	           (     6     ,    2    ,        5        ,'2023-08-25' , '2023-09-20'  ,'2023-10-15');
               
INSERT INTO
 SupporterLogs (PATIENT_ID ,   CONTENT   , CREATE_AT   ,   UPDATE_AT   , DELETE_AT  )
VALUES         (     2     ,'Supported'  ,'2023-10-10' , '2023-12-25'  ,'2023-12-30'),
	           (     3     ,'Supported'  ,'2023-08-30' , '2023-10-05'  ,'2023-12-25'),
			   (     1     ,'Supported'  ,'2023-09-20' , '2023-11-02'  ,'2023-11-30'),
			   (     4     ,'Supported'  ,'2023-10-09' , '2023-12-08'  ,'2023-11-30'),
	           (     6     ,'Supported'  ,'2023-12-10' , '2023-12-09'  ,'2023-12-30'),
	           (     7     ,'Supported'  ,'2023-11-12' , '2023-12-11'  ,'2023-12-30'),
	           (     9     ,'Supported'  ,'2023-10-13' , '2023-12-10'  ,'2023-12-25'),
	           (    10     , 'Supported' ,'2023-08-17' , '2023-10-18'  ,'2023-11-30'),
	           (     5     ,'Supported'  ,'2023-09-20' , '2023-10-19'  ,'2023-12-30'),
	           (     6     ,'Supported'  ,'2023-08-25' , '2023-09-20'  ,'2023-10-15');
                              
