# Feedback App
Feedback App adalah aplikasi berbasis Java Swing yang memungkinkan pengguna memberikan umpan balik menggunakan berbagai emoji.

## Prasyarat

Pastikan Anda telah menginstal perangkat lunak berikut sebelum memulai:

- Java Development Kit (JDK) 8 atau yang lebih baru
- Apache Maven
- Git
- PostgreSQL

## Instalasi

### 1. Clone Repositori

Clone repositori ini ke mesin lokal Anda:

```bash
git clone https://github.com/username/feedback-app.git
cd feedback-app
```

### 2. Setup Database

#### a. Instal PostgreSQL

Jika Anda belum menginstal PostgreSQL, Anda dapat mengunduh dan menginstalnya dari [sini](https://www.postgresql.org/download/).

#### b. Buat Database

1. Buka terminal atau command prompt.
2. Masuk ke psql (PostgreSQL interactive terminal):

    ```bash
    psql -U postgres
    ```

3. Buat database baru:

    ```sql
    CREATE DATABASE feedback_db;
    ```

4. Buat user baru dan berikan hak akses:

    ```sql
    CREATE USER feedback_user WITH ENCRYPTED PASSWORD 'password';
    GRANT ALL PRIVILEGES ON DATABASE feedback_db TO feedback_user;
    ```

#### c. Konfigurasi Hibernate

Tambahkan file `hibernate.cfg.xml` di src/main/resources:

```xml
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
        <property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/feedback_db</property>
        <property name="hibernate.connection.username">feedback_user</property>
        <property name="hibernate.connection.password">password</property>
        <property name="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>
        <mapping class="com.mycompany.Model.Feedback"/>
        <mapping class="com.mycompany.Model.User"/>
    </session-factory>
</hibernate-configuration>
```

### 3. Tambahkan Pustaka JLayer ke Proyek Anda

Tambahkan repositori dan dependensi ke `pom.xml`:

```xml
<repositories>
    <repository>
        <id>central</id>
        <url>https://repo.maven.apache.org/maven2</url>
    </repository>
    <repository>
        <id>sonatype</id>
        <url>https://oss.sonatype.org/content/groups/public/</url>
    </repository>
    <repository>
        <id>sourceforge</id>
        <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>javazoom</groupId>
        <artifactId>jlayer</artifactId>
        <version>1.0.1</version>
    </dependency>
    <!-- Add other dependencies here -->
</dependencies>
```

### 4. Bangun Proyek Menggunakan Maven

Jalankan perintah berikut untuk membangun proyek:

```bash
mvn clean install
```

## Menjalankan Aplikasi

1. **Navigasikan ke direktori target:**

   ```bash
   cd target
   ```

2. **Jalankan aplikasi:**

   ```bash
   java -jar feedback-app-1.0-SNAPSHOT.jar
   ```

## Struktur Proyek

- `src/main/java/com/mycompany`: Kode sumber aplikasi
- `src/main/resources/images`: Gambar emoji
- `src/main/resources/audio`: File audio untuk emoji
- `src/main/resources/hibernate.cfg.xml`: Konfigurasi Hibernate
- `pom.xml`: File konfigurasi Maven

## Fitur

- Tampilan feedback dengan emoji
- Input teks dengan placeholder
- Pemutaran audio saat emoji dipilih
- Validasi input feedback

## Menggunakan JLayer untuk Pemutaran Audio MP3

Untuk memutar file audio MP3, aplikasi ini menggunakan pustaka JLayer. Pastikan Anda memiliki file audio di jalur `src/main/resources/audio` dengan nama yang sesuai (`sad_sound.mp3`, `neutral_sound.mp3`, `happy_sound.mp3`, `love_sound.mp3`).

## Kontribusi

Jika Anda ingin berkontribusi pada proyek ini, silakan fork repositori ini dan buat pull request dengan perubahan Anda.

## Lisensi

Proyek ini dilisensikan di bawah MIT License - lihat file [LICENSE](LICENSE) untuk detail lebih lanjut.
