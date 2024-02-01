# 概要
DB操作を行うCRUD機能をもったREST APIを作成するプロジェクトです。

## 開発環境
- IDE : IntelliJ Community
- Java : 17
- Gradle : 8.5
- SpringBoot : 3.3.2

## CRUD処理とは

「アプリケーションを作る上で基本となる４つの処理」を指します。
- Create ：生成/登録
- Read ：読取
- Update ：更新
- Delete ：削除


## READ処理
### ①全件取得API
<img width="400" alt="スクリーンショット 2024-01-22 21 05 18" src="https://github.com/tomoya0844/kadai10/assets/152973671/80a8ecf4-a0ae-44db-8212-387b5ffa4790">

```bash
curl --location 'http://localhost:8080/employees'
``` 
- 200を返すことを確認
- レスポンスのボティが全件データ(5件)

### ②名前取得API
<img width="400" alt="スクリーンショット 2024-01-22 21 14 13" src="https://github.com/ADA-ad/mybatis-demo-202312/assets/152973671/cca05e01-dd66-4e9a-8de3-782e9de6d7b9">

```bash
curl --location 'http://localhost:8080/employees/names?name=%E4%BD%90%E8%97%A4'
``` 
- 200を返すことを確認
- 『name = 佐藤』を検索し、レスポンスのボティに名前は佐藤のデータ、1件が出力される

### ③id取得API
<img width="400" alt="スクリーンショット 2024-01-22 21 22 41" src="https://github.com/ADA-ad/mybatis-demo-202312/assets/152973671/dbc829c7-f4ac-4952-bc00-016a5fd28b0b">

```bash
curl --location 'http://localhost:8080/employees/3'
``` 
- 200を返すことを確認
- 『3』を検索し、レスポンスのボティにid = 3のデータ、1件が出力される

### ④age取得API
<img width="400" alt="スクリーンショット 2024-01-22 21 25 43" src="https://github.com/ADA-ad/mybatis-demo-202312/assets/152973671/d30cf664-0e0f-4e50-ada8-e52e6721c263">

```bash
curl --location 'http://localhost:8080/employees/ages?age=16'
``` 
- 200を返すことを確認
- 『age = 16』を検索し、レスポンスのボティにage = 3のデータ、1件が出力される

### ⑤address取得API
<img width="400" alt="スクリーンショット 2024-01-22 21 28 51" src="https://github.com/ADA-ad/mybatis-demo-202312/assets/152973671/3ed796ac-f2ad-48f1-a498-bcb099691167">

```bash
curl --location 'http://localhost:8080/employees/address?address=%E4%BA%AC%E9%83%BD%E5%BA%9C'
``` 
- 200を返すことを確認
- 『address = 京都府』を検索し、レスポンスのボティに住所は京都府のデータ、1件が出力される 

## UPDATE処理 
### ①通常処理
<img width="400" alt="スクリーンショット 2024-01-31 22 59 10" src="https://github.com/ADA-ad/employee/assets/152973671/5cade367-c146-452b-8306-0386ccbe7c7d">


```bash
curl --location --request PATCH 'http://localhost:8080/employees/2' \
--header 'Content-Type: application/json' \
--data '{
	"name": "花房　剛志",
	"age": 24,
	"address": "静岡県伊豆市1-2-3"
}'
``` 
- 201を返すことを確認
- {
  "name": "花房　剛志",
  "age": 24,
  "address": "静岡県伊豆市1-2-3"
  }をbodyに入力し、  
レスポンスのボティに "message": "ユーザーを更新しました。"が出力される  


### ②重複チェック 

<img width="400" alt="スクリーンショット 2024-02-01 20 39 53" src="https://github.com/tomoya0844/kadai10/assets/152973671/9bfb0a76-140f-452e-aaca-c5ff8013ef0c">


```bash
curl --location --request PATCH 'http://localhost:8080/employees/2' \
--header 'Content-Type: application/json' \
--data '{
	"name": "花房　剛志",
	"age": 24,
	"address": "静岡県伊豆市1-2-3"
}'
``` 
- 409を返すことを確認
- {
  "name": "花房　剛志",
  "age": 23,
  "address": "静岡県伊豆市1-2-3"
  }  
  名前と住所を重複したデータをbodyに入力し、  
  レスポンスのボティに "message": "ユーザーは重複不可。"が出力される  





