DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  age INT NOT NULL,
  address VARCHAR(50) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO employees (name, age, address) VALUES ("鈴木 碧", 16, "東京都品川区1-1-1");
INSERT INTO employees (name, age, address) VALUES ("佐藤 陽葵", 20, "静岡県伊豆市1-2-3");
INSERT INTO employees (name, age, address) VALUES ("高橋 大雅", 18, "大阪府和泉市2-2-3");
INSERT INTO employees (name, age, address) VALUES ("田中 ゆいと", 25, "奈良県生駒市3-1-3");
INSERT INTO employees (name, age, address) VALUES ("伊藤 はると", 28, "京都府京都市4-1-3");




