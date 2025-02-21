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