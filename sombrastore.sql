CREATE DATABASE  IF NOT EXISTS `sombrastore` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sombrastore`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sombrastore
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` mediumtext,
  `price` int(11) DEFAULT NULL,
  `category` int(11) unsigned DEFAULT '0',
  `photo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `category_idx` (`category`),
  CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` (`id`, `name`, `description`, `price`, `category`, `photo`) VALUES (20,'Canon 600','Canon EOS 600D – усовершенствование модели 550D. Она унаследовала 18-мегапиксельную CMOS-матрицу и мощный процессор DIGIC 4, имеет диапазон чувствительности ISO до 6400, что позволяет получать четкие снимки даже при плохой освещенности.Canon EOS 600D – усовершенствование модели 550D. Она унаследовала 18-мегапиксельную CMOS-матрицу и мощный процессор DIGIC 4, имеет диапазон чувствительности ISO до 6400, что позволяет получать четкие снимки даже при плохой освещенности.\r\nКамера может снимать видео высокого разрешения формата FullHD со скоростью до 29.97 кадров в секунду, с автоматической подстройкой экспозиции, что позволит сконцентрироваться на снимаемом событии.\r\n\r\n9-точечная система автофокуса позволяет быстро фокусироваться как на статических объектах, так и на движущихся. Центральная точка перекрестного типа, чувствительная к горизонтальным и вертикальным линиям.\r\n\r\n63-зональный датчик замера экспозиции разделяет сцену на 63 области для анализа и поиска оптимального сочетания скорости затвора, диафрагмы и ISO.\r\n\r\nБольшой и четкий дисплей 3 дюйма (7.7 см) Clear View дает возможность легко компоновать кадр, просмотреть отснятые снимки и комфортно перемещатся по меню фотоаппарата.\r\n\r\nТехнология Basic+ позволяет настроить базовые авто настройки в соответствие со светом и окружением.\r\n\r\nВстроенный радиосинхронизатор позволяет использовать удаленные вспышки в режиме TTL без дополнительных аксессуаров.',900,2,'1 (2).png'),(44,'Scott Spark 700','Наши инженеры разработали новую технологию IMP™, применяемую при производстве карбоновых рам. Запатентованный процесс IMP™ позволяет исключить 11% материала в рулевом узле, увеличить его жесткость и снизить общий вес рамы. В сочетании с трубами Naked External Tubeset™, исключающие любые косметические слои карбона, структура рамы гарантирует прочность, износостойкость и безупречную функциональность.',250000,9,'1 (1).jpg'),(56,'Scott SCALE 700 SL','\r\nSDS: Shock Damping System\r\nСистема виброгашения SDS абсорбирует удары и сглаживает вибрации, без потерь в эффективности передвижения. Инженеры SCOTT достигли прекрасного баланса поперечной жесткость и продольной абсорбцией вибраций, за счет специальной карбоновой структуры труб заднего треугольника и вилки. Система добавляет уверенности в управлении и предоставляет максимальный комфорт. Применяется на моделях CR1 и Scale. ',250000,8,'1 (2).jpg'),(57,'Scott SCALE 735','\r\n27.5” Wheels\r\nКогда обсуждается вопрос «за» и «против» больших колес велосипеда, невысокие райдеры попадают в двойственное положение. Они признают преимущества больших колес 29”, но страдают от негативных последствий, где с ними приходится сталкиваться. Кроме того, большой вес вращения колес 29”, является проблемой для многих райдеров с невысокой мощностью педалирования. Таким образом, теряя мощность по ходу гонки, становиться сложнее ускорить вращение колеса достаточно быстро. Решением для многих стали колеса радиусом 27,5”. Этот новый стандарт сочетает в себе всё самое лучшее от стандартов 29” и 26”. • Увеличенный накат • Увеличенная инерционность • Увеличенная площадь сцепления • Ниже вес вращения чем 29” • Больше угол чем 29” • Больше возможностей регулировки подвески • Короткие задние нижние перья • Баланс стабильности и функциональности • Геометрия ближе к 26” • Прекрасный баланс маневренности и инерционности ',250000,8,'1 (3).jpg'),(58,'Scott GENIUS 950','\r\n\r\nTwinloc Lever System\r\nНаша запатентованная система удаленного управления работы вилки и амортизатора Twinloc. Открытая позиция предоставляет полный ход всей подвески. Один клик и амортизатор переходит в режим работы Traction, при этом вилка остается полностью активной. Еще один клик и система блокирует вилку и амортизатор, превращая байк в хардтейл. Система применяется на моделях Spark, Genius и Genius LT. ',250000,9,'1 (4).jpg'),(59,'Scott CR1 20','S-bend Construction\r\nПрофиль каждой трубы ориентирован на абсорбцию вибраций без компромиссов в поперечной жесткости. Мы создали оригинальные формы труб, которые также позволяют контролировать деформации заднего треугольника S-bend. SDS задние перья и вилка рамы компенсируют вибрации за счет уникальной карбоновой конструкции этих секций. ',250000,6,'1 (5).jpg'),(60,'Scott SPEED 20 FB ','AERO ALLOY FRAME\r\nНа 20% повышенные аэродинамические характеристики по круглой трубе велосипеда. Среднее снижение мощности на выходе 5%. ',250000,6,'1 (6).jpg'),(61,'Scott SPEED 40','Aero Carbon/Alloy Fork\r\nФорма вилки аналогична форме вилки Foil. Аэро профиль карбоновых ног. Алюминиевые дропауты, корона и шток. ',250000,6,'1 (7).jpg'),(62,'Scott SUB SPEED 20','Комфортные поездки, полезная физическая нагрузка и чувство бодрости одновременно - все это обновленный фитнес велосипед Scott Sub Speed 20. Байк сконструирован на базе новой жесткой рамы SUB Speed, с внутренней системой разводки тросов. Укомплектован полноразмерными крыльями, что позволяет райдеру защитить от загрязнений одежду во время катания. Путешествуйте, получая при этом только положительные эмоции вместе с Scott Sub Speed 20.',250000,6,'1 (8).jpg'),(63,'Giant TCR Advanced SL 0','<h3>Привід</h3></th></tr><tr><th>Манетки</th><td>Shimano Dura-Ace Di2, 11-speed</td></tr><tr><th>Перемикач передній</th><td>Shimano Dura-Ace Di2, 11-speed</td></tr><tr><th>Перемикач задній</th><td>Shimano Dura-Ace Di2, 11-speed</td></tr><tr><th>Гальма</th><td>Shimano Dura-Ace </td></tr><tr><th>Важелі гальм</th><td>Shimano Dura-Ace Di2</td></tr><tr><th>Касета</th><td> Shimano Dura-Ace 11-25, 11s</td></tr><tr><th>Ланцюг</th><td>KMC X11SL</td></tr><tr><th>Система шатунів</th><td>Shimano Dura-Ace, 36/52</td></tr><tr><th>Каретка</th><td>Shimano, Press Fit</td>',250000,6,'1 (9).jpg'),(64,'TCR Advanced 1 LTD','Using lightweight, race-proven Advanced-grade composite technology, Giant engineers created a high-performance frame that was designed with its critical parts as a single high-performance system. From the OverDrive steerer tube, to RideSense, an ANT+ compatible sensor built into the frame, to the ride-tuned Giant WheelSystem, it’s all designed with a common goal: optimized system performance. The frame also features the MegaDrive oversized downtube and the stiff, efficient PowerCore bottom bracket.\r\n\r\nEach frame features:\r\n\r\n    OverDrive steerer tube for additional steering stiffness and control\r\n    PowerCore bottom bracket for outstanding pedaling stiffness\r\n    Vector seatpost for minimal weight and maximum aerodynamics\r\n    Lightweight Advanced-grade composite frame material\r\n',250000,6,'1 (10).jpg'),(65,'Giant Lust 2','Lust features hydroformed ALUXX SL Aluminum Technology, specifically designed to fit the 27.5 wheel size and Liv’s women’s specifications. Combined with internal cable routing and integrated downtube and chainstay protection, and performance technology features such as OverDrive, PowerCore, and Maestro Suspension, the Lust offers premium off-road fit and performance.\r\n\r\n    OverDrive steerer tube system for maximum front end stiffness\r\n    142/135mm convertible dropout design\r\n    Optimized women’s-specific 27.5 geometry for ideal handling and fit',250000,9,'1 (11).jpg'),(66,'Merida Big.Nine CF Team','Merida Big.Nine Carbon Team-D – отличный горный велосипед с оригинальным дизайном. Спицы в этом транспорте изготовлены из высококачественного материала. На переключателях скоростей в велосипеде Merida Big.Nine Carbon Team-D имеются 20 передач. Амортизаторы в этом изделии способствуют спокойной и комфортной поездке. С данной моделью вы сможете легко преодолевать крутые спуски и подъемы. ',250000,8,'1 (12).jpg'),(67,'Merida Big.Nine TFS XT-D','Merida Big.Nine TFS XT-D - чудесная модель горного велосипеда. Смесь серебристого, белого и синего цветов добавит оригинальности дизайну данной модели. Амортизаторы в этом транспорте способствуют комфортной прогулке на велосипеде. Удобные тормоза в велосипеде Merida Big.Nine TFS XT-D помогут убавить скорость при необходимости. С данной моделью вы сможете легко преодолевать любые трудности на дороге.\r\n\r\nГарантия на этот товар 1 год. Более подробную техническую информацию про Merida Big.Nine TFS XT-D вы найдете в закладке «Характеристики». ',250000,8,'1 (12).jpg'),(68,'Merida Crossway 10-V','Merida Crossway 10-V - прекрасная модель гибридного велосипеда. С ним вы можете преодолеть любые дороги, будь это шумные улицы города или каменистая и неровная дорога бездорожья. Черный цвет в сочетании с серым придает контраста и яркости к дизайну велосипеда Merida Crossway 10-V. С данной моделью вы сможете легко преодолевать крутые спуски и подъемы и побеждать пробки в городе. ',250000,8,'1 (13).jpg'),(69,'Merida One-Twenty XT-D','Merida One-Twenty XT-D - это прекрасная модель горного велосипеда. Серый и белый цвета добавят оригинальности к дизайну этого транспорта. Амортизаторы велосипеда Merida One-Twenty XT-D способствуют комфортной прогулке на велосипеде. С данной моделью вы сможете легко преодолевать крутые спуски и подъемы. Если Вы хотите иметь красивое, подтянутое тело, тогда этот транспорт уже ждет Вас! ',250000,9,'1 (14).jpg'),(70,'Giant XTC 27.5 0 ','At the core of this performance XC hardtail is a lightweight ALUXX SL frameset designed to handle the specific geometry demands of 27.5-inch wheels. A smart mix of weight, comfort and performance handling are built into each frame, offering the more-advanced off-roader the opportunity to own a bona-fide hardtail.\r\n\r\n    ALUXX SL-grade, butted aluminum tubeset\r\n    27.5-inch wheel, optimized geometry for nimble XC riding characteristics\r\n    OverDrive steerer tube for pinpoint steering precision\r\n    Integrated rear disc tab',250000,8,'1 (15).jpg'),(71,'XTC SE 27.5','At the core of this performance XC hardtail is a lightweight ALUXX SL frameset designed to handle the specific geometry demands of 27.5-inch wheels. A smart mix of weight, comfort and performance handling are built into each frame, offering the more-advanced off-roader the opportunity to own a bona-fide hardtail.\r\n\r\n    ALUXX SL-grade, butted aluminum tubeset\r\n    27.5-inch wheel, optimized geometry for nimble XC riding characteristics',250000,8,'1 (16).jpg'),(72,'Giant TCR Advanced Pro 1 (compact)','Using lightweight, race-proven Advanced-grade composite technology, Giant engineers created a high-performance frame that was designed with its critical part as a single high-performance system. From the OverDrive 2 steerer tube, to RideSense, an ANT+ compatible sensor built into the frame, to the ride-tuned Giant WheelSystem, it’s all designed with a common goal: optimized system performance. The frame also features the MegaDrive oversized downtube and the stiff, efficient PowerCore bottom bracket.\r\n\r\nEach frame features:\r\n\r\n    OverDrive 2 steerer tube and Advanced-grade full composite fork for superb steering stiffness and control\r\n    PowerCore bottom bracket for outstanding pedaling stiffness\r\n    Vector seatpost for minimal weight and maximum aerodynamics\r\n    Lightweight Advanced-grade composite frame material\r\n',250000,6,'1 (17).jpg'),(73,'Kellis Swag 10','Велосипед Kellys Swag 10 – это горный универсальный велосипед, который среди любителей называют Двухподвес или Трейловый байк.\r\n\r\nЭтот вид велосипеда стал очень популярным среди тех, кто участвует в марафонах и соревнованиях на выносливость. Агрессивные спуски, кросс-кантри, дерт, даунхилл и фрирайд – все эти виды катания вы сможете осуществить на этом велосипеде, благодаря оборудованию, которое объединяет в себе жесткую раму и качественную подвеску.\r\n\r\nДвухподвес отличается от горных MTB тем, что оборудован двумя амортизаторами на обоих колеса. Благодаря этому отличию, велосипед справится с любым дорожным и внедорожным покрытием, будет хорошо управляем, сэкономить вашу энергию и правильно распределит силы во время длительных спусков по бездорожью и горной местности.\r\n\r\nАгрессивное катания стало модным по всем миру, поэтому производители, борясь за лицензию и баланс между мягкостью подвески и эффективностью педалирования, пытаются придумать свой вид задней подвески и его размещения. Но эта борьба никак не повлияет на качество и производительность велосипедов Kellys и Trek, так как они являются лидерами в категории «качество-цена» в классе велосипедов MTB Marathon.\r\n\r\nОборудован: рама из алюминиевого сплава Kellys SFS Supertrail Allmountain-Enduro SWT 7005, амортизационная вилка - SR Suntour Raidon RL-R с ходом  140мм, задний амортизатор - RockShox Monarch RL, навесное оборудование Shimano Acera и переключатели Shimano SLX / Deore с трансмиссией на 27 скоростей, гидравлические дисковые тормоза Avid DB1.\r\n\r\nВелосипед двухподвес Kellys Swag 10 – правильный выбор для любителей агрессивного катания по бездорожью или горной местности. Подойдет для соревнований, марафонов, дерта, кросс-кантри и катания в свое удовольствие.',50000,9,'swag_10.jpg'),(74,'Kellis Stade 90','Завдяки великим колесам, велосипед володіє більшою швидкістю, а також кращою стійкістю на поворотах. Товщина шини забезпечує велику площу контакту і пом\'якшує їзду по пересіченій місцевості. Занижений центр ваги і великі колеса трохи заважають в процесі вздибанія переднім колесом і різкими \"банні хопами\". Але, Ви набагато комфортніше себе почуваєте на крутих схилах і пересіченій місцевості, для яких і створений 29-й :)',50000,8,'stage_90.jpg');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baskets`
--

DROP TABLE IF EXISTS `baskets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baskets` (
  `user_id` int(10) unsigned NOT NULL,
  `article_id` int(10) unsigned NOT NULL,
  `count` int(11) DEFAULT NULL,
  KEY `article_idx` (`article_id`),
  KEY `user_idx` (`user_id`),
  CONSTRAINT `article` FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baskets`
