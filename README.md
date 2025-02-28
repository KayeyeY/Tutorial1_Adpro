# Tutorial1_Adpro

# Module 1: Coding Standard

## Reflection 1
Saya menggunakan penamaan variabel, function, kelas yang menjelaskan kegunaannya dengan panjang yang relatif pendek
sehingga membantu dan memudahkan saya dalam memanggil yang dibutuhkan. Penggunaan branch pada github juga membantu
saya dalam men-track error yang terjadi sehingga tidak membuat kesulitan dalam mencari error.
Comment juga diberikan di beberapa bagian kode meskipun tidak panjang namun cukup untuk menjelaskan bagian
kode tersebut. Saya juga menggunakan UUID generator untuk memudahkan dalam pengambilan data dalam
create dan delete data dari list product yang diinginkan

Penerapan secure coding sudah diterapkan seperti pengecekan pada UUID produk yang akan mengecek apakah UUID
ada atau tidak pada produk sebelum UUID ditambahkan

### Explain how to improve your code:
Dalam mengerjakan tutorial ini, ketika mendapat error atau kesalahan dalam kode saya mengecek help desk pada
discord Advance Programing untuk mencari permasalahan yang sama dengan yang saya alami, tidak jarang juga saya
bertanya kepada teman dan asisten dosen di forum untuk mencari solusi jika permasalahan yang saya alami 
tidak ada pada help desk discord.


# Module 2: CI/CD & DevOps
Link deployment: https://corporate-yasmin-kayeyey-b3dba0e7.koyeb.app/product/list

## Reflection 1
- Saya mendapatkan code quality problem yang di detect JACOCO dan PMD, permasalahan yang saya coba fix dari pmd adalah
Unnecessary modifier public on method yang sebelumnya ada public perlu dihapus public-nya.

```java
public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
    Product findById(String productId);
    Product update(Product product);
    void delete(String productId);
}
```

- Problem This utility class has a non-private constructor, untuk solved solution dikarenakan main tidak perlu untuk private
maka ditambahkan line di bawah.
```java
@SuppressWarnings("PMD.UseUtilityClass")
```

- Problem ```import org.springframework.web.bind.annotation.*;``` tidak saya perbaiki dikarenakan untuk memperbaiki nya perlu
untuk import yang digunakan saja, terkadang dari Intellij nya sendiri ketika banyak yang diimport dari springframework akan
secara otomatis menjadi ```import org.springframework.web.bind.annotation.*;```.

## Reflection 2
- Menurut saya, CI/CD sudah diimplementasikan pada kode proyek saya.Untuk Continuous saya menggunakan Github Actions Workflow 
untuk menjalankan Workflow yang sudah saya buat seperti ci.yml, scorecard dan pmd.yml. Workflow tersebut akan secara otomatis mendetect ketika saya
menjalankan push atau pull pada branch yang saya pilih. Untuk Continuous Deployment saya menggunakan Koyeb yang akan deploy secara otomatis setiap
saya melakukan push atau pull ke suatu branch.

# Module 3: Maintainability & OO Principles

## Reflection 1
### S.O.L.I.D
Single Responsibility Principle:
Sudah diterapkan pada ``ProductController`` dan ``CarController``
- ``ProductController`` berfungsi untuk melakukan mapping dengan endpoint /product.
- ``CarController`` berfungsi untuk melakukan mapping dengan endpoint /car.

Open / Closed Principle:
Sudah diterapkan pada ``CarServiceImpl`` dan ``ProductServiceImpl``
sebagai contoh pada ``ProductServiceImpl``:
- Bergantung pada interface (ProductService), bukan implementasi konkret → Jika ingin mengganti 
cara penyimpanan data (misalnya dari in-memory ke database), cukup buat implementasi baru tanpa mengubah kode yang ada.
- Terbuka untuk ekstensi, tertutup untuk modifikasi → Jika ada fitur baru (misalnya pencarian berdasarkan kategori), 
bisa cukup menambahkan metode baru di interface dan implementasinya tanpa mengubah logika yang sudah ada.

Liskov Substitution Principle:
- Pada branch before-solid, CarController awalnya merupakan subclass dari ProductController, meskipun keduanya memiliki 
perilaku yang berbeda. Contohnya, metode editProduct di ProductController menggunakan metode POST untuk mengedit produk, 
sementara editCarPost di CarController juga menggunakan POST, tetapi dengan penanganan yang berbeda. 
Selain itu, deleteProduct di ProductController menggunakan metode GET, sedangkan deleteCar di CarController menggunakan 
metode POST. Perbedaan ini menyebabkan objek dari ProductController tidak dapat digantikan dengan objek dari 
CarController, sehingga melanggar Liskov Substitution Principle (LSP).
Sebagai solusinya, saya menghapus extends dari CarController, menjadikannya sebagai kelas independen yang tidak 
bergantung pada ProductController. Hal ini memungkinkan setiap controller memiliki perilaku yang sesuai dengan kebutuhan 
masing-masing, tanpa dipaksa mengikuti struktur yang tidak cocok.

Interface Segregation Principle:
- Prinsip ini sudah diterapkan pada CarService. Menurut saya, tidak perlu dilakukan pemisahan lebih lanjut karena 
interface ini sudah berfokus pada satu tujuan utama, yaitu operasi CRUD (Create, Read, Update, Delete) untuk Car.

Dependency Inversion Principle:
- Pada branch before-solid, CarController secara langsung bergantung pada CarServiceImpl, yang bukan praktik yang baik. 
Seharusnya, CarController bergantung pada antarmuka CarService agar lebih fleksibel dan sesuai dengan prinsip desain yang baik. 
Oleh karena itu, saya mengubah tipe data variabel carServiceImpl di CarController menjadi CarService. Dengan mengganti tipe 
data dari CarServiceImpl menjadi CarService, CarController sekarang bergantung pada antarmuka, bukan implementasi langsung. 

## Reflection 2
Prinsip S.O.L.I.D membantu menciptakan kode yang lebih bersih, fleksibel, dan mudah di-maintain. SRP memastikan setiap kelas 
memiliki satu tanggung jawab, sehingga lebih mudah diuji dan dimodifikasi. OCP memungkinkan kode diperluas tanpa mengubah kode 
yang ada, meningkatkan reusability. LSP memastikan subclass dapat menggantikan superclass tanpa mengubah perilaku yang 
diharapkan, menjaga kestabilan sistem. ISP mencegah ketergantungan yang tidak perlu dengan membagi interface besar menjadi 
lebih spesifik. DIP memastikan kelas bergantung pada abstraksi, bukan implementasi konkret, sehingga sistem lebih fleksibel dan mudah diuji.

## Reflection 3
Kode yang saya buat menjadi sulit untuk dikelola dan memerlukan usaha lebih besar saat melakukan modifikasi. Selain itu, 
dalam kerja tim, proses code review akan lebih rumit karena kode sulit dipahami.
Jika SRP tidak diterapkan pada CarController, saya mungkin tidak kesulitan menemukan bagian kode yang menangani mapping 
dengan endpoint /car, karena saya yang menulisnya. Namun, bagi orang lain yang membaca repositori, akan sulit menemukan 
kode tersebut jika digabung dalam satu file dengan ProductController. Selain itu, tanpa menerapkan LSP, subclass 
CarController tidak dapat sepenuhnya menggantikan superclass-nya, yang mengakibatkan masalah dalam penggunaan kembali kode.