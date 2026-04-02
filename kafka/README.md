# Real-time Content Moderator (Kafka Streams)

Dự án này minh họa cách sử dụng Kafka Streams để lọc và che dấu các từ ngữ không mong muốn trong tin nhắn theo thời gian thực.

## Thành phần chính
- **WordFilterStream**: Ứng dụng Kafka Streams xử lý tin nhắn từ `raw-messages` sang `clean-messages`.
- **ChatProducer**: Giả lập người dùng gửi tin nhắn.
- **ChatConsumer**: Hiển thị tin nhắn đã được làm sạch.
- **LoadGenerator**: Công cụ tạo tải lớn để thử nghiệm hiệu năng.

## Cách chạy dự án

### 1. Khởi động Kafka
Chạy Docker Compose để dựng cụm Kafka và Zookeeper:
```bash
docker-compose up -d
```

### 2. Tạo các Topic (Tùy chọn)
Kafka sẽ tự động tạo topic khi có dữ liệu, nhưng bạn nên tạo thủ công với nhiều partition để test khả năng mở rộng:
```bash
docker exec -it kafka kafka-topics --create --topic raw-messages --partitions 6 --replication-factor 1 --bootstrap-server localhost:9092
docker exec -it kafka kafka-topics --create --topic clean-messages --partitions 6 --replication-factor 1 --bootstrap-server localhost:9092
```

### 3. Build Project
```bash
mvn clean compile
```

### 4. Chạy các ứng dụng (Mở các terminal riêng biệt)

#### Bước A: Chạy Stream Processor
```bash
mvn exec:java -Dexec.mainClass="com.example.stream.WordFilterStream"
```

#### Bước B: Chạy Consumer để xem kết quả
```bash
mvn exec:java -Dexec.mainClass="com.example.consumer.ChatConsumer"
```

#### Bước C: Chạy Producer để gửi tin nhắn thử nghiệm
```bash
mvn exec:java -Dexec.mainClass="com.example.producer.ChatProducer"
```

#### Bước D: Thử nghiệm tải cao (Load Testing)
```bash
mvn exec:java -Dexec.mainClass="com.example.producer.LoadGenerator"
```

## Logic lọc tin nhắn
Hệ thống sử dụng một danh sách đen (`BLACKLIST`) và Regex để thay thế các từ như `spam`, `badword`, `thô tục`, `quảng cáo` thành `***`.