--

LOCK TABLES `baskets` WRITE;
/*!40000 ALTER TABLE `baskets` DISABLE KEYS */;
/*!40000 ALTER TABLE `baskets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `parent_category` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `parent_idx` (`parent_category`),
  CONSTRAINT `parent` FOREIGN KEY (`parent_category`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`id`, `name`, `description`, `parent_category`) VALUES (0,'Без категорії','Без категорії',NULL),(1,'Cameras','Digital photo cameras',NULL),(2,'Canon','Canon digital cameras',1),(3,'Nikon','Nikon digital cameras',1),(4,'Bikes','Bikes',NULL),(5,'MTB','MTB bikes',4),(6,'Road','Road bikes',4),(7,'Phone','Mobile phones',NULL),(8,'Hardtail','Bike with suspension fork',5),(9,'Full suspension',NULL,5);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_articles`
--

DROP TABLE IF EXISTS `order_articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_articles` (
  `order_id` int(10) unsigned NOT NULL,
  `article_id` int(10) unsigned NOT NULL,
  `count` int(10) unsigned DEFAULT NULL,
  KEY `fk_order_idx` (`order_id`),
  KEY `fk_article_idx` (`article_id`),
  CONSTRAINT `fk_article` FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_articles`
--

LOCK TABLES `order_articles` WRITE;
/*!40000 ALTER TABLE `order_articles` DISABLE KEYS */;
INSERT INTO `order_articles` (`order_id`, `article_id`, `count`) VALUES (60,20,1),(61,63,1),(62,65,1),(63,73,1),(64,70,1),(65,20,1),(66,63,1),(67,20,1),(68,63,1),(69,20,1),(70,20,1),(71,73,1);
/*!40000 ALTER TABLE `order_articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userEmail` varchar(45) NOT NULL,
  `userPhone` varchar(45) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `userEmail`, `userPhone`, `date`, `state`, `price`) VALUES (60,'admin@mail.com','+380634355889','2015-02-03 22:06:51',0,900),(61,'admin@mail.com','+380634355889','2015-02-03 22:06:58',1,250000),(62,'admin@mail.com','+380634355889','2015-02-03 22:07:08',0,250000),(63,'admin@mail.com','+380634355889','2015-02-03 22:07:16',1,50000),(64,'admin@mail.com','+380634355889','2015-02-03 22:07:25',0,250000),(65,'admin@mail.com','+380634355889','2015-02-03 22:07:34',0,900),(66,'admin@mail.com','+380634355889','2015-02-03 22:07:55',2,250000),(67,'admin@mail.com','+380634355889','2015-02-03 22:08:03',0,900),(68,'admin@mail.com','+380634355889','2015-02-03 22:08:10',1,250000),(69,'admin@mail.com','+380634355889','2015-02-03 22:08:17',0,900),(70,'admin@mail.com','+380634355889','2015-02-03 22:08:25',2,900),(71,'admin@mail.com','+380634355889','2015-02-03 22:08:34',0,50000);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`, `description`) VALUES (1,'ROLE_USER','For registered users'),(2,'ROLE_ADMIN','For administrators');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` int(1) NOT NULL DEFAULT '1',
  `role_id` int(10) unsigned NOT NULL,
  `reg_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `role_idx` (`role_id`),
  CONSTRAINT `role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `name`, `email`, `phone`, `password`, `enabled`, `role_id`, `reg_date`) VALUES (10,'admin','admin@mail.com','+380634355889','$2a$10$YW5RA1ZUkjgDi9ZzCURUTebVoCvC7DA3jgReszT8FX41V25OqESfy',1,2,'2015-02-01'),(13,'user','user@mail.com','+380987456321','$2a$10$cCqk9jQK7lbsU.c3uLjQeu2esnLWTdAJE0DSDgYaTwfP8CNjqbIfK',1,1,'2015-02-04');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-04  2:27:59
