--role
INSERT INTO `webinvoice`.`role` (`id`, `name`) VALUES (1, 'USER');
INSERT INTO `webinvoice`.`role` (`id`, `name`) VALUES (2, 'ADMIN');

--user
INSERT INTO `webinvoice`.`user` (`id`, `firstname`, `lastname`, `logintimestamp`, `password`, `username`, `roleID`) VALUES (1, 'Manpreet', 'Sushil', '2013-05-10 10:00:00', 'passwd', 'manssster', 1);
INSERT INTO `webinvoice`.`user` (`id`, `firstname`, `lastname`, `logintimestamp`, `password`, `username`, `roleID`) VALUES (2, 'Alex ', 'Wang', '2013-05-10 10:00:00', 'passwd', 'alex', 2);

--address

