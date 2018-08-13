# githubの簡単な使い方
細かいgitの概要・使い方は[サルでもわかるGit入門](https://backlog.com/ja/git-tutorial/?gclid=EAIaIQobChMI5cO81onp3AIVLbvtCh1-4wY8EAAYASAAEgKjmPD_BwE)を見てもらえれば大丈夫だと思います（サルでもわかると言っておきながら，案外最初は戸惑う）．ここでは，使う重視で軽く説明します．

## git clone
githubにすでに存在するレポジトリをローカルに取ってくる．パスはgithubのクローンしたいレポジトリをブラウザで表示したとき，上の右側に`Clone or download`というタブがあるので，そこをクリックして，得られるパスを使えばOK．
```
git clone http://github.com/path/to/repository.git
```
そうすると，今いるディレクトリの下にcloneしてきたレポジトリと同じ名前のフォルダ（レポジトリ）ができる．

## git branch
branchを作る．クローンしてきた直後はmasterというbranchになっている．
PR(Pull Request)を使うことも考えて，基本的にはmasterで作業しないほうが良い．
```
git branch mybranch
```
そうすると今いるmasterブランチと同じ内容のmybranchという名前のブランチができる．
branchの表示は
```
git branch
```
でできる．
これだけだとまだブランチを作っただけなので，このあとの`git checkout`が必要．

## git checkout
branchを切り替える．`git branch`で表示された存在するbranchに切り替えられる．
```
git checkout mybranch
```
ちなみに，`git branch`と`git checkout`を同時にしたいなら，
```
git checkout -b mybranch
```

## git remote
リモートレポジトリの操作．pushするときに気にする．
```
git remote -v
```
でリモートレポジトリの一覧が見られる．
```
git remote add myremote http://github.com/path/to/repository.git
```
で好きなリモートレポジトリを追加できる．

## git pull
すでにcloneしてあるレポジトリ内で実行すると，ローカルとリモートの差分をmergeしてくれる．
```
git pull origin master
```
`git pull`の第一引数がリモートレポジトリ名，第二引数がそのリモートレポジトリに存在するブランチ名．
自分が現在編集中のファイルなどがあると，conflictする可能性もあるので注意！

## git add
ローカルに現在の差分を追加する．
```
git add myfile
```
`myfile`は追加したいファイル名．
`git add .`
で今いるディレクトリ以下のファイルをすべて追加できる．

## git status
現在の編集状況を確認する．
```
git status
```
`git add`するときに参照しよう．

## git commit
（ローカルに）コミットする．
```
git commit -m "コメント"
```
で，これまでに`git add`したファイルがすべて（ローカル）レポジトリにコミットできる．

## git push
リモートリポジトリに変更を反映させる．
```
git push origin mybranch
```
`git push`の第一引数はどのリモートレポジトリにpushするか，第二引数はそのブランチ名．基本的には今作業している，`git branch`で作成したブランチ名を入れるのが無難．ここで（やっと）リモートが変更されるので注意！どこにpushしようとしているかをしっかり確認してから実行しよう．

## 大まかな流れ
Tokyo_A_Serverを例に流れを示してみます．
```
(レポジトリをcloneしたいディレクトリに移動)

$ git clone https://github.com/line-school2018summer/Tokyo_A_Server.git
$ cd Tokyo_A_Server
$ git branch mybranch (mybranchは好きな名前)
$ git checkout mybranch

(ファイルを編集する)

$ git status
$ git add .
$ git commit -m "modified some files" (コメントは自由に)
$ git remote -v
$ git push origin mybranch
```
後日，他人の変更をローカルにmergeしたいとき，
```
(該当レポジトリに移動)

$ git pull origin master
```
