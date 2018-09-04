
-- 当名称改为data.sql时，启动时会被执行     springboot2.0默认关闭

insert into admin(id,username,password,rememberMe) values (null,'admin','038bdaf98f2037b31f1e75b5b4c9b26e',0);
insert into admin(id,username,password,rememberMe) values (null,'wangtao','318599de7d56ad4c132774427f84911a',1);



INSERT INTO `systemroles`(`id`, `orderNo`, `roleName`) VALUES (1, NULL, 'admin');
INSERT INTO `systemroles`(`id`, `orderNo`, `roleName`) VALUES (2, NULL, 'super');
INSERT INTO `systemroles`(`id`, `orderNo`, `roleName`) VALUES (3, NULL, 'user');
INSERT INTO `systemroles`(`id`, `orderNo`, `roleName`) VALUES (4, NULL, 'db');
INSERT INTO `systemroles`(`id`, `orderNo`, `roleName`) VALUES (5, NULL, 'guest');



INSERT INTO `systempermission`(`id`, `orderNo`, `permissionName`, `remark`) VALUES (1, NULL, 'select', NULL);
INSERT INTO `systempermission`(`id`, `orderNo`, `permissionName`, `remark`) VALUES (2, NULL, 'update', NULL);
INSERT INTO `systempermission`(`id`, `orderNo`, `permissionName`, `remark`) VALUES (3, NULL, 'insert', NULL);
INSERT INTO `systempermission`(`id`, `orderNo`, `permissionName`, `remark`) VALUES (4, NULL, 'delete', NULL);
INSERT INTO `systempermission`(`id`, `orderNo`, `permissionName`, `remark`) VALUES (5, NULL, 'all', NULL);


INSERT INTO `adminpermission`(`id`, `admin_id`, `permission_id`) VALUES (1, 1, 1);
INSERT INTO `adminpermission`(`id`, `admin_id`, `permission_id`) VALUES (2, 2, 1);
INSERT INTO `adminpermission`(`id`, `admin_id`, `permission_id`) VALUES (3, 1, 2);
INSERT INTO `adminpermission`(`id`, `admin_id`, `permission_id`) VALUES (4, 1, 3);


INSERT INTO `adminroles`(`id`, `admin_id`, `role_id`) VALUES (1, 1, 1);
INSERT INTO `adminroles`(`id`, `admin_id`, `role_id`) VALUES (2, 1, 2);
INSERT INTO `adminroles`(`id`, `admin_id`, `role_id`) VALUES (3, 1, 3);

