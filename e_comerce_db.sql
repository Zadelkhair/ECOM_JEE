-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2021 at 03:29 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e_comerce_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `actions`
--

CREATE TABLE `actions` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `advertisements`
--

CREATE TABLE `advertisements` (
  `id` int(11) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `position` int(11) NOT NULL DEFAULT -1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `advertisements`
--

INSERT INTO `advertisements` (`id`, `image`, `description`, `position`) VALUES
(1, 'uploads\\images\\ads_6dda8dfa-d433-4dd7-ba4e-b1b138fea3bf_DPS-Makeup-Essentials-Pt1_07.jpg', 'Shop the best new makeup arrivals now,	', 4),
(2, 'uploads\\images\\ads_7b50df6b-1744-4cc0-8dbb-11815ca15322_81541636-fashion-summer-women-clothes-set-with-accessories-flat-lay-top-view.jpg', 'It\'s all about cute summer outfits. Summer is here and it\'s time to celebrate with summer clothes for women.	', 3),
(3, 'uploads\\images\\ads_b3a0ad46-8444-486d-bc01-a642f0945aa5_102654925-summer-women-s-clothing-and-accessories-on-a-brown-background.jpg', 'Shop Women\'s Shoes at DSW. Check out our huge selection with free shipping every day!	', 1),
(4, 'uploads\\images\\ads_e71a87b3-17f6-4755-bba7-9bb1f2f902fb_2019-Kids-Clothes-Children-Clothing-Sets-Girls-Boutique-Outfits-Toddler-Fashion-Fall-Autumn-Top-Jeans-3.jpg', 'At The Children\'s Place, we\'ve got kids clothes in every size, color and trend-&#8203;setting style. ', 5),
(5, 'uploads\\images\\ads_de409638-a9ca-4e71-85c9-6abf35f9e41e_595763b8588e0d4e3246fbde-large.jpg', 'Shop for children\'s clothing at next.co.uk. Next day delivery and free returns available. ', 2),
(6, 'uploads\\images\\ads_26eb6d8b-5fdf-4259-8148-8fdbae478e04_https___ae01.alicdn.com_kf_HTB1mYiNa6LuK1Rjy0Fhq6xpdFXaB_Baby-Girls-Clothing-Sets-2020-Summer-fashion-Print-Tops-and-Shorts-Kids-Clothes-Suits-3-4.jpg', 'At The Children\'s Place, we\'ve got kids clothes in every size, color and trend-&#8203;setting style.', 13),
(7, 'uploads\\images\\ads_3e55b8c5-4c44-4252-9681-778d1f5eb3c3_6f874bf5887f01caab908ed0d4bdc110.jpg', 'Regarding Hijab clothing products, Modanisa.com present a wide range of quality products. New season Hijab clothing is one click away', 6),
(8, 'uploads\\images\\ads_8825903e-2fcf-4b89-963f-c52ef34eb043_category-3.jpg', 'Check out our couple clothes selection for the very best in unique or custom, handmade pieces from our t-shirts shops', 8),
(9, 'uploads\\images\\ads_ca456c2d-4fd9-4f77-9bce-9ab2c46ae0c7_14434.jpg', 'Safe Trading Kitchen Accessory on Leading B2B Platform. Get Factory Price on Kitchen Accessory. Highlights: Multiple Quotes Available, New User Guide Available', 9),
(10, 'uploads\\images\\ads_51dc712c-5217-42cf-ac6e-3280ffccf0fa_category-4.jpg', 'Discover the latest in beauty at Sephora. Explore our unrivaled selection of makeup, skin care, fragrance and more from classic and emerging brands.\r\n', -1),
(11, 'uploads\\images\\ads_1ecfd3a5-0281-4fcb-bbd7-6d46af227aae_shutterstock_384358651-1024x702-1.jpg', 'Here are 17 cleaning products to clean your entire house, from the bathroom to the living room: · Anywhere: Dyson V11 Outsize Cordless Vacuum.', 11),
(12, 'uploads\\images\\ads_a5efbbbd-de6e-4609-b059-b109949514ed_beauty-products-1588098976.jpg', 'The UK\'s no.1 fragrance only retailer with free delivery on Eau de Parfum and Eau de Toilette by brands including Chanel, Dior, Tom Ford and Paco Rabanne.\r\n', 10);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `fa_icon` varchar(250) NOT NULL DEFAULT 'fas fa-circle'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`, `fa_icon`) VALUES
(1, 'categorie 1', 'categorie 1', 'fas fa-caret-square-right'),
(4, 'categorie 2', 'categorie 2', 'fas fa-circle'),
(5, 'categorie 3', 'categorie 3', 'fas fa-caret-square-right');

-- --------------------------------------------------------

--
-- Table structure for table `files`
--

CREATE TABLE `files` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `filename` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `delivery_charges` float DEFAULT NULL,
  `transaction_status` int(11) DEFAULT NULL,
  `ship_date` date DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `delivery_charges`, `transaction_status`, `ship_date`, `order_date`, `user_id`, `price`) VALUES
(1, 0, 1, NULL, '2021-06-19', 2, 1800),
(2, 0, -1, NULL, '2021-06-20', 2, 1500),
(3, 0, 1, NULL, '2021-06-21', 2, 1800),
(4, 0, 1, NULL, '2021-06-21', 5, 4000),
(5, 0, 0, NULL, '2021-06-21', 5, 600),
(6, 0, -1, NULL, '2021-06-21', 5, 200),
(7, 0, 0, NULL, '2021-06-21', 5, 200),
(8, 0, 0, NULL, '2021-06-21', 5, 1800),
(9, 0, 1, NULL, '2021-06-21', 5, 1600),
(10, 0, -1, NULL, '2021-06-22', 9, 2900),
(11, 0, 0, NULL, '2021-06-26', 2, 3000);

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `order_quantity` int(11) DEFAULT NULL,
  `order_price` float DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`id`, `order_quantity`, `order_price`, `product_id`, `order_id`) VALUES
(1, 6, 1200, 6, 1),
(2, 3, 600, 1, 1),
(3, 2, 400, 6, 2),
(4, 4, 800, 1, 2),
(5, 1, 300, 10, 2),
(6, 5, 1000, 8, 3),
(7, 4, 800, 7, 3),
(8, 2, 400, 8, 4),
(9, 3, 600, 7, 4),
(10, 6, 1200, 6, 4),
(11, 6, 1800, 10, 4),
(12, 3, 600, 1, 5),
(13, 1, 200, 6, 6),
(14, 1, 200, 6, 7),
(15, 4, 800, 6, 8),
(16, 1, 200, 1, 8),
(17, 4, 800, 7, 8),
(18, 5, 1000, 7, 9),
(19, 3, 600, 6, 9),
(20, 2, 400, 8, 10),
(21, 3, 900, 10, 10),
(22, 5, 1000, 6, 10),
(23, 3, 600, 7, 10),
(24, 2, 400, 8, 11),
(25, 4, 1200, 10, 11),
(26, 3, 600, 7, 11),
(27, 4, 800, 6, 11);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` int(11) NOT NULL,
  `shipping_fees` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `id` int(11) NOT NULL,
  `action_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `garmentType` varchar(10) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `image` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `product_name`, `price`, `size`, `color`, `garmentType`, `rating`, `supplier_id`, `category_id`, `image`) VALUES
(1, 'Product 1', 200, '200cm', 'red', 'type1', 3, 1, 1, 'img\\prod.jpg'),
(6, 'Product 4', 200, '200cm', 'red', 'type2', 3, 1, 1, 'uploads\\images\\Product_4_de352c00-98a1-42cf-9985-bcdbb18ef537_download.png'),
(7, 'Product 4', 200, '200cm', 'green', 'type2', 3, 1, 1, 'uploads\\images\\Product_4_d9de32f3-05bd-4ff9-bd56-14abedc07090_1542077976246443.jpg'),
(8, 'Product 5', 200, '200cm', 'green', 'type2', 3, 1, 1, 'uploads\\images\\Product_5_1b8d10a8-ade4-497e-ac01-a613ded375b1_products.png'),
(10, 'Product 6', 300, '200cm', 'purple', 'type2', 4, 1, 1, 'img\\prod.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `product_orders`
--

CREATE TABLE `product_orders` (
  `quantity` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT 0,
  `delivery_date` date DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `rate` int(11) DEFAULT 0,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `description`, `rate`, `product_id`, `user_id`) VALUES
(2, 'Review 1', 3, 1, 2),
(3, 'Review 2', 4, 1, 5),
(4, 'i dont like it :(', 1, 1, 8),
(6, 'the best product ever ever', 5, 10, 5),
(8, 'Change my review', 4, 6, 5);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'admin'),
(7, 'user'),
(8, 'superadmin');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` int(11) NOT NULL,
  `supplier_name` varchar(100) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `country` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id`, `supplier_name`, `address`, `country`) VALUES
(1, 'Nike', 'Thailand', 'Thailand');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `ship_address` varchar(100) DEFAULT NULL,
  `role_id` int(11) DEFAULT 7
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `address`, `city`, `country`, `phone`, `email`, `password`, `ship_address`, `role_id`) VALUES
(2, 'admin', '104 RUE SOUSSA HAY NAIMA', 'Berrechid', 'Morocco', '0600000002', 'admin@admin.com', 'admin@password', '104 RUE SOUSSA HAY NAIMA', 1),
(5, 'user', '104 RUE SOUSSA HAY NAIMA', 'Berrechid', 'Morocco', '0662855677', 'user@user.com', '', '104 RUE SOUSSA HAY NAIMA', 7),
(6, 'hamza', '104 RUE SOUSSA HAY NAIMA', 'Berrechid', 'Morocco', '0600000000', 'hamza@hamza.com', '', '104 RUE SOUSSA HAY NAIMA', 7),
(8, 'zadelkhair', '104 RUE SOUSSA HAY NAIMA', '', '', '0680096104', 'zad@zad.com', 'zadelkhair@password', '', 1),
(9, 'YasserZad', '104 RUE SOUSSA HAY NAIMA', '', '', '0680096104', 'yasser@zad.com', '', '104 RUE SOUSSA HAY NAIMA', 8),
(10, 'newuser', '104 RUE SOUSSA HAY NAIMA', 'Berrechid', 'Morocco', '0600000001', 'newuser@gmail.com', 'newuser@password', '104 RUE SOUSSA HAY NAIMA', 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actions`
--
ALTER TABLE `actions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `advertisements`
--
ALTER TABLE `advertisements`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `files`
--
ALTER TABLE `files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `action_id` (`action_id`),
  ADD KEY `role_id` (`role_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `supplier_id` (`supplier_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `product_orders`
--
ALTER TABLE `product_orders`
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `actions`
--
ALTER TABLE `actions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `advertisements`
--
ALTER TABLE `advertisements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `files`
--
ALTER TABLE `files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `permissions`
--
ALTER TABLE `permissions`
  ADD CONSTRAINT `permissions_ibfk_2` FOREIGN KEY (`action_id`) REFERENCES `actions` (`id`),
  ADD CONSTRAINT `permissions_ibfk_3` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `product_orders`
--
ALTER TABLE `product_orders`
  ADD CONSTRAINT `product_orders_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `product_orders_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
