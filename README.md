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
<img width="600" alt="スクリーンショット 2024-01-22 21 05 18" src="https://github.com/tomoya0844/kadai10/assets/152973671/80a8ecf4-a0ae-44db-8212-387b5ffa4790">

```bash
curl --location 'http://localhost:8080/employees'
``` 
- 200を返すことを確認
- レスポンスのボティが全件データ(5件)

### ②名前取得API
<img width="600" alt="スクリーンショット 2024-01-22 21 14 13" src="https://github.com/ADA-ad/mybatis-demo-202312/assets/152973671/cca05e01-dd66-4e9a-8de3-782e9de6d7b9">

```bash
curl --location 'http://localhost:8080/employees/names?name=%E4%BD%90%E8%97%A4'
``` 
- 200を返すことを確認
- 『name = 佐藤』を検索し、レスポンスのボティに名前は佐藤のデータ、1件が出力される

### ③id取得API
<img width="600" alt="スクリーンショット 2024-01-22 21 22 41" src="https://github.com/ADA-ad/mybatis-demo-202312/assets/152973671/dbc829c7-f4ac-4952-bc00-016a5fd28b0b">

```bash
curl --location 'http://localhost:8080/employees/3'
``` 
- 200を返すことを確認
- 『3』を検索し、レスポンスのボティにid = 3のデータ、1件が出力される

### ④age取得API
<img width="600" alt="スクリーンショット 2024-01-22 21 25 43" src="https://github.com/ADA-ad/mybatis-demo-202312/assets/152973671/d30cf664-0e0f-4e50-ada8-e52e6721c263">

```bash
curl --location 'http://localhost:8080/employees/ages?age=16'
``` 
- 200を返すことを確認
- 『age = 16』を検索し、レスポンスのボティにage = 3のデータ、1件が出力される

### ⑤address取得API
<img width="600" alt="スクリーンショット 2024-01-25 20 09 57" src="https://github.com/ADA-ad/Java05/assets/152973671/4db22790-0800-4352-8576-b84d5e318e4c">

```bash
curl --location 'http://localhost:8080/employees/address?address=%E4%BA%AC%E9%83%BD%E5%BA%9C'
``` 
- 200を返すことを確認
- 『address = 京都府』を検索し、レスポンスのボティに住所は京都府のデータ、1件が出力される

## CREATE処理
### ①登録処理を実装する
<img width="600" alt="スクリーンショット 2024-01-25 20 09 57" src="https://github.com/ADA-ad/Java05/assets/152973671/4db22790-0800-4352-8576-b84d5e318e4c">


```bash
curl --location 'http://localhost:8080/employees' \
--header 'Content-Type: application/json' \
--data '{
	"name": "望月　智",
	"age": 29,
	"address": "東京都品川区1-1-1"
}'
``` 
- 201を返すことを確認
- name、ageとaddressを登録すると、登録した内容＋「を登録しました」を返す

### ②登録処理のバリデーションの挙動を実装する
<img width="600" alt="スクリーンショット 2024-01-25 21 19 12" src="https://github.com/ADA-ad/Java05/assets/152973671/a854b6e9-4cb5-467d-9d20-4d192786041b"> 

```bash
curl --location 'http://localhost:8080/employees' \
--header 'Content-Type: application/json' \
--data '{
	"name": "",
	"age": 23,
	"address": "m"
}'
``` 
- 400を返すことを確認
- nameを空にして登録すると、「名前を入力してください」を返す

### ③登録処理の重複チェックを実装する
<img width="600" alt="スクリーンショット 2024-01-25 21 24 09" src="https://github.com/ADA-ad/Java05/assets/152973671/d1730a66-6065-4a36-a469-9900cb9a0e0b"> 

```bash
curl --location 'http://localhost:8080/employees' \
--header 'Content-Type: application/json' \
--data '{
	"name": "鈴木",
	"age": 23,
	"address": "東京都品川区1-1-1"
}'
``` 
- 409を返すことを確認
- nameとaddressを重複にして登録すると、「ユーザーは重複不可。 データが既に存在しています。新しいデータを追加できません」を返す


## UPDATE処理 
### ①通常処理
<img width="600" alt="スクリーンショット 2024-01-31 22 59 10" src="https://github.com/ADA-ad/employee/assets/152973671/5cade367-c146-452b-8306-0386ccbe7c7d">


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
レスポンスのボティに "message": "従業員を更新しました。"が出力される  


### ②重複チェック 

