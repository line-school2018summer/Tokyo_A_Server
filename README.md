# Tokyo_A_Server

## 8/10-8/16の課題
- [ ] クライアントUIの作成
- [ ] firebaseを用いたユーザ認証のチュートリアル・実装
- [ ] サーバ構築

議事録  
https://docs.google.com/document/d/1qNGnhNMBe3uRl7y1zRI8h1Rd1NMq2WVPlHOAHi6EU_c/edit?usp=sharing

## データ型
```
data class UserProfile(
        var id: Long,
        var name: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)
```

## APIリファレンス

### GET `/user`
テーブル内のすべてのデータを取得

#### Request
None

#### Response
All UserProfiles in the table

| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | Long | |
| name | String | |
| created_at | Date | |
| updated_at | Date | |

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":1,"name":"hogehoge","created_at":"2018-08-11T18:20:35.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":2,"name":"fugafuga","created_at":"2018-08-11T18:21:17.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":3,"name":"foofoo","created_at":"2018-08-11T18:21:25.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]
```

### GET `/user/id/{id}`
テーブル内の特定のidのデータを取得

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | Long | |

#### Response
UserProfile whose id is `{id}`

| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | Long | |
| name | String | |
| created_at | Date | |
| updated_at | Date | |

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user/id/2
{"id":2,"name":"fugafuga","created_at":"2018-08-11T18:21:17.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}
```

### POST `/user/search`
ユーザ名検索でデータを取得（動作未確認）

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| search_str | String | |

#### Response
UserProfile whose name matches `{search_str}`

| Parameter | Type | Description |
| -------- | -------- | -------- |
| results[].id | Long | |
| results[].name | String | |

### PUT `/user/name/{name}`
名前を入力としてテーブルにデータを追加

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| name | String | |

#### Response
None (Temporary)

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":1,"name":"hogehoge","created_at":"2018-08-11T18:20:35.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":2,"name":"fugafuga","created_at":"2018-08-11T18:21:17.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":3,"name":"foofoo","created_at":"2018-08-11T18:21:25.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]

$ curl -X PUT http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user/name/piyopiyo

$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":1,"name":"hogehoge","created_at":"2018-08-11T18:20:35.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":2,"name":"fugafuga","created_at":"2018-08-11T18:21:17.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":3,"name":"foofoo","created_at":"2018-08-11T18:21:25.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":4,"name":"piyopiyo","created_at":"2018-08-11T18:22:48.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]
```

### DELETE `/user/name/{name}`
テーブルから特定のユーザ名のデータを削除  
(名前が一致するものはすべて削除してしまうので，id検索に変更したほうがよさそう)

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| name | String | |

#### Response
Deleted list of UserProfiles

| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | Long | |
| name | String | |
| created_at | Date | |
| updated_at | Date | |

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":1,"name":"hogehoge","created_at":"2018-08-11T18:20:35.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":2,"name":"fugafuga","created_at":"2018-08-11T18:21:17.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":3,"name":"foofoo","created_at":"2018-08-11T18:21:25.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":4,"name":"piyopiyo","created_at":"2018-08-11T18:22:48.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]

$ curl -X DELETE http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user/name/fugafuga
[{"id":2,"name":"fugafuga","created_at":"2018-08-11T18:21:17.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]

$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":1,"name":"hogehoge","created_at":"2018-08-11T18:20:35.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":3,"name":"foofoo","created_at":"2018-08-11T18:21:25.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":4,"name":"piyopiyo","created_at":"2018-08-11T18:22:48.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]
```

## サーバ操作

### mysqlにログイン

```
$ mysql -h localhost -u root -p
Enter password: [lineschool]
```

### 存在するデータベースの一覧を表示
```
> SHOW DATABASES
```

### 特定のデータベースの削除
```
> DROP DATABASE IF EXISTS client_info;
```

### サーバ構築
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

### メモ
```
> CREATE DATABASE IF NOT EXISTS client_info DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
```
`client_info`という名前のデータベースが存在しなければ作成する．  
デフォルトの文字コードを`utf8mb4`に指定する（日本語に対応）．  
データベース照合順序を`utf8mb4_bin`に指定する．  

## コードメモ
| フォルダ名 | ファイル名 | 内容 |
| ----------------------- | ----------------------- | ----------------------- |
| model | entities.kt | データクラス（`UserProfile` etc.）の定義 |
| controller | UserController.kt | `curl`で来たrequestに対するKotlinの関数の定義（受付側） |
| mapper | UserMapper.kt | sqlのコマンド（`SELECT`, `INSERT` etc.）を実行するKotlinの関数の定義（実行側） |
| service | UserProfileService.kt | 受付側と実行側をつなぐ関数の定義 |

## References
https://uxmilk.jp/12421  
https://qiita.com/tfunato/items/e48ad0a37b8244a788f6  
