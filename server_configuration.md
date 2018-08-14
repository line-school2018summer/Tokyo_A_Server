# データベースの構成

## データ型
```
data class UserProfile(
        var id: String,
        var name: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)
```

## APIリファレンス（2018.8.14現在）

### GET `/user`
テーブル内のすべてのユーザを取得する

#### Request
None

#### Response
All UserProfiles in the table

| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | String | |
| name | String | |
| created_at | Date | |
| updated_at | Date | |

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":"fjalkcmipizx","name":"hogehoge","created_at":"2018-08-14T05:55:11.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"9xmdsm9fqoij","name":"fugafuga","created_at":"2018-08-14T05:55:26.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"mfalckj09iqw","name":"piyopiyo","created_at":"2018-08-14T05:55:41.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"3cimjdsflmca","name":"gehogeho","created_at":"2018-08-14T05:56:03.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]
```

### GET `/user/id/{id}`
idが`{id}`と一致するユーザを取得する

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | String | |

#### Response
Unique UserProfile whose id is `{id}`

| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | String | |
| name | String | |
| created_at | Date | |
| updated_at | Date | |

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user/id/mfalckj09iqw
{"id":"mfalckj09iqw","name":"piyopiyo","created_at":"2018-08-14T05:55:41.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}
```

### GET `/user/name/{name}`
ユーザ名が`{user}`と一致するユーザを取得する

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| name | String | |

#### Response
List of UserProfiles whose names are `{name}`

| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | String | |
| name | String | |
| created_at | Date | |
| updated_at | Date | |

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user/name/hogehoge
[{"id":"fjalkcmipizx","name":"hogehoge","created_at":"2018-08-14T05:55:11.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]
```

### GET `/user/likelyname/{name}`
似た名前のユーザ（ユーザ名に`{name}`が含まれるユーザ）を取得する

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| name | String | |

#### Response
List of UserProfiles whose names include `{name}`

| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | String | |
| name | String | |
| created_at | Date | |
| updated_at | Date | |

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user/likelyname/hoge
[{"id":"fjalkcmipizx","name":"hogehoge","created_at":"2018-08-14T05:55:11.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"3cimjdsflmca","name":"gehogeho","created_at":"2018-08-14T05:56:03.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]
```

### POST `/user/create/{id}/{name}`
`{id}`と`{name}`で定義されるユーザを追加する

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | String | |
| name | String | |

#### Response
None (Temporary)

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":"fjalkcmipizx","name":"hogehoge","created_at":"2018-08-14T05:55:11.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"9xmdsm9fqoij","name":"fugafuga","created_at":"2018-08-14T05:55:26.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"mfalckj09iqw","name":"piyopiyo","created_at":"2018-08-14T05:55:41.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"3cimjdsflmca","name":"gehogeho","created_at":"2018-08-14T05:56:03.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]

$ curl -X POST http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user/create/109oicmls8cf/foofoo

$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":"fjalkcmipizx","name":"hogehoge","created_at":"2018-08-14T05:55:11.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"9xmdsm9fqoij","name":"fugafuga","created_at":"2018-08-14T05:55:26.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"mfalckj09iqw","name":"piyopiyo","created_at":"2018-08-14T05:55:41.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"3cimjdsflmca","name":"gehogeho","created_at":"2018-08-14T05:56:03.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"109oicmls8cf","name":"foofoo","created_at":"2018-08-14T06:01:10.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]
```

### PUT `/user/modify/{id}/{name}`
idが`{id}`のユーザのユーザ名を`{name}`に変更する

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | String | |
| name | String | |

#### Response
None (Temporary)

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":"fjalkcmipizx","name":"hogehoge","created_at":"2018-08-14T05:55:11.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"9xmdsm9fqoij","name":"fugafuga","created_at":"2018-08-14T05:55:26.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"mfalckj09iqw","name":"piyopiyo","created_at":"2018-08-14T05:55:41.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"3cimjdsflmca","name":"gehogeho","created_at":"2018-08-14T05:56:03.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"109oicmls8cf","name":"foofoo","created_at":"2018-08-14T06:01:10.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]

$ curl -X PUT http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user/modify/fjalkcmipizx/barbar

$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":"fjalkcmipizx","name":"barbar","created_at":"2018-08-14T06:03:16.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"9xmdsm9fqoij","name":"fugafuga","created_at":"2018-08-14T05:55:26.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"mfalckj09iqw","name":"piyopiyo","created_at":"2018-08-14T05:55:41.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"3cimjdsflmca","name":"gehogeho","created_at":"2018-08-14T05:56:03.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"109oicmls8cf","name":"foofoo","created_at":"2018-08-14T06:01:10.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]
```

### DELETE `/user/delete/{id}`
idが`{id}`と一致するユーザを削除する

#### Request
| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | String | |

#### Response
Deleted UserProfile

| Parameter | Type | Description |
| -------- | -------- | -------- |
| id | String | |
| name | String | |
| created_at | Date | |
| updated_at | Date | |

#### テスト例
```
$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":"fjalkcmipizx","name":"barbar","created_at":"2018-08-14T06:03:16.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"9xmdsm9fqoij","name":"fugafuga","created_at":"2018-08-14T05:55:26.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"mfalckj09iqw","name":"piyopiyo","created_at":"2018-08-14T05:55:41.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"3cimjdsflmca","name":"gehogeho","created_at":"2018-08-14T05:56:03.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"109oicmls8cf","name":"foofoo","created_at":"2018-08-14T06:01:10.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]

$ curl -X DELETE http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user/delete/3cimjdsflmca
{"id":"3cimjdsflmca","name":"gehogeho","created_at":"2018-08-14T05:56:03.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}

$ curl -X GET http://ec2-52-197-250-179.ap-northeast-1.compute.amazonaws.com/user
[{"id":"fjalkcmipizx","name":"barbar","created_at":"2018-08-14T06:03:16.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"9xmdsm9fqoij","name":"fugafuga","created_at":"2018-08-14T05:55:26.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"mfalckj09iqw","name":"piyopiyo","created_at":"2018-08-14T05:55:41.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"},
 {"id":"109oicmls8cf","name":"foofoo","created_at":"2018-08-14T06:01:10.000+0000","updated_at":"2018-01-01T00:00:00.000+0000"}]
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
    id varchar(255) COLLATE utf8mb4_bin NOT NULL,
    name varchar(255) COLLATE utf8mb4_bin NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT '2018-01-01 00:00:00'
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

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
