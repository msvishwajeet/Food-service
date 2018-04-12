CREATE TABLE `admins` (
  `name` varchar(50) NOT NULL,
  `contactNumber` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(20)NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
)

 CREATE TABLE `restaurent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `restaurentName` varchar(40)  NOT NULL,
  `contactNumber` varchar(15) NOT NULL,
  `pin` varchar(8) NOT NULL,
  `stateName` varchar(20)NOT NULL,
  `cityName` varchar(20) NOT NULL,
  `menuId` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
)

 CREATE TABLE `ordered` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `orderId` varchar(60) NOT NULL,
  `contactNumber` varchar(20) NOT NULL,
  `orderItem` varchar(255) NOT NULL,
  `bill` int(20) NOT NULL,
  `dateOfOrder` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dishName` varchar(50) NOT NULL,
  `dishPrice` varchar(20) NOT NULL,
  `resturentId` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `menu_non` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dishName` varchar(55) NOT NULL,
  `dishPrice` varchar(20) NOT NULL,
  `restaurentId` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
)
