-- =============================================
-- Database: Shopping Cart Application (s10_giohang)
-- =============================================

-- Tạo database
DROP DATABASE IF EXISTS s10_giohang;
CREATE DATABASE s10_giohang CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE s10_giohang;

-- =============================================
-- Tạo bảng product
-- =============================================
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    stock INT DEFAULT 100,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Thêm dữ liệu mẫu
-- =============================================

-- Sản phẩm điện tử
INSERT INTO product (name, price, description, image_url, stock) VALUES
('iPhone 15 Pro Max', 29990000, 'Điện thoại thông minh cao cấp với chip A17 Pro, camera 48MP, màn hình 6.7 inch Super Retina XDR', 'https://via.placeholder.com/300x300/007bff/ffffff?text=iPhone+15', 50),
('Samsung Galaxy S24 Ultra', 27990000, 'Flagship Android với bút S Pen, camera 200MP, màn hình Dynamic AMOLED 2X 6.8 inch', 'https://via.placeholder.com/300x300/28a745/ffffff?text=Galaxy+S24', 45),
('MacBook Pro M3', 45990000, 'Laptop chuyên nghiệp với chip M3, RAM 16GB, SSD 512GB, màn hình Retina 14 inch', 'https://via.placeholder.com/300x300/6c757d/ffffff?text=MacBook+Pro', 30),
('iPad Air M2', 16990000, 'Máy tính bảng mạnh mẽ với chip M2, màn hình Liquid Retina 10.9 inch, hỗ trợ Apple Pencil', 'https://via.placeholder.com/300x300/17a2b8/ffffff?text=iPad+Air', 60),
('AirPods Pro 2', 6490000, 'Tai nghe không dây chống ồn chủ động, âm thanh không gian, hộp sạc MagSafe', 'https://via.placeholder.com/300x300/ffc107/ffffff?text=AirPods', 100);

-- Sản phẩm thời trang
INSERT INTO product (name, price, description, image_url, stock) VALUES
('Áo Polo Nam Lacoste', 2890000, 'Áo polo nam cao cấp, chất liệu cotton 100%, logo cá sấu thêu nổi', 'https://via.placeholder.com/300x300/dc3545/ffffff?text=Polo+Lacoste', 80),
('Giày Nike Air Max 270', 3590000, 'Giày thể thao nam nữ, đế Air Max êm ái, thiết kế năng động', 'https://via.placeholder.com/300x300/fd7e14/ffffff?text=Nike+Air', 70),
('Túi Xách Coach', 8990000, 'Túi xách nữ da thật, thiết kế sang trọng, nhiều ngăn tiện dụng', 'https://via.placeholder.com/300x300/e83e8c/ffffff?text=Coach+Bag', 40),
('Đồng Hồ Casio G-Shock', 4290000, 'Đồng hồ thể thao chống nước 200m, nhiều tính năng thông minh', 'https://via.placeholder.com/300x300/20c997/ffffff?text=G-Shock', 55),
('Kính Mát Ray-Ban Aviator', 3990000, 'Kính mát phi công classic, gọng kim loại, tròng polarized chống UV', 'https://via.placeholder.com/300x300/6610f2/ffffff?text=Ray-Ban', 65);

-- Sản phẩm gia dụng
INSERT INTO product (name, price, description, image_url, stock) VALUES
('Nồi Cơm Điện Tử Cuckoo', 4590000, 'Nồi cơm điện tử cao tần IH, dung tích 1.8L, 10 chế độ nấu', 'https://via.placeholder.com/300x300/343a40/ffffff?text=Cuckoo', 35),
('Máy Lọc Không Khí Xiaomi', 3290000, 'Máy lọc không khí thông minh, diện tích 48m², kết nối Mi Home', 'https://via.placeholder.com/300x300/007bff/ffffff?text=Xiaomi+Air', 50),
('Robot Hút Bụi Ecovacs', 7990000, 'Robot hút bụi lau nhà tự động, AI navigation, pin 5200mAh', 'https://via.placeholder.com/300x300/28a745/ffffff?text=Ecovacs', 25),
('Máy Pha Cà Phê Delonghi', 12990000, 'Máy pha cà phê tự động, nghiền hạt tích hợp, 13 mức độ nghiền', 'https://via.placeholder.com/300x300/dc3545/ffffff?text=Delonghi', 20),
('Bình Đun Siêu Tốc Philips', 890000, 'Bình đun nước 1.7L, công suất 2200W, tự ngắt khi sôi', 'https://via.placeholder.com/300x300/ffc107/ffffff?text=Philips', 90);

-- Sản phẩm sách & văn phòng phẩm
INSERT INTO product (name, price, description, image_url, stock) VALUES
('Sách: Đắc Nhân Tâm', 89000, 'Cuốn sách kinh điển về nghệ thuật giao tiếp và ứng xử', 'https://via.placeholder.com/300x300/17a2b8/ffffff?text=Book', 150),
('Bộ Bút Montblanc', 15990000, 'Bộ bút cao cấp gồm bút máy và bút bi, mạ vàng 18K', 'https://via.placeholder.com/300x300/6c757d/ffffff?text=Montblanc', 15),
('Máy Tính Casio FX-580VN X', 590000, 'Máy tính khoa học 552 chức năng, phù hợp học sinh THPT', 'https://via.placeholder.com/300x300/20c997/ffffff?text=Casio', 120),
('Balo Laptop Targus', 1290000, 'Balo laptop 15.6 inch, nhiều ngăn, chống thấm nước', 'https://via.placeholder.com/300x300/6610f2/ffffff?text=Targus', 75),
('Sổ Tay Moleskine', 450000, 'Sổ tay cao cấp khổ A5, 240 trang, bìa cứng', 'https://via.placeholder.com/300x300/e83e8c/ffffff?text=Moleskine', 100);

-- Sản phẩm thực phẩm & đồ uống
INSERT INTO product (name, price, description, image_url, stock) VALUES
('Cà Phê Trung Nguyên Legend', 350000, 'Cà phê rang xay cao cấp 340g, hương vị đậm đà', 'https://via.placeholder.com/300x300/fd7e14/ffffff?text=Coffee', 200),
('Trà Ô Long Đài Loan', 280000, 'Trà ô long cao cấp 200g, hương thơm tự nhiên', 'https://via.placeholder.com/300x300/28a745/ffffff?text=Tea', 180),
('Mật Ong Rừng Tràm', 250000, 'Mật ong thiên nhiên 500ml, nguồn gốc U Minh', 'https://via.placeholder.com/300x300/ffc107/ffffff?text=Honey', 160),
('Hạt Điều Rang Muối', 180000, 'Hạt điều rang muối 500g, giòn ngon, bổ dưỡng', 'https://via.placeholder.com/300x300/dc3545/ffffff?text=Cashew', 140),
('Chocolate Lindt', 420000, 'Socola Thụy Sĩ cao cấp 200g, vị đắng nhẹ 70% cacao', 'https://via.placeholder.com/300x300/6610f2/ffffff?text=Chocolate', 110);

-- =============================================
-- Kiểm tra dữ liệu
-- =============================================
SELECT COUNT(*) as total_products FROM product;
SELECT * FROM product ORDER BY id;
