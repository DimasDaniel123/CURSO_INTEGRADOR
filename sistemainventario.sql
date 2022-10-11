-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-10-2022 a las 19:13:55
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemainventario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `categoria` varchar(50) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `categoria`) VALUES
(4, 'Cremas'),
(5, 'Emulsiones'),
(6, 'Mascarillas de belleza'),
(7, 'Polvos para maquillaje'),
(8, 'Jabones de tocador'),
(9, 'Jabones desodorantes'),
(10, 'Productos antiarrugas'),
(11, 'Productos para blanqueo de la piel'),
(12, 'Productos para el sol'),
(13, 'Productos de higiene íntima externa'),
(14, 'Productos para el cuidado'),
(15, 'Maquillaje y productos para desmaquillar'),
(16, 'Jabones'),
(17, 'Espumas'),
(18, 'Lociones'),
(19, 'Lacas'),
(20, 'Aceites'),
(21, 'Geles'),
(22, 'Espumas'),
(23, 'Colonia'),
(24, 'Alisado '),
(25, 'Depilatorios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `dni` int(8) NOT NULL,
  `nombre` varchar(180) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` int(15) NOT NULL,
  `direccion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `Razon` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `dni`, `nombre`, `telefono`, `direccion`, `Razon`) VALUES
(1, 71618123, 'Dimas Daniel', 999333666, 'Av. Lurin', 'cliente'),
(2, 71618124, 'Juan Perez', 999333777, 'Av. Lima', 'cliente'),
(3, 71618125, 'Pablo Suarez', 999333888, 'Av. Lima', 'cliente'),
(4, 71618126, 'Miguel Angel', 999333999, 'Av. Lima', 'cliente'),
(5, 71618127, 'Anthony', 999333111, 'Av. Lima', 'cliente'),
(63, 71612335, 'Juan Antonio', 963214321, 'Av.Lima', 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `id` int(11) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`id`, `id_proveedor`, `total`, `fecha`) VALUES
(1, 44, '20.00', '2022-07-18 06:04:11'),
(2, 41, '10.00', '2022-07-18 06:04:59'),
(3, 42, '10.00', '2022-07-18 06:06:10'),
(4, 44, '20.00', '2022-07-20 19:14:34'),
(5, 29, '20.00', '2022-07-20 20:45:32'),
(6, 44, '160.00', '2022-07-22 05:25:45'),
(7, 29, '20.00', '2022-07-22 15:54:25'),
(8, 29, '20.00', '2022-10-10 03:11:43'),
(9, 29, '4.00', '2022-10-10 03:15:14'),
(10, 29, '162.00', '2022-10-10 15:59:33');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_compra`
--

CREATE TABLE `detalle_compra` (
  `id` int(11) NOT NULL,
  `id_compra` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `detalle_compra`
--

INSERT INTO `detalle_compra` (`id`, `id_compra`, `id_producto`, `precio`, `cantidad`, `subtotal`, `fecha`) VALUES
(1, 1, 22, '2.00', 10, '20.00', '2022-07-18 06:04:11'),
(2, 2, 20, '1.00', 10, '10.00', '2022-07-18 06:04:59'),
(3, 3, 17, '1.00', 10, '10.00', '2022-07-18 06:06:10'),
(4, 4, 22, '2.00', 10, '20.00', '2022-07-20 19:14:34'),
(5, 5, 13, '2.00', 10, '20.00', '2022-07-20 20:45:32'),
(6, 6, 22, '2.00', 80, '160.00', '2022-07-22 05:25:45'),
(7, 7, 13, '2.00', 10, '20.00', '2022-07-22 15:54:25'),
(8, 8, 13, '2.00', 10, '20.00', '2022-10-10 03:11:43'),
(9, 9, 13, '2.00', 2, '4.00', '2022-10-10 03:15:14'),
(10, 10, 13, '54.00', 3, '162.00', '2022-10-10 15:59:33');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `id` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detalle_venta`
--

