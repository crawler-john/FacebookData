#创建数据库
create database facebookdata default charset utf8;
#采集facebook上对应的数据
CREATE TABLE `t_university` (
	`id`  int(20) NOT NULL AUTO_INCREMENT ,
	`schoolName` varchar(100) NOT NULL,
	`schoolName_FaceBook` varchar(100) NOT NULL,
	`schoolId`  bigint,
	`about` text,
	`can_post` boolean,
	`description` text,
	`founded` int,
	`general_info` text,
	`global_brand_page_name` varchar(200),
	`is_always_open` boolean,
	`is_community_page` boolean,
	`is_publiced` boolean,
	`is_verified` boolean,
	`link` varchar(100),
	`phone` varchar(20),
	`talking_about_count` int,
	`username` varchar(50),
	`website` varchar(50),
	`were_here_count` int,
	`checkins` int,
	`likes` int,
	`location_city` varchar(30),
	`location_country` varchar(30),
	`location_latitude` double,
	`location_longitude` double,
	`location_state` varchar(20),
	`location_street` varchar(30),
	`location_zip` varchar(20),
	`state`  enum('1','0') ,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB default charset utf8;

#采集facebook上各个大学对应相册
CREATE TABLE `t_university_albums` (
	`schoolId`  bigint  NOT NULL ,
	`albumId` bigint  NOT NULL ,
	`created_time` varchar(50) ,
	`name` varchar(50) ,
	PRIMARY KEY (`schoolId`,`albumId`) 

)ENGINE=InnoDB default charset utf8;

#采集facebook上各个大学对应feed
CREATE TABLE `t_university_feeds` (
	`schoolId`  bigint  NOT NULL ,
	`feedId` varchar(50)  NOT NULL ,
	`created_time` varchar(50) ,
	`story` text,
	`message` text,
	PRIMARY KEY (`schoolId`,`feedId`) 
)ENGINE=InnoDB default charset utf8;


#采集facebook上各个大学对应milestones
CREATE TABLE `t_university_milestones` (
	`schoolId`  bigint  NOT NULL ,
	`milestoneId` bigint  NOT NULL ,
	`created_time` varchar(50) ,
	`description` text,
	`end_time` varchar(50) ,
	`is_hidden` boolean,
	`from_id` bigint,
	`from_name` varchar(50),
	`start_time`  varchar(50) ,
	`title` varchar(200),
	`updated_time`  varchar(50) ,
	PRIMARY KEY (`schoolId`,`milestoneId`) 
)ENGINE=InnoDB default charset utf8;

#采集facebook上各个大学对应photos
CREATE TABLE `t_university_photos` (
	`schoolId`  bigint  NOT NULL ,
	`photoId` bigint  NOT NULL ,
	`created_time` varchar(50) ,
	`name` varchar(300),
	PRIMARY KEY (`schoolId`,`photoId`) 
	
)ENGINE=InnoDB default charset utf8;

#采集facebook上各个大学对应videos
CREATE TABLE `t_university_videos` (
	`schoolId`  bigint  NOT NULL ,
	`videoId` bigint  NOT NULL ,
	`description` varchar(300),
	`updated_time` varchar(50),
	PRIMARY KEY (`schoolId`,`videoId`) 
)ENGINE=InnoDB default charset utf8;


#采集facebook上各个大学对应videos
CREATE TABLE `t_university_category` (
	`schoolId`  bigint  NOT NULL ,
	`categoryId` bigint  NOT NULL ,
	`name` varchar(50),
	
	PRIMARY KEY (`schoolId`,`categoryId`) 
)ENGINE=InnoDB default charset utf8;
#采集facebook上各个大学对应emails
CREATE TABLE `t_university_emails` (
	`schoolId`  bigint  NOT NULL ,
	`emails` varchar(50),
	
	PRIMARY KEY (`schoolId`,`emails`) 
)ENGINE=InnoDB default charset utf8;
