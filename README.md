# サウナ占い
## 概要
優柔不断なあなたへ、占い形式でランダムにサウナを提案するアプリケーションです。Let the sauna fortune decide where we go today!

### 技術スタック
- **バックエンド**: Spring Boot
- **データベース**: PostgreSQL
- **フロントエンド**: React
- **コンテナ化**: Docker
- **デプロイ方法**: Render（予定）

### セットアップ
1. リポジトリをクローンします。
2. `.env`ファイルを作成し、データベースの接続情報を設定します。
3. Docker Composeを使用してアプリケーションを起動します。

```bash
docker-compose up --build
```
4. ブラウザで`http://localhost:8080`にアクセスします。

5. 起動停止は以下のコマンドで行います。

```bash
# コンテナを停止削除する。バックグラウンド起動時でも止められる
docker-compose down
```
もしくは、ctrl + Cで停止できます(フォアグラウンド実行のみ停止可能)。