INSERT INTO `detalle_venta` (`id`, `id_venta`, `id_producto`, `precio`, `cantidad`, `subtotal`, `fecha`) VALUES
(1, 1, 13, '2.50', 10, '25.00', '2022-07-22 00:10:47'),
(2, 2, 22, '2.90', 20, '58.00', '2022-07-22 05:26:21'),
(3, 3, 13, '2.50', 100, '250.00', '2022-07-22 05:30:05'),
(4, 4, 13, '2.50', 10, '25.00', '2022-07-22 18:30:32'),
(5, 5, 22, '2.90', 10, '29.00', '2022-07-22 18:32:23'),
(6, 6, 22, '2.90', 10, '29.00', '2022-07-22 18:45:33'),
(7, 7, 13, '2.00', 1, '2.00', '2022-10-08 03:04:00'),
(8, 8, 13, '2.50', 10, '25.00', '2022-10-08 03:22:12'),
(9, 9, 13, '2.50', 10, '25.00', '2022-10-08 03:22:50'),
(10, 11, 13, '2.50', 10, '25.00', '2022-10-08 22:23:27'),
(11, 12, 13, '2.50', 3, '7.50', '2022-10-08 22:24:10'),
(12, 12, 16, '2.00', 30, '60.00', '2022-10-08 22:24:10'),
(13, 13, 13, '2.50', 5, '12.50', '2022-10-10 00:54:47'),
(14, 14, 13, '2.50', 101, '252.50', '2022-10-10 03:11:59'),
(15, 27, 13, '49.90', 2, '99.80', '2022-10-10 15:58:57'),
(16, 28, 13, '49.90', 20, '998.00', '2022-10-10 16:10:55'),
(17, 28, 15, '54.90', 3, '164.70', '2022-10-10 16:10:55');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `codigo` varchar(20) CHARACTER SET latin1 NOT NULL,
  `nombre` varchar(255) CHARACTER SET latin1 NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `stock` int(11) NOT NULL DEFAULT 0,
  `precio` decimal(10,2) NOT NULL,
  `precio_venta` decimal(10,2) NOT NULL,
  `id_categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `codigo`, `nombre`, `id_proveedor`, `stock`, `precio`, `precio_venta`, `id_categoria`) VALUES
(13, '1001', 'Máscara de pestañas Mega Full Size a prueba de agua', 29, 31, '54.00', '49.90', 14),
(15, '1002', 'Colorfix Máscara de pestañas Ultimate Volume.', 41, 57, '58.00', '54.90', 13),
(16, '1003', 'Colorfix Delineador Plumón Tattoo Metálico\n', 42, 87, '50.00', '47.90', 14),
(17, '1004', 'Paleta de sombras profesional Eye PRO\n', 42, 30, '94.00', '88.90', 24),
(18, '1005', 'Vibranza Perfume de Mujer, 45 ml\n', 43, 200, '138.00', '129.90', 23),
(19, '1006', 'Impredecible Eau de Parfum, 50 ml\n', 39, 200, '110.00', '104.90', 4),
(26, '1013', 'Uno Perfume para Hombre, 90 ml\n', 29, 52, '170.00', '161.90', 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL,
  `ruc` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `proveedor` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `Razon` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `ruc`, `proveedor`, `telefono`, `direccion`, `Razon`) VALUES
(29, '21321321321', 'Mary key', '123345', 'Av. Jose Galvez', 'Mary key SAC'),
(39, '20511358907', 'Natura', '3490114', 'Cal. Renee Descartes Nro. 114', 'Natura S.A.C'),
(41, '20100050359', 'Oriflame', '6121900', 'Av. la Molina Nr', 'Oriflame S A'),
(42, '20100067324', 'Esika', '6180601', 'Cal. Maria Curie', 'Esika S.A'),
(43, '20508565934', 'Yanbal', '5133355', 'Av. Jose Galvez', 'Yanbal S.A.C'),
(44, '20544563441', 'Avon Products', '4376634', 'Av. General', 'Avon Products\n');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `correo` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `pass` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `rol` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `correo`, `pass`, `rol`) VALUES
(1, 'admin', 'admin@gmail.com', 'admin', 'Administrador'),
(2, 'anthony', 'anthony@gmail.com', '123456', 'Asistente'),
(4, 'dimas', 'dimas@gmail.com', '123456', 'Asistente'),
(5, 'fabrizio', 'fabrizio@gmail.com', '123456', 'Asistente'),
(9, 'ibeth', 'ibeth@gmail.com', '123456', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id`, `id_cliente`, `total`, `fecha`) VALUES
(1, 1, '25.00', '2022-10-10 03:14:19'),
(2, 2, '58.00', '2022-10-10 03:14:20'),
(3, 3, '250.00', '2022-10-10 03:14:21'),
(4, 4, '25.00', '2022-10-10 03:14:23'),
(5, 4, '29.00', '2022-10-10 03:14:24'),
(6, 4, '29.00', '2022-10-10 03:14:25'),
(7, 4, '2.00', '2022-10-10 03:14:26'),
(8, 5, '25.00', '2022-10-10 03:14:27'),
(9, 5, '25.00', '2022-10-10 03:14:28'),
(10, 5, '25.00', '2022-10-10 03:14:30'),
(11, 5, '25.00', '2022-10-10 03:14:31'),
(12, 5, '67.50', '2022-10-10 03:14:32'),
(14, 1, '252.50', '2022-10-10 03:11:59'),
(25, 5, '12.50', '2022-10-10 03:14:33'),
(27, 1, '99.80', '2022-10-10 15:58:57'),
(28, 1, '1162.70', '2022-10-10 16:10:55');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_proveedor` (`id_proveedor`);

--
-- Indices de la tabla `detalle_compra`
--
ALTER TABLE `detalle_compra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_compra` (`id_compra`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `proveedor` (`id_proveedor`),
  ADD KEY `id_proveedor` (`id_proveedor`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `detalle_compra`
--
ALTER TABLE `detalle_compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`);

--
-- Filtros para la tabla `detalle_compra`
--
ALTER TABLE `detalle_compra`
  ADD CONSTRAINT `detalle_compra_ibfk_1` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`),
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
