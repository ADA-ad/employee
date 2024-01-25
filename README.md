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
- 『name = 佐藤』を検索し、レスポンスのボティがに名前は佐藤のデータ、1件出力される

### ③id取得API
<img width="400" alt="スクリーンショット 2024-01-22 21 22 41" src="https://github.com/ADA-ad/mybatis-demo-202312/assets/152973671/dbc829c7-f4ac-4952-bc00-016a5fd28b0b">

```bash
curl --location 'http://localhost:8080/employees/3'
``` 
- 200を返すことを確認
- 『3』を検索し、レスポンスのボティがにid = 3のデータ、1件出力される

### ④age取得API
<img width="400" alt="スクリーンショット 2024-01-22 21 25 43" src="https://github.com/ADA-ad/mybatis-demo-202312/assets/152973671/d30cf664-0e0f-4e50-ada8-e52e6721c263">

```bash
curl --location 'http://localhost:8080/employees/ages?age=16'
``` 
- 200を返すことを確認
- 『age = 16』を検索し、レスポンスのボティがにage = 3のデータ、1件出力される

### ⑤address取得API
<img width="400" alt="スクリーンショット 2024-01-25 20 09 57" src="https://github.com/ADA-ad/Java05/assets/152973671/4db22790-0800-4352-8576-b84d5e318e4c">

```bash
curl --location 'http://localhost:8080/employees/address?address=%E4%BA%AC%E9%83%BD%E5%BA%9C'
``` 
- 200を返すことを確認
- 『address = 京都府』を検索し、レスポンスのボティがに住所は京都府のデータ、1件出力される 

## CREATE処理
### ①登録処理を実装する  
<img width="400" alt="スクリーンショット 2024-01-25 20 09 57" src="https://github.com/ADA-ad/Java05/assets/152973671/4db22790-0800-4352-8576-b84d5e318e4c">


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
<img width="400" alt="スクリーンショット 2024-01-25 21 19 12" src="https://github.com/ADA-ad/Java05/assets/152973671/a854b6e9-4cb5-467d-9d20-4d192786041b"> 

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
<img width="400" alt="スクリーンショット 2024-01-25 21 24 09" src="https://github.com/ADA-ad/Java05/assets/152973671/d1730a66-6065-4a36-a469-9900cb9a0e0b"> 

```bash
curl --location 'http://localhost:8080/employees' \
--header 'Content-Type: application/json' \
--data '{
	"name": "鈴木",
	"age": 23,
	"address": "東京都品川区1-1-1"
}'
}'
``` 
- 409を返すことを確認
- nameとaddressを重複にして登録すると、「ユーザーは重複不可。 データが既に存在しています。新しいデータを追加できません」を返す 





