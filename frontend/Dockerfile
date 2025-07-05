# ベースイメージとしてNode.js 20を使う（Node.jsの実行環境を用意）
FROM node:20

# コンテナ内の作業ディレクトリを /app に設定
WORKDIR /app

# package.json と package-lock.json（依存関係の情報）だけを先にコピーする
COPY package.json package-lock.json ./

# 依存パッケージをインストール（npm install）
RUN npm install

# 残りのファイルをすべてコピー
COPY . .

# npm start コマンドで開発サーバーを起動
CMD ["npm", "start"]
