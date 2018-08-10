# Tokyo_A_Server

## 8/10-8/16の課題
- [ ] クライアントUIの作成
- [ ] firebaseを用いたユーザ認証のチュートリアル・実装
- [ ] サーバ構築

議事録  
https://docs.google.com/document/d/1qNGnhNMBe3uRl7y1zRI8h1Rd1NMq2WVPlHOAHi6EU_c/edit?usp=sharing

```
$ mysql -h localhost -u root -p
Enter password: [lineschool]
```

```
> SHOW DATABASES
```

```
> DROP DATABASE IF EXISTS client_info;
```

```
> CREATE DATABASE IF NOT EXISTS client_info DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

> USE client_info;
> CREATE TABLE clients (
    id bigint(13) NOT NULL,
    name varchar(255) COLLATE utf8mb4_bin NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT '2018-01-01 00:00:00'
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

> ALTER TABLE clients ADD PRIMARY KEY (id);
> ALTER TABLE clients MODIFY id bigint(13) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

> GRANT ALL PRIVILEGES ON client_info.* TO dbuser@'localhost' IDENTIFIED BY 'lineschool'; // 重要
> COMMIT;
```


`client_info`という名前のデータベースが存在しなければ作成する．
デフォルトの文字コードを`utf8mb4`に指定する（日本語に対応）．
データベース照合順序を`utf8mb4_bin`に指定する．

https://uxmilk.jp/12421
https://qiita.com/tfunato/items/e48ad0a37b8244a788f6