<img width="600" alt="スクリーンショット 2024-02-01 20 39 53" src="https://github.com/tomoya0844/kadai10/assets/152973671/9bfb0a76-140f-452e-aaca-c5ff8013ef0c">


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
  レスポンスのボティに "message": "従業員は重複不可。"が出力される  

## ③バリデーションチェック

<img width="600" alt="スクリーンショット 2024-02-04 21 16 29" src="https://github.com/ADA-ad/employee/assets/152973671/736899cf-2530-4156-9f17-d03e1cb44c4e">


```bash
curl --location --request PATCH 'http://localhost:8080/employees/2' \
--header 'Content-Type: application/json' \
--data '{
	"name": "",
	"age": 328888,
	"address": ""
}'
``` 
- 400を返すことを確認
- {
  "name": "",
  "age": 328888,
  "address": ""
  }  
  名前と住所をnullにして、年齢を200を超える数字をbodyに入力し、  
  レスポンスのボティに  
  "field": "address",
  "message": "住所を入力してください", 

  "field": "age",
  "message": "200 以下の値にしてください", 

  "field": "name",
  "message": "名前を入力してください" 

  が出力される
- 『address = 京都府』を検索し、レスポンスのボティがに住所は京都府のデータ、1件出力される 

## DELETE処理
### ①通常処理
<img width="600" alt="スクリーンショット 2024-02-07 21 44 47" src="https://github.com/ADA-ad/employee/assets/152973671/e3a0f5fa-6542-4024-b583-43caf864088e">

```bash
curl --location --request DELETE 'http://localhost:8080/employees/delete/7' \
--data ''
``` 
- 200を返すことを確認
- 『id = 7』を入力し、「ユーザーを削除しました。」を返す

### ②nullチェック
<img width="600" alt="スクリーンショット 2024-02-07 21 54 07" src="https://github.com/ADA-ad/employee/assets/152973671/d87a101c-9916-4214-ad9f-a2a80d0e550a">

```bash
curl --location --request DELETE 'http://localhost:8080/employees/delete/70' \
--data ''
``` 
- 404を返すことを確認
- 存在しないid、『id = 70』を入力し、「従業員は存在しない。」を返す

### READ処理のServiceテスト 
#### 実装したテスト内容 

- すべての従業員が取得できること
- 存在する従業員のIDを指定した時に正常に従業員が返されること 
- 存在しないIDを指定したときに例外処理が動作すること
- 存在する名前を指定したときに正常に従業員情報が返されること
- 存在する年齢を指定したときに正常に従業員情報が返されること
- 存在する住所を指定したときに正常に従業員情報が返されること

### 六つ全てのテスト成功
<img width="600" alt="スクリーンショット 2024-02-17 18 51 08" src="https://github.com/simamumu89/Todo_API/assets/152973671/00c32f63-a22f-40ec-9a0f-010d56df9614">

### READ処理のDBテスト
#### 実装したテスト内容

- すべての従業員が取得できること
- 指定したIDの従業員情報を獲得すること
- 存在しないIDを指定する場合に空の情報を獲得すること
- 指定した名前の従業員情報を獲得すること
- 指定した年齢の従業員情報を獲得すること
- 指定した住所の従業員情報を獲得すること

### 六つ全てのテスト成功

<img width="600" alt="スクリーンショット 2024-02-17 18 46 06" src="https://github.com/simamumu89/Todo_API/assets/152973671/9c77352a-6892-42fe-924d-b486ef1914b9">

### CREATE処理のServiceテスト
#### 実装したテスト内容

- 存在しない従業員情報を新規登録すること
- 既に存在する従業員情報を新規登録すること

### 二つ全てのテスト成功

<img width="600" alt="スクリーンショット 2024-02-19 20 19 17" src="https://github.com/ADA-ad/employee/assets/152973671/f8524f6e-8c6f-4b9e-9942-e634ecbe3748">
<img width="600" alt="スクリーンショット 2024-02-19 20 19 52" src="https://github.com/ADA-ad/employee/assets/152973671/58a5a9df-a6c6-430b-bf3d-ab7eecd66d5f"> 

### CREATE処理のDBテスト
#### 実装したテスト内容

- 新規の従業員が登録できること

### テスト成功

<img width="600" alt="スクリーンショット 2024-02-19 20 20 28" src="https://github.com/ADA-ad/employee/assets/152973671/6c0e1f2e-0f90-4b7b-8ba2-a0d8c069e95e">
