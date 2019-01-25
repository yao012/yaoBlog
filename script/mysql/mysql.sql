create DATABASE yao_blog;

-- 用户表 --
CREATE TABLE `yao_blog`.`users` (
  `id` varchar(40) COLLATE utf8_bin NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `password` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `passwordMd5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `regTime` datetime NOT NULL DEFAULT '2010-01-01 00:00:00',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;